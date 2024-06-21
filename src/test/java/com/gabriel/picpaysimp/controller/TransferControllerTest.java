package com.gabriel.picpaysimp.controller;

import com.gabriel.picpaysimp.domain.TransferDTO;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.domain.user.UserType;
import com.gabriel.picpaysimp.service.TransferService;
import com.gabriel.picpaysimp.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

@SpringBootTest
class TransferControllerTest {
    @InjectMocks
    private TransferController transferController;
    @Mock
    private TransferService transferService;
    @Mock
    private UserService userService;

    @Test
    void transferApi_ReturnsOk_WhenSuccessfull() {
        TransferDTO transferDTO = new TransferDTO(new BigDecimal(100), 1L, 2L);

        BDDMockito.doNothing().when(transferService).transfer(transferDTO);

        ResponseEntity<Void> entity = transferController.transferApi(transferDTO);

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(transferService).transfer(transferDTO);

    }

    @Test
    @DisplayName("findUserById returns ok when successfull")
    void findUserById_ReturnsOk_WhenSuccessfull() {
        User user = new User(1L, new BigDecimal(100), "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);
        BDDMockito.when(userService.findUserById(1L)).thenReturn(user);

        ResponseEntity<User> entity = transferController.findUserById(1L);

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(userService).findUserById(1L);
    }
}