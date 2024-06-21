package com.gabriel.picpaysimp.domain;

import jakarta.validation.constraints.NotEmpty;

public record TransferDTO(@NotEmpty Double value, @NotEmpty Long payer, @NotEmpty Long payee) {
}
