package br.com.fastDelivery.web.domain.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizacaoProduto(
        @NotBlank(message = "Campo não pode ser vázio.")
        String descricao,

        @NotNull(message = "Campo não pode ser vázio.")
        BigDecimal preco) {
}
