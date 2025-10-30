package br.com.fastDelivery.web.controller;

import br.com.fastDelivery.web.domain.dto.endereco.DadosAtualizacaoEnderecoCliente;
import br.com.fastDelivery.web.domain.dto.endereco.DadosCadastroEndereco;
import br.com.fastDelivery.web.domain.dto.endereco.DadosDetalhamentoEndereco;
import br.com.fastDelivery.web.domain.dto.endereco.DadosListagemEndereco;
import br.com.fastDelivery.web.domain.service.EnderecoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/cadastrar/{id}")
    public ResponseEntity<DadosDetalhamentoEndereco> cadastrarEnderecoCliente(@PathVariable Long id, @RequestBody @Valid DadosCadastroEndereco dadosCadastroEndereco,
                                                                          UriComponentsBuilder uriComponentsBuilder){
        var endereco = enderecoService.cadastroEndereco(id, dadosCadastroEndereco);
        var uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEndereco(endereco));
    }

    @GetMapping("/listar-enderecos")
    public ResponseEntity<Page<DadosListagemEndereco>> listarEnderecos(DadosListagemEndereco dadosListagemEndereco,
                                                                       @PageableDefault(size = 10, sort = {"id"})Pageable pageable){
        var pagina = enderecoService.listarEnderecosCliente(pageable);
        return ResponseEntity.ok(pagina);
    }

    @PatchMapping("/atualizar-endereco/{id}")
    public ResponseEntity<DadosDetalhamentoEndereco> atualizarDadosEndereco(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoEnderecoCliente dadosAtualizacaoEnderecoCliente){
        var endereco = enderecoService.atualizarDadosEndereco(id, dadosAtualizacaoEnderecoCliente);
        return ResponseEntity.ok(new DadosDetalhamentoEndereco(endereco));
    }

    @DeleteMapping("/excluir-endereco/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id){
        enderecoService.excluirEnderecoCliente(id);
        return ResponseEntity.noContent().build();
    }

}
