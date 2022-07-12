package com.protasenya.cryptoCurrencyWatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CryptocurrencyWatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyWatcherApplication.class, args);
	}

}
