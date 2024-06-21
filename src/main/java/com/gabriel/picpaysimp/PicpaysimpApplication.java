package com.gabriel.picpaysimp;

import com.gabriel.picpaysimp.domain.common.CommonUser;
import com.gabriel.picpaysimp.domain.retailer.Retailer;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.repository.CommonUserRepository;
import com.gabriel.picpaysimp.repository.RetailerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PicpaysimpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpaysimpApplication.class, args);
	}


	//Iniciando database com usuários padrão.
	@Bean
	CommandLineRunner initDefaultUsers(RetailerRepository retailerRepository, CommonUserRepository commonUserRepository){
		return args -> {
			User exampleUser1 = new User("12345678911", "example1@gmail.com");
			User exampleUser2 = new User("12314124331", "example2@gmail.com");
			User exampleUser3 = new User("23453234377", "example3@gmail.com");
			User exampleUser4 = new User("23453234388", "example4@gmail.com");

			Retailer exampleRetailer1 = new Retailer(100D, "exampleRetailer1", exampleUser1);
			Retailer exampleRetailer2 = new Retailer(100D, "exampleRetailer2", exampleUser2);

			CommonUser exampleCommonUser1 = new CommonUser(100D, "exampleCommonUser1", exampleUser3);
			CommonUser exampleCommonUser2 = new CommonUser(100D, "exampleCommonUser2", exampleUser4);

			retailerRepository.save(exampleRetailer1);
			retailerRepository.save(exampleRetailer2);

			commonUserRepository.save(exampleCommonUser1);
			commonUserRepository.save(exampleCommonUser2);
		};
	}
}
