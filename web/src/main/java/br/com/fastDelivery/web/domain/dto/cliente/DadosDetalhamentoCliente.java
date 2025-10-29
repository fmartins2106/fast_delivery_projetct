package br.com.fastDelivery.web.domain.dto.cliente;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.endereco.Endereco;

import java.util.List;

public record DadosDetalhamentoCliente(
        Long id,
        String nomeCompleto,
        String telefone,
        String cpf,
        List<Endereco> enderecos) {


    public DadosDetalhamentoCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNomeCompleto(), cliente.getTelefone(),
                cliente.getCpf(), cliente.getEnderecos());
    }
}
