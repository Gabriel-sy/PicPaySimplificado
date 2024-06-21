package com.gabriel.picpaysimp.service;

import com.gabriel.picpaysimp.config.TransferRequests;
import com.gabriel.picpaysimp.domain.TransferDTO;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.domain.user.UserType;
import com.gabriel.picpaysimp.exception.InsufficientFundsException;
import com.gabriel.picpaysimp.exception.InvalidPayerException;
import com.gabriel.picpaysimp.exception.TransferAuthenticationException;
import com.gabriel.picpaysimp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {
    private final TransferRequests transferRequests;
    private final UserRepository userRepository;
    private final UserService userService;

    public TransferService(TransferRequests transferRequests, UserRepository userRepository, UserService userService) {
        this.transferRequests = transferRequests;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public void transfer(TransferDTO transferDTO) {
        isPayerRetailer(transferDTO.payer());

        User payer = userService.findUserById(transferDTO.payer());
        User payee = userService.findUserById(transferDTO.payee());

        checkPayerWallet(payer.getMoney(), transferDTO.value());

        checkAuthorization();

        transferMoney(payer, payee, transferDTO.value());

        sendNotifications(payer, payee, transferDTO.value());
    }


    private void checkAuthorization() {
        if (!transferRequests.authorizeRequest()){
            throw new TransferAuthenticationException("Transação não autorizada.");
        }
    }

    private void transferMoney(User payer, User payee, BigDecimal value) {
        payee.setMoney(payee.getMoney().add(value));
        userRepository.save(payee);

        payer.setMoney(payer.getMoney().subtract(value));
        userRepository.save(payer);
    }


    public void checkPayerWallet(BigDecimal payerBalance, BigDecimal transferAmount){
        if (payerBalance.compareTo(transferAmount) < 0){
            throw new InsufficientFundsException("Pagador não tem dinheiro suficiente");
        }
    }

    public void isPayerRetailer(Long id){
        if (userService.findUserById(id).getUserType().equals(UserType.RETAILER)){
            throw new InvalidPayerException("Apenas usuários comuns podem fazer pagamentos");
        }
    }


    private void sendNotifications(User payer, User payee, BigDecimal amount) {
        transferRequests.senderNotification(payer, amount);
        transferRequests.receiverNotification(payee, amount);
    }


}
