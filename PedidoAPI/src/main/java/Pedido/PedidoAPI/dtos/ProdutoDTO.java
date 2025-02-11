package Pedido.PedidoAPI.dtos;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long idProduto;
    private String descricao;
    private Integer qtdEstoque;
    private String dsEstoque;
    private BigDecimal vlProduto;

    // Getters e Setters
    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQtdEstoque() { return qtdEstoque; }
    public void setQtdEstoque(Integer qtdEstoque) { this.qtdEstoque = qtdEstoque; }

    public String getDsEstoque() { return dsEstoque; }
    public void setDsEstoque(String dsEstoque) { this.dsEstoque = dsEstoque; }

    public BigDecimal getVlProduto() { return vlProduto; }
    public void setVlProduto(BigDecimal vlProduto) { this.vlProduto = vlProduto; }
}
