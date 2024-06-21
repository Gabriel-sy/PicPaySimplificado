package com.gabriel.picpaysimp.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class User {
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;

}
