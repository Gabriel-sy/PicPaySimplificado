package com.gabriel.picpaysimp.exception;

public class InvalidPayerException extends RuntimeException {
    public InvalidPayerException(String message) {
        super(message);
    }
}
