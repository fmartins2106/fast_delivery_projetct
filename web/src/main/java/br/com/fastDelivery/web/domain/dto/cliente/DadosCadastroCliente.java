package br.com.fastDelivery.web.domain.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCliente(
        @NotBlank(message = "Digite o seu nome completo")
        @Pattern(regexp = "\\p{L}+(\\s\\p{L}+)+")
        String nomeCompleto,

        @NotBlank(message = "Digite o seu telefone.")
        String telefone,

        @NotBlank(message = "Digite o seu cpf")
        @Pattern(regexp = "(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})",
                message = "CPF inválido. Use apenas números ou o formato 000.000.000-00")
        String cpf) {


}
