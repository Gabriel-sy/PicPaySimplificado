package com.gabriel.picpaysimp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "commonUser")
@Table(name = "commonUser")
@Data
public class CommonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double money;
    private Long userId;
}
