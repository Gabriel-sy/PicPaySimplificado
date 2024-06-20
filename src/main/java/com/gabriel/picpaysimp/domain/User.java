package com.gabriel.picpaysimp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Embeddable
@Data
public class User {
    @Column(unique = true)
    @NotEmpty
    private String cpf;
    @Column(unique = true)
    @NotEmpty
    private String email;
}
