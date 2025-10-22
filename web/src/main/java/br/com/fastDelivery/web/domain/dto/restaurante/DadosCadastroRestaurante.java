package br.com.fastDelivery.web.domain.dto.restaurante;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.restaurante.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalTime;

public record DadosCadastroRestaurante(
        @NotBlank(message = "Campo não pode ser vázio.")
        String nome,

        @NotNull(message = "Campo não pode ser vázio.")
        Categoria categoria,

        @NotBlank(message = "Campo CNPJ não pode ser vázio")
        @Pattern(regexp = "^\\d{14}$", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
        String cnpj,

        @NotBlank(message = "Campo não pode ser vázio.")
        Endereco endereco,

        @NotNull(message = "Campo não pode ser vázio.")
        LocalTime horaAbertura,

        @NotBlank(message = "Campo não pode ser vázio")
        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        String telefone,

        @NotNull(message = "Campo não pode ser vázio.")
        LocalTime horaFechamento,

        @NotNull(message = "Campo não pode ser vázio.")
        BigDecimal pedidoMinimo) {
}
