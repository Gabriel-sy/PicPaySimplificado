package com.gabriel.picpaysimp.service;

import com.gabriel.picpaysimp.config.TransferRequests;
import com.gabriel.picpaysimp.domain.TransferDTO;
import com.gabriel.picpaysimp.domain.common.CommonUser;
import com.gabriel.picpaysimp.domain.retailer.Retailer;
import com.gabriel.picpaysimp.exception.InsufficientFundsException;
import com.gabriel.picpaysimp.exception.InvalidPayerException;
import com.gabriel.picpaysimp.exception.TransferAuthenticationException;
import com.gabriel.picpaysimp.exception.UserNotFoundException;
import com.gabriel.picpaysimp.repository.CommonUserRepository;
import com.gabriel.picpaysimp.repository.RetailerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final CommonUserRepository commonUserRepository;
    private final TransferRequests transferRequests;
    private final RetailerRepository retailerRepository;
    private static final String INVALIDIDMESSAGE = "ID inválido";

    public TransferService(CommonUserRepository commonUserRepository, TransferRequests transferRequests, RetailerRepository retailerRepository) {
        this.commonUserRepository = commonUserRepository;
        this.transferRequests = transferRequests;
        this.retailerRepository = retailerRepository;
    }

    @Transactional
    public void transfer(TransferDTO transferDTO) {
        isPayerRetailer(transferDTO.payer());

        CommonUser payer = findPayer(transferDTO.payer());

        checkPayerWallet(payer.getMoney(), transferDTO.value());

        checkAuthorization();

        //Verificando quem é o payee e fazendo a transferência/envio de notificações de
        // acordo com o objeto.
        if (commonUserRepository.existsById(transferDTO.payee())){
            CommonUser payee = findCommonUserPayee(transferDTO.payee());

            transferMoney(payer, payee, transferDTO.value());

            sendNotifications(payer, payee, transferDTO.value());
        } else {
            Retailer payee = findRetailerPayee(transferDTO.payee());

            transferMoney(payer, payee, transferDTO.value());

            sendNotifications(payer, payee, transferDTO.value());
        }

    }

    //Encontrando Payer
    private CommonUser findPayer(Long id){
        return commonUserRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(INVALIDIDMESSAGE));
    }

    //Verificando se o Payee é CommonUser, retornando o objeto se sim.
    private CommonUser findCommonUserPayee(Long id){
        return commonUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(INVALIDIDMESSAGE));
    }

    //Verificando se o Payee é Retailer, retornando o objeto se sim.
    private Retailer findRetailerPayee(Long id){
        return retailerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(INVALIDIDMESSAGE));
    }

    private void checkAuthorization() {
        if (!transferRequests.authorizeRequest()){
            throw new TransferAuthenticationException("Transação não autorizada.");
        }
    }

    //Transfer com CommonUser de payee
    private void transferMoney(CommonUser payer, CommonUser payee, Double value) {
        payee.setMoney(payee.getMoney() + value);
        commonUserRepository.save(payee);

        payer.setMoney(payer.getMoney() - value);
        commonUserRepository.save(payer);
    }

    //Transfer com Retailer de payee
    private void transferMoney(CommonUser payer, Retailer payee, Double value) {
        payee.setMoney(payee.getMoney() + value);
        retailerRepository.save(payee);

        payer.setMoney(payer.getMoney() - value);
        commonUserRepository.save(payer);
    }


    public void checkPayerWallet(Double payerBalance, Double transferAmount){
        if (payerBalance < transferAmount){
            throw new InsufficientFundsException("Pagador não tem dinheiro suficiente");
        }
    }

    public void isPayerRetailer(Long id){
        if (retailerRepository.existsById(id)){
            throw new InvalidPayerException("Apenas usuários comuns podem fazer pagamentos");
        }
    }

    //Notificações com CommonUser de payee
    private void sendNotifications(CommonUser payer, CommonUser payee, Double amount) {
        transferRequests.senderNotification(payer, amount);
        transferRequests.receiverNotification(payee, amount);
    }

    //Notificações com Retailer de payee
    private void sendNotifications(CommonUser payer, Retailer payee, Double amount) {
        transferRequests.senderNotification(payer, amount);
        transferRequests.receiverNotification(payee, amount);
    }


}
