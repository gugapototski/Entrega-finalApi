package Venda.VendaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "Venda.VendaAPI.clients")
public class VendaApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VendaApiApplication.class, args);
	}
}
