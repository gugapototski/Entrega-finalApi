package Venda.VendaAPI.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "produto-api", url = "http://localhost:8083/produtos")
public interface ProdutoClient {

    @PutMapping("/{id}/devolver-estoque")
    void devolverEstoque(@PathVariable("id") Long idProduto, @RequestParam("quantidade") Integer quantidade);
}
