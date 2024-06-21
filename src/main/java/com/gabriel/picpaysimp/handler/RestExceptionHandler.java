package com.gabriel.picpaysimp.handler;

import com.gabriel.picpaysimp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TransferAuthenticationException.class)
    public ResponseEntity<TransferAuthenticationExceptionDetails> transferAuthenticationExceptionHandler(
            TransferAuthenticationException exception){
        return new ResponseEntity<>(
                TransferAuthenticationExceptionDetails.builder()
                        .title("Falha na autenticação da transação")
                        .status(HttpStatus.FORBIDDEN.value())
                        .details(exception.getMessage())
                        .time(LocalDateTime.now())
                        .build(), HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionDetails> userNotFoundExceptionHandler(
            UserNotFoundException exception){
        return new ResponseEntity<>(
                UserNotFoundExceptionDetails.builder()
                        .title("Usuário não encontrado")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(exception.getMessage())
                        .time(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<InsufficientFundsExceptionDetails> insufficientFundsExceptionHandler(
            InsufficientFundsException exception){
        return new ResponseEntity<>(
                InsufficientFundsExceptionDetails.builder()
                        .title("Fundos insuficientes")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(exception.getMessage())
                        .time(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidPayerException.class)
    public ResponseEntity<InvalidPayerExceptionDetails> invalidPayerExceptionHandler(
            InvalidPayerException exception){
        return new ResponseEntity<>(
                InvalidPayerExceptionDetails.builder()
                        .title("Pagador inválido")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(exception.getMessage())
                        .time(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<NotificationExceptionDetails> notificationExceptionHandler(
            NotificationException exception){
        return new ResponseEntity<>(
                NotificationExceptionDetails.builder()
                        .title("Erro no envio da notificação")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .details(exception.getMessage())
                        .time(LocalDateTime.now())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
