package br.com.fastDelivery.web.controller;

import br.com.fastDelivery.web.domain.dto.cliente.DadosAtualizacaoCliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosCadastroCliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosDetalhamentoCliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosListagemClientes;
import br.com.fastDelivery.web.domain.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<DadosDetalhamentoCliente> cadastroCliente(@RequestBody @Valid DadosCadastroCliente dadosCadastroCliente,
                                                                UriComponentsBuilder uriComponentsBuilder){
        var cliente = clienteService.cadastrarCliente(dadosCadastroCliente);
        var uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping("/listar-clientes")
    public ResponseEntity<Page> listarClientesCadastrados(DadosListagemClientes dadosListagemClientes,
                                                          @PageableDefault(size = 10, sort = {"nomeCompleto"})Pageable pageable){
        var page = clienteService.listarClientes(pageable);
        return ResponseEntity.ok(page);
    }

    @PatchMapping("/atualizar-dados-cliente/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> atualizarDadosCliente(Long id, @RequestBody @Valid DadosAtualizacaoCliente dadosAtualizacaoCliente){
        var cliente = clienteService.atualizarDadosCliente(id, dadosAtualizacaoCliente);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

}
