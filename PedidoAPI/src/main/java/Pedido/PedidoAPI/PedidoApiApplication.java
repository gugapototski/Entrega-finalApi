package Pedido.PedidoAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "Pedido.PedidoAPI.clients")  // Habilita o Feign Client e garante o escaneamento correto do pacote
public class PedidoApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PedidoApiApplication.class, args);
	}
}
