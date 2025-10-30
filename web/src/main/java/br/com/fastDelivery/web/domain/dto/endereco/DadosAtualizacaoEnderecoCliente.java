package br.com.fastDelivery.web.domain.dto.endereco;

public record DadosAtualizacaoEnderecoCliente(
        String logradouro,
        String endereco,
        String numero,
        String bairro,
        String cidade,
        String cep,
        String complemento,
        String tipoEndereco,
        String uf) {
}
