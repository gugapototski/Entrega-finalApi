package Pedido.PedidoAPI.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "produto_id", nullable = true)  // Temporariamente permitir nulo
    private Long produtoId;

    @Column(name = "quantidade", nullable = true)  // Temporariamente permitir nulo
    private Integer quantidade;

    @Column(name = "status")
    private String status;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
