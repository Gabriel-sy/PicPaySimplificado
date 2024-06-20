package com.gabriel.picpaysimp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "retailer")
@Table(name = "retailer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double money;
    private String nome;
    @Embedded
    private User userDetails;

    public Retailer(Double money, String nome, User userDetails) {
        this.money = money;
        this.nome = nome;
        this.userDetails = userDetails;
    }
}
