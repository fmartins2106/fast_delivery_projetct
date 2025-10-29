package br.com.fastDelivery.web.domain.dto.cliente;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.endereco.Endereco;

import java.util.List;

public record DadosListagemClientes(
        Long id,
        String nomeCompleto,
        String telefone,
        String cpf,
        List<Endereco> enderecos) {

    public DadosListagemClientes(Cliente cliente){
        this(cliente.getId(), cliente.getNomeCompleto(), cliente.getTelefone(),
                cliente.getCpf(), cliente.getEnderecos());
    }
}
