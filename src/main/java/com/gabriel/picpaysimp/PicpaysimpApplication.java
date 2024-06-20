package com.gabriel.picpaysimp;

import com.gabriel.picpaysimp.domain.CommonUser;
import com.gabriel.picpaysimp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PicpaysimpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpaysimpApplication.class, args);
	}

	@Bean
	CommandLineRunner initDb(UserRepository userRepository){
		return args -> {

		};
	}
}
