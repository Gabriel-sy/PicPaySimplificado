package com.gabriel.picpaysimp.config;

import com.gabriel.picpaysimp.domain.NotificationDetails;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.exception.NotificationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class TransferRequests {
    WebClient.Builder webClient = WebClient.builder();

    String notifyUrl = "https://util.devi.tools/api/v1/notify";
    String authorizeUrl = "https://util.devi.tools/api/v2/authorize";

    public boolean authorizeRequest(){

        //Quando a resposta vem com o campo "authorization" false, é lançada uma WebClientResponseException
        //Logo, se ocorrer essa exceção, retorna false, se não, retorna true.
        try {
            webClient.build()
                    .get()
                    .uri(authorizeUrl)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return true;
        } catch (WebClientResponseException e) {
            return false;
        }
    }

    public void receiverNotification(User user, BigDecimal recievedAmount){
        String message = user.getName() + " você acabou de receber: " + recievedAmount.toString() + "na sua conta!";

        NotificationDetails notificationDetails = new NotificationDetails(message,
                LocalDateTime.now(),
                user.getEmail());

        try {
            webClient.build()
                    .post()
                    .uri(notifyUrl)
                    .bodyValue(notificationDetails)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new NotificationException("Erro ao enviar notificação do recebedor");
        }

    }

    public void senderNotification(User user, BigDecimal amountSent){

        String message = user.getName() + " você acabou de enviar: " + amountSent.toString();

        NotificationDetails notificationDetails = new NotificationDetails(message,
                LocalDateTime.now(),
                user.getEmail());

        try {
            webClient.build()
                    .post()
                    .uri(notifyUrl)
                    .bodyValue(notificationDetails)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new NotificationException("Erro ao enviar notificação do pagador");
        }

    }
}
