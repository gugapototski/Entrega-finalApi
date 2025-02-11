package Pedido.PedidoAPI.services;

import Pedido.PedidoAPI.clients.ProdutoClient;
import Pedido.PedidoAPI.dtos.ProdutoDTO;
import Pedido.PedidoAPI.models.Pedido;
import Pedido.PedidoAPI.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoClient produtoClient;  // Feign Client para acessar o ProdutoAPI

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido criarPedido(Pedido pedido) {
        // Buscar o produto no ProdutoAPI
        ProdutoDTO produto = produtoClient.buscarProdutoPorId(pedido.getProdutoId());

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado.");
        }

        // Verificar se há estoque suficiente
        if (produto.getQtdEstoque() < pedido.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getDescricao());
        }

        // Atualizar o estoque no ProdutoAPI apenas uma vez
        produtoClient.atualizarEstoque(pedido.getProdutoId(), pedido.getQuantidade());

        // Salvar o pedido após a confirmação do estoque
        Pedido novoPedido = pedidoRepository.save(pedido);

        return novoPedido;
    }


    public Pedido atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = buscarPedidoPorId(id);
        if (pedido != null) {
            pedido.setStatus(novoStatus);
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
