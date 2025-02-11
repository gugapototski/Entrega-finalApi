package Venda.VendaAPI.clients;

import Venda.VendaAPI.dtos.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pedido-api", url = "http://localhost:8084/pedidos")
public interface PedidoClient {

    @GetMapping("/{id}")
    PedidoDTO buscarPedidoPorId(@PathVariable("id") Long id);

    @PutMapping("/{id}/status")
    void atualizarStatusPedido(@PathVariable("id") Long id, @RequestParam("novoStatus") String novoStatus);
}
