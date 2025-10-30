package br.com.fastDelivery.web.domain.dto.endereco;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEndereco(
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
