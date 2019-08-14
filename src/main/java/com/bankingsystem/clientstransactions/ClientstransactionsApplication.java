package com.bankingsystem.clientstransactions;

import com.bankingsystem.clientstransactions.controller.AccountController;
import com.bankingsystem.clientstransactions.controller.ClientController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class ClientstransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientstransactionsApplication.class, args);
	}

}
