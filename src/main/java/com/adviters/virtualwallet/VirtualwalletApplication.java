package com.adviters.virtualwallet;

import com.adviters.virtualwallet.model.Currency;
import com.adviters.virtualwallet.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VirtualwalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualwalletApplication.class, args);
	}

	//Se cargan en la base de datos los 2 tipos de monedas utilizados en la aplicacion
	@Bean
	CommandLineRunner start(CurrencyRepository currencyRepository){
		return args -> {
			Currency currencyUsd  = new Currency("USD");
			Currency currencyBtc  = new Currency("BTC");
			currencyRepository.save(currencyUsd);
			currencyRepository.save(currencyBtc);
		};
	}
}
