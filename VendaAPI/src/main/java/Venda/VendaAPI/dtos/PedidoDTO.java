package Venda.VendaAPI.dtos;

public class PedidoDTO {

    private Long id;
    private Long produtoId;
    private Integer quantidade;
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
