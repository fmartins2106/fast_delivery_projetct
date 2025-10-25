package br.com.fastDelivery.web.domain.dto.produto;

import br.com.fastDelivery.web.domain.produto.Produto;

import java.math.BigDecimal;

public record DadosListagemProduto(
        String descricao,
        BigDecimal preco) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getDescricao(), produto.getPreco());
    }
}
