package com.gabriel.picpaysimp.repository;

import com.gabriel.picpaysimp.domain.transferhistory.TransferHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferHistoryRepository extends JpaRepository<TransferHistory, Long> {
}
