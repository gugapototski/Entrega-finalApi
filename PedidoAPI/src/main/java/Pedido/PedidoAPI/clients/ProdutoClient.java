package Pedido.PedidoAPI.clients;  // Certifique-se de que o pacote está correto

import Pedido.PedidoAPI.dtos.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// Definindo o Feign Client para o ProdutoAPI
@FeignClient(name = "produto-api", url = "http://localhost:8083/produtos")
public interface ProdutoClient {

    // Buscar o produto pelo ID
    @GetMapping("/{id}")
    ProdutoDTO buscarProdutoPorId(@PathVariable("id") Long id);

    // Atualizar o estoque do produto após o pedido
    @PutMapping("/{id}/atualizar-estoque")
    void atualizarEstoque(@PathVariable("id") Long id, @RequestParam("quantidadeVendida") Integer quantidadeVendida);

}
