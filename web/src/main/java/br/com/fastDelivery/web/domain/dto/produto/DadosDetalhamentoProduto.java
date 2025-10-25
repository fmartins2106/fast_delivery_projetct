package br.com.fastDelivery.web.domain.dto.produto;

import br.com.fastDelivery.web.domain.produto.Produto;

import java.math.BigDecimal;

public record DadosDetalhamentoProduto(
        String descricao,
        BigDecimal preco) {

    public DadosDetalhamentoProduto(Produto produto){
        this(produto.getDescricao(), produto.getPreco());
    }
}
