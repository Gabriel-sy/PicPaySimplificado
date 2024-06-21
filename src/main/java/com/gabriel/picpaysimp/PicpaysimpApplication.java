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

			User exampleRetailer1 = new User(defaultMoney, "exampleRetailer1","23453234388" , "example1@gmail.com", UserType.RETAILER);
			User exampleRetailer2 = new User(defaultMoney, "exampleRetailer2","23453234366" , "example2@gmail.com", UserType.RETAILER);
			User exampleCommon1 = new User(defaultMoney, "exampleCommon1","23453234399" , "example3@gmail.com", UserType.COMMON);
			User exampleCommon2 = new User(defaultMoney, "exampleCommon2","23453234311" , "example4@gmail.com", UserType.COMMON);


			userRepository.save(exampleRetailer1);
			userRepository.save(exampleRetailer2);

			userRepository.save(exampleCommon1);
			userRepository.save(exampleCommon2);
		};
	}
}
