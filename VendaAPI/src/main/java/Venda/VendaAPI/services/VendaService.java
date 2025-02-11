package Venda.VendaAPI.services;

import Venda.VendaAPI.clients.PedidoClient;
import Venda.VendaAPI.clients.ProdutoClient;
import Venda.VendaAPI.dtos.PedidoDTO;
import Venda.VendaAPI.models.Venda;
import Venda.VendaAPI.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private PedidoClient pedidoClient;  // Feign Client para PedidoAPI

    @Autowired
    private ProdutoClient produtoClient;  // Feign Client para ProdutoAPI

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    public Venda buscarVendaPorId(Long id) {
        return vendaRepository.findById(id).orElse(null);
    }

    public Venda criarVenda(Venda venda) {
        // Verificar se o pedido existe no PedidoAPI
        PedidoDTO pedido = pedidoClient.buscarPedidoPorId(venda.getIdPedido());

        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado. Não é possível concluir a venda.");
        }

        if (!pedido.getStatus().equalsIgnoreCase("Pendente")) {
            throw new RuntimeException("O pedido não está disponível para venda. Status atual: " + pedido.getStatus());
        }

        // Atualiza o status do pedido para "Finalizado"
        pedidoClient.atualizarStatusPedido(pedido.getId(), "Finalizado");

        venda.setDtHora(java.time.LocalDateTime.now());
        venda.setDsStatus("Concluída");
        return vendaRepository.save(venda);
    }

    public void deletarVenda(Long id) {
        Venda venda = buscarVendaPorId(id);
        if (venda == null) {
            throw new RuntimeException("Venda não encontrada.");
        }

        // Buscar o pedido relacionado à venda no PedidoAPI
        PedidoDTO pedido = pedidoClient.buscarPedidoPorId(venda.getIdPedido());

        if (pedido != null && pedido.getStatus().equalsIgnoreCase("Finalizado")) {
            // Atualiza o status do pedido para "Cancelado"
            pedidoClient.atualizarStatusPedido(pedido.getId(), "Cancelado");

            // Devolver o estoque no ProdutoAPI
            produtoClient.devolverEstoque(pedido.getProdutoId(), pedido.getQuantidade());
        }

        // Excluir a venda
        vendaRepository.deleteById(id);
    }
}
