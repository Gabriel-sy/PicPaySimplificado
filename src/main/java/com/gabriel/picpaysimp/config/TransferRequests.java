package com.gabriel.picpaysimp.config;

import com.gabriel.picpaysimp.domain.NotificationDetails;
import com.gabriel.picpaysimp.domain.common.CommonUser;
import com.gabriel.picpaysimp.domain.retailer.Retailer;
import com.gabriel.picpaysimp.exception.NotificationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

    public void receiverNotification(CommonUser commonUser, Double recievedAmount){
        String message = commonUser.getName() + " você acabou de receber: " + recievedAmount + "na sua conta!";

        NotificationDetails notificationDetails = new NotificationDetails(message,
                LocalDateTime.now(),
                commonUser.getUserDetails().getEmail());

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

    public void receiverNotification(Retailer retailer, Double recievedAmount){
        String message = retailer.getName() + " você acabou de receber: " + recievedAmount + "na sua conta!";

        NotificationDetails notificationDetails = new NotificationDetails(message,
                LocalDateTime.now(),
                retailer.getUserDetails().getEmail());

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

    public void senderNotification(CommonUser commonUser, Double amountSent){

        String message = commonUser.getName() + " você acabou de enviar: " + amountSent;

        NotificationDetails notificationDetails = new NotificationDetails(message,
                LocalDateTime.now(),
                commonUser.getUserDetails().getEmail());

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
