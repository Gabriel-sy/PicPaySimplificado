package com.gabriel.picpaysimp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
public class NotificationDetails {
    private String message;
    private LocalDateTime localDateTime;
    private String email;
}
