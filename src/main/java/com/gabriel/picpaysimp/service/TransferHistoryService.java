package com.gabriel.picpaysimp.service;

import com.gabriel.picpaysimp.domain.transferhistory.TransferHistory;
import com.gabriel.picpaysimp.dto.TransferHistoryDTO;
import com.gabriel.picpaysimp.repository.TransferHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferHistoryService {
    private final TransferHistoryRepository transferHistoryRepository;

    public TransferHistoryService(TransferHistoryRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }


    public void saveTransfer(TransferHistoryDTO transferHistoryDTO){
        TransferHistory transferToSave = new TransferHistory(
                transferHistoryDTO.transferAmount(),
                transferHistoryDTO.timestamp(),
                transferHistoryDTO.payer(),
                transferHistoryDTO.payee());

        transferHistoryRepository.save(transferToSave);
    }

    public List<TransferHistory> findAll(){
        return transferHistoryRepository.findAll();
    }
}
