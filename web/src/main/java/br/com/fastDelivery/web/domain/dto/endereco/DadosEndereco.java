package br.com.fastDelivery.web.domain.dto.endereco;

public record DadosEndereco(
        String logradouro,
        String endereco,
        String numero,
        String bairro,
        String cidade,
        String cep,
        String complemento,
        String uf) {
}
