package br.com.fastDelivery.web.domain.dto.endereco;

import br.com.fastDelivery.web.domain.endereco.Endereco;

public record DadosDetalhamentoEndereco(
        String logradouro,
        String endereco,
        String numero,
        String bairro,
        String cidade,
        String cep,
        String complemento,
        String tipoEndereco,
        String uf) {

    public DadosDetalhamentoEndereco(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getEndereco(), endereco.getNumero(), endereco.getBairro(),
                endereco.getCidade(), endereco.getCep(), endereco.getComplemento(), endereco.getTipoEndereco(),
                endereco.getUf());
    }
}
