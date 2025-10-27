package br.com.fastDelivery.web.domain.dto.cliente;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCliente(
        @NotBlank(message = "Digite o seu nome completo")
        @Pattern(regexp = "\\p{L}+(\\s\\p{L}+)+")
        String nomeCompleto,

        @NotBlank(message = "Digite o seu telefone.")
        String telefone,

        @NotBlank(message = "Digite o seu cpf")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
        String cpf,

        @NotNull
        @Valid
        Endereco endereco) {


}
