package br.com.fastDelivery.web.domain.service;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.dto.endereco.DadosAtualizacaoEnderecoCliente;
import br.com.fastDelivery.web.domain.dto.endereco.DadosCadastroEndereco;
import br.com.fastDelivery.web.domain.dto.endereco.DadosListagemEndereco;
import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteService clienteService;

    public EnderecoService(EnderecoRepository enderecoRepository, ClienteService clienteService) {
        this.enderecoRepository = enderecoRepository;
        this.clienteService = clienteService;
    }

    @Transactional
    public Endereco cadastroEndereco(Long id, DadosCadastroEndereco dadosCadastroEndereco){
        var cliente = clienteService.pesquisaPorId(id);
        var endereco = new Endereco(cliente, dadosCadastroEndereco);
        enderecoRepository.save(endereco);
        return endereco;
    }

    public Page<DadosListagemEndereco> listarEnderecosCliente(Pageable pageable){
        return enderecoRepository.findAll(pageable).map(DadosListagemEndereco::new);
    }

    @Transactional
    public Endereco atualizarDadosEndereco(Long id, DadosAtualizacaoEnderecoCliente dadosAtualizacaoEnderecoCliente) {
        var endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com o ID: " + id));
        endereco.atualizarEndereco(dadosAtualizacaoEnderecoCliente); // chama o método na entidade
        return enderecoRepository.save(endereco); // persiste e retorna o endereço atualizado
    }

    @Transactional
    public void excluirEnderecoCliente(Long id){
        var endereco = enderecoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ID não encontrado."));
        enderecoRepository.delete(endereco);
    }





}
