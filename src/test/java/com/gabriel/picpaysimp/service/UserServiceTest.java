package com.gabriel.picpaysimp.service;

import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.domain.user.UserType;
import com.gabriel.picpaysimp.exception.UserNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTest {


    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @Test
    @DisplayName("findUserById returns user when successfull")
    void findUserById_ReturnsUser_WhenSuccessfull() {
        User user = new User(1L, new BigDecimal(100), "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);

        BDDMockito.when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(user));

        User userById = userService.findUserById(1L);

        Assertions.assertThat(Optional.of(userById))
                .isNotNull()
                .isEqualTo(Optional.of(user));

        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("findUserById throws UserNotFoundException when id is invalid")
    void findUserById_ThrowsUserNotFoundException_WhenIdIsInvalid() {
        Assertions.assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> userService.findUserById(321L));
    }
}