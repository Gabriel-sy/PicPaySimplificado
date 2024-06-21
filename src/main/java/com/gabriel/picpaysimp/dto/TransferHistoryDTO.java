package com.gabriel.picpaysimp.dto;

import com.gabriel.picpaysimp.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferHistoryDTO(BigDecimal transferAmount, LocalDateTime timestamp, User payer, User payee) {
}
