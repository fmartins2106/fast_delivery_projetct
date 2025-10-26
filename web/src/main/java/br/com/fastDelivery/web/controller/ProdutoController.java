package br.com.fastDelivery.web.controller;

import br.com.fastDelivery.web.domain.dto.produto.DadosAtualizacaoProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosCadastroProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosDetalhamentoProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosListagemProduto;
import br.com.fastDelivery.web.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;


    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastrar/{idRestaurante}")
    public ResponseEntity<DadosDetalhamentoProduto> cadastrarProduto(@PathVariable Long idRestaurante, @RequestBody @Valid DadosCadastroProduto dadosCadastroProduto,
                                                                     UriComponentsBuilder uriComponentsBuilder){
        var produto = produtoService.cadastroProduto(idRestaurante, dadosCadastroProduto);
        var uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping("/listar-produtos")
    public ResponseEntity<Page<DadosListagemProduto>> listarProdutosCadastrados(@PageableDefault(size = 10, sort = {"descricao"})
                                                                                Pageable pageable){
        var pagina = produtoService.listarProdutos(pageable);
        return ResponseEntity.ok(pagina);
    }

    @PatchMapping("/atualizar-produto/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> atualizarProduto(@PathVariable  Long id, @RequestBody @Valid DadosAtualizacaoProduto dadosAtualizacaoProduto){
        var produto = produtoService.atualizarDadosProduto(id, dadosAtualizacaoProduto);
        return ResponseEntity.ok().body(new DadosDetalhamentoProduto(produto));
    }

    @DeleteMapping("/excluir-produto/{id}")
    public ResponseEntity<Void> excluirDadosProduto(@PathVariable Long id){
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> encontrarPorID(@PathVariable Long id){
        var produto = produtoService.encontrarPorID(id);
        produtoService.excluirProduto(id);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

}
