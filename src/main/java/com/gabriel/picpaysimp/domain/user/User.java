package com.gabriel.picpaysimp.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal money;
    private String name;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private UserType userType;

    public User(BigDecimal money, String name, String document, String email, UserType userType) {
        this.money = money;
        this.name = name;
        this.document = document;
        this.email = email;
        this.userType = userType;
    }
}
