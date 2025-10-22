package br.com.fastDelivery.web.domain.dto.restaurante;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.restaurante.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

        @NotBlank(message = "Campo não pode ser vázio")
        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        String telefone,

        @NotNull(message = "Campo não pode ser vázio.")
        BigDecimal pedidoMinimo) {
}
