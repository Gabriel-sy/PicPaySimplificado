package com.gabriel.picpaysimp.domain.retailer;

import com.gabriel.picpaysimp.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "retailer")
@Table(name = "retailer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    private Double money;
    private String name;
    @Embedded
    private User userDetails;

    public Retailer(Double money, String nome, User userDetails) {
        this.money = money;
        this.name = nome;
        this.userDetails = userDetails;
    }
}
