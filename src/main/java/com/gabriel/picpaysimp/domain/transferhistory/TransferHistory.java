package com.gabriel.picpaysimp.domain.transferhistory;

import com.gabriel.picpaysimp.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transferHistory")
@Table(name = "transferHistory")
@NoArgsConstructor
@Data
public class TransferHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal transferAmount;
    private LocalDateTime timestamp;
    @ManyToOne
    private User payer;
    @ManyToOne
    private User payee;

    public TransferHistory(BigDecimal transferAmount, LocalDateTime timestamp, User payer, User payee) {
        this.transferAmount = transferAmount;
        this.timestamp = timestamp;
        this.payer = payer;
        this.payee = payee;
    }
}
