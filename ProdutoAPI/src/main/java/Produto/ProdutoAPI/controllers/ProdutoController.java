package Produto.ProdutoAPI.controllers;

import Produto.ProdutoAPI.dtos.ProdutoDTO;
import Produto.ProdutoAPI.models.Produto;
import Produto.ProdutoAPI.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoService.criarProduto(dtoParaProduto(produtoDTO));
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, dtoParaProduto(produtoDTO));
        return produtoAtualizado != null ? ResponseEntity.ok(produtoAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/atualizar-estoque")
    public ResponseEntity<Produto> atualizarEstoque(@PathVariable Long id, @RequestParam Integer quantidadeVendida) {
        Produto produto = produtoService.buscarProdutoPorId(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        if (produto.getQtdEstoque() < quantidadeVendida) {
            return ResponseEntity.badRequest().body(null); // Estoque insuficiente
        }

        // Atualiza a quantidade do estoque
        produto.setQtdEstoque(produto.getQtdEstoque() - quantidadeVendida);
        Produto produtoAtualizado = produtoService.criarProduto(produto);

        return ResponseEntity.ok(produtoAtualizado);
    }

    @PutMapping("/{id}/devolver-estoque")
    public ResponseEntity<Produto> devolverEstoque(@PathVariable Long id, @RequestParam Integer quantidade) {
        Produto produto = produtoService.buscarProdutoPorId(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        // Devolver a quantidade ao estoque
        produto.setQtdEstoque(produto.getQtdEstoque() + quantidade);
        Produto produtoAtualizado = produtoService.criarProduto(produto);

        return ResponseEntity.ok(produtoAtualizado);
    }


    // Método para converter ProdutoDTO em Produto
    private Produto dtoParaProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQtdEstoque(produtoDTO.getQtdEstoque());
        produto.setDsEstoque(produtoDTO.getDsEstoque());
        produto.setVlProduto(produtoDTO.getVlProduto());  // BigDecimal mantido aqui
        return produto;
    }
}
