package br.com.fastDelivery.web.domain.dto.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoEnderecoCliente(
        @NotBlank(message = "Campo logradouro não pode ser vázio.")
        String logradouro,

        @NotBlank(message = "Campo endereço não pode ser vázio.")
        String endereco,
        String numero,

        @NotBlank(message = "Campo bairro não pode ser vázio.")
        String bairro,

        @NotBlank(message = "Campo cidade não pode ser vázio.")
        String cidade,

        @NotBlank(message = "Campo CEP não pode ser vázio.")
        String cep,
        String complemento,

        String tipoEndereco,

        @NotBlank(message = "Campo UF não pode ser vázio.")
        String uf) {
}
