package br.com.fastDelivery.web.domain.dto.restaurante;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.restaurante.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalTime;

public record DadosAtualizacaoResturante(
        @NotBlank(message = "Campo não pode ser vázio.")
        String nome,

        @NotNull(message = "Campo não pode ser vázio.")
        Categoria categoria,

        @NotBlank(message = "Campo não pode ser vázio.")
        Endereco endereco,

        @NotNull(message = "Campo não pode ser vázio.")
        LocalTime horaAbertura,

        @NotNull(message = "Campo não pode ser vázio.")
        LocalTime horaFechamento,

        @NotNull(message = "Campo não pode ser vázio.")
        BigDecimal pedidoMinimo) {
}
