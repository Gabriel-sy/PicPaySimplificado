package com.gabriel.picpaysimp.service;

import com.gabriel.picpaysimp.config.TransferRequests;
import com.gabriel.picpaysimp.dto.TransferDTO;
import com.gabriel.picpaysimp.dto.TransferHistoryDTO;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.domain.user.UserType;
import com.gabriel.picpaysimp.exception.InsufficientFundsException;
import com.gabriel.picpaysimp.exception.InvalidPayerException;
import com.gabriel.picpaysimp.exception.TransferAuthenticationException;
import com.gabriel.picpaysimp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TransferServiceTest {
    @InjectMocks
    private TransferService transferService;
    @Mock
    private UserService userService;
    @Mock
    private TransferRequests transferRequests;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TransferHistoryService transferHistoryService;

    @BeforeEach
    void setUp(){
        BigDecimal defaultMoney = new BigDecimal(100);
        User payer = new User(1L, defaultMoney, "example1@gmail.com","23453234388" , "exampleCommon1", UserType.COMMON);
        User payee = new User(2L, defaultMoney, "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);
        BDDMockito.when(userRepository.save(payee)).thenReturn(payee);
        BDDMockito.when(userRepository.save(payer)).thenReturn(payer);
        BDDMockito.when(userService.findUserById(1L)).thenReturn(payer);
        BDDMockito.when(userService.findUserById(2L)).thenReturn(payee);
        BDDMockito.when(transferRequests.authorizeRequest()).thenReturn(true);

    }

    @Test
    @DisplayName("transfer doesnt throw exception and methods are executed when successfull")
    void transfer_DoesntThrowExceptionAndMethodsAreExecuted_WhenSuccessfull() {
        BigDecimal defaultMoney = new BigDecimal(100);
        User payer = new User(1L, defaultMoney, "example1@gmail.com","23453234388" , "exampleCommon1", UserType.COMMON);
        User payee = new User(2L, defaultMoney, "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);
        TransferDTO transferDTO = new TransferDTO(defaultMoney, payer.getId(), payee.getId());

        transferService.transfer(transferDTO);


        verify(userService).findUserById(1L);
        verify(userService).findUserById(2L);
        payee.setMoney(new BigDecimal(200));
        verify(userRepository).save(payee);
        payer.setMoney(new BigDecimal(0));
        verify(userRepository).save(payer);
        verify(transferHistoryService).saveTransfer(ArgumentMatchers.any(TransferHistoryDTO.class));
    }

    @Test
    @DisplayName("transfer throws TransferAuthenticationException when Authorization Is False")
    void transfer_ThrowsTransferAuthenticationException_WhenAuthorizationIsFalse() {
        BigDecimal defaultMoney = new BigDecimal(100);
        User payer = new User(1L, defaultMoney, "example1@gmail.com","23453234388" , "exampleCommon1", UserType.COMMON);
        User payee = new User(2L, defaultMoney, "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);
        TransferDTO transferDTO = new TransferDTO(defaultMoney, payer.getId(), payee.getId());

        when(transferRequests.authorizeRequest()).thenReturn(false);

        Assertions.assertThatExceptionOfType(TransferAuthenticationException.class)
                .isThrownBy(() -> transferService.transfer(transferDTO));
    }

    @Test
    @DisplayName("transfer throws InsufficientFundsException when payer doesnt have funds")
    void transfer_ThrowsInsufficientFundsException_WhenPayerDoesntHaveFunds() {
        BigDecimal defaultMoney = new BigDecimal(100);
        User payer = new User(1L, defaultMoney, "example1@gmail.com","23453234388" , "exampleCommon1", UserType.COMMON);
        User payee = new User(2L, defaultMoney, "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);
        TransferDTO transferDTO = new TransferDTO(defaultMoney, payer.getId(), payee.getId());

        payer.setMoney(new BigDecimal(0));
        BDDMockito.when(userService.findUserById(1L)).thenReturn(payer);

        Assertions.assertThatExceptionOfType(InsufficientFundsException.class)
                .isThrownBy(() -> transferService.transfer(transferDTO));
    }

    @Test
    @DisplayName("transfer throws InvalidPayerException when payer is retailer")
    void transfer_ThrowsInvalidPayerException_WhenPayerIsRetailer() {
        BigDecimal defaultMoney = new BigDecimal(100);
        User payer = new User(1L, defaultMoney, "example1@gmail.com","23453234388" , "exampleCommon1", UserType.COMMON);
        User payee = new User(2L, defaultMoney, "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);

        TransferDTO transferDTO = new TransferDTO(defaultMoney, payer.getId(), payee.getId());

        payer.setUserType(UserType.RETAILER);
        BDDMockito.when(userService.findUserById(1L)).thenReturn(payer);

        Assertions.assertThatExceptionOfType(InvalidPayerException.class)
                .isThrownBy(() -> transferService.transfer(transferDTO));
    }



}