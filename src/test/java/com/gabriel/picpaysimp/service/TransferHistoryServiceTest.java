package com.gabriel.picpaysimp.service;

import com.gabriel.picpaysimp.domain.transferhistory.TransferHistory;
import com.gabriel.picpaysimp.dto.TransferHistoryDTO;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.domain.user.UserType;
import com.gabriel.picpaysimp.repository.TransferHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class TransferHistoryServiceTest {
    @InjectMocks
    TransferHistoryService transferHistoryService;
    @Mock
    TransferHistoryRepository transferHistoryRepository;

    @Test
    @DisplayName("saveTransfer transfer history is saved when successfull")
    void saveTransfer_TransferHistoryIsSaved_WhenSuccessfull() {
        User payer = new User(1L, new BigDecimal(100), "example1@gmail.com","23453234388" , "exampleCommon1", UserType.COMMON);

        User payee = new User(2L, new BigDecimal(100), "example2@gmail.com","23453234377" , "exampleCommon2", UserType.COMMON);

        TransferHistoryDTO transferHistoryDTO = new TransferHistoryDTO(new BigDecimal(100), LocalDateTime.now(), payer, payee);

        TransferHistory transferToSave = new TransferHistory(
                transferHistoryDTO.transferAmount(),
                transferHistoryDTO.timestamp(),
                transferHistoryDTO.payer(),
                transferHistoryDTO.payee());

        BDDMockito.when(transferHistoryRepository.save(ArgumentMatchers.any(TransferHistory.class)))
                .thenReturn(transferToSave);

        transferHistoryService.saveTransfer(transferHistoryDTO);

        verify(transferHistoryRepository).save(ArgumentMatchers.any(TransferHistory.class));
    }
}