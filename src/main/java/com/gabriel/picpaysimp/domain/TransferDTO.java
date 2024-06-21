package com.gabriel.picpaysimp.domain;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record TransferDTO(@NotEmpty BigDecimal value, @NotEmpty Long payer, @NotEmpty Long payee) {
}
