package com.gabriel.picpaysimp;

import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.domain.user.UserType;
import com.gabriel.picpaysimp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class PicpaysimpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpaysimpApplication.class, args);
	}


	//Iniciando database com usuários padrão.
	@Bean
	CommandLineRunner initDefaultUsers(UserRepository userRepository){
		return args -> {
			BigDecimal defaultMoney = new BigDecimal(100);

			User exampleRetailer1 = new User(defaultMoney, "example1@gmail.com","23453234388" , "exampleRetailer1", UserType.RETAILER);
			User exampleRetailer2 = new User(defaultMoney, "example2@gmail.com","23453234366" , "exampleRetailer2", UserType.RETAILER);
			User exampleCommon1 = new User(defaultMoney, "example3@gmail.com","23453234399" , "exampleCommon1", UserType.COMMON);
			User exampleCommon2 = new User(defaultMoney, "example4@gmail.com","23453234311" , "exampleCommon1", UserType.COMMON);


			userRepository.save(exampleRetailer1);
			userRepository.save(exampleRetailer2);

			userRepository.save(exampleCommon1);
			userRepository.save(exampleCommon2);
		};
	}
}
