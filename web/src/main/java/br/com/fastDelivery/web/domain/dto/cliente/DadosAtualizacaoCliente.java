package br.com.fastDelivery.web.domain.dto.cliente;

import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoCliente(
        @Pattern(regexp = "\\p{L}+(\\s\\p{L}+)+")
        String nomeCompleto,

        String telefone,

        @Pattern(regexp = "(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})",
                message = "CPF inválido. Use apenas números ou o formato 000.000.000-00")
        String cpf) {
}
