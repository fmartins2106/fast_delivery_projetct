package br.com.fastDelivery.web.domain.service;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosAtualizacaoCliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosCadastroCliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosListagemClientes;
import br.com.fastDelivery.web.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente cadastrarCliente(DadosCadastroCliente dadosCadastroCliente){
        var cliente = new Cliente(dadosCadastroCliente);
        clienteRepository.save(cliente);
        return cliente;
    }

    public Page<DadosListagemClientes> listarClientes(Pageable pageable){
        return clienteRepository.findAll(pageable).map(DadosListagemClientes::new);
    }

    public Cliente pesquisaPorID(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado."));
    }

    @Transactional
    public Cliente atualizarDadosCliente(Long id, DadosAtualizacaoCliente dadosAtualizacaoCliente){
        var cliente = pesquisaPorID(id);
        cliente.atualizarDadosCliente(dadosAtualizacaoCliente);
        clienteRepository.save(cliente);
        return cliente;
    }




}
