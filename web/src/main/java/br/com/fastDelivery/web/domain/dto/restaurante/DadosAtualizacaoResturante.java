package br.com.fastDelivery.web.domain.dto.restaurante;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.restaurante.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalTime;

public record DadosAtualizacaoResturante(

        String nome,
        Categoria categoria,
        Endereco endereco,
        LocalTime horaAbertura,
        LocalTime horaFechamento,

        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        String telefone,
        BigDecimal pedidoMinimo) {
}
