package br.com.fastDelivery.web.controller;

import br.com.fastDelivery.web.domain.dto.restaurante.DadosAtualizacaoResturante;
import br.com.fastDelivery.web.domain.dto.restaurante.DadosCadastroRestaurante;
import br.com.fastDelivery.web.domain.dto.restaurante.DadosDetalhamentoRestaurante;
import br.com.fastDelivery.web.domain.dto.restaurante.DadosListagemRestaurante;
import br.com.fastDelivery.web.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping("/cadastrar")
    ResponseEntity<DadosDetalhamentoRestaurante> cadastroRestaurante(@RequestBody @Valid DadosCadastroRestaurante dadosCadastroRestaurante,
                                                                     UriComponentsBuilder uriComponentsBuilder){
        var restaurante = restauranteService.cadastrarRestaurante(dadosCadastroRestaurante);
        var uri = uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(restaurante.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRestaurante(restaurante));
    }

    @GetMapping("/pesquisa-restaurante")
    public ResponseEntity<Page<DadosListagemRestaurante>> listarRestaurantes(@RequestParam(required = false) String nome,
                                                                             @RequestParam(required = false) String categoria,
                                                                             @PageableDefault(size = 10, sort = {"nome"})
                                                                             Pageable paginacao){
        var pagina = restauranteService.listarRestaurantesCadastrados(categoria, nome, paginacao);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoRestaurante> pesquisaPeloId(@PathVariable Long id){
        var restaurante = restauranteService.pesquisaPorID(id);
        return ResponseEntity.ok(new DadosDetalhamentoRestaurante(restaurante));
    }

    @PatchMapping("atualizar-restaurante/{id}")
    public ResponseEntity<DadosDetalhamentoRestaurante> atualizarDadosRestaurante(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoResturante dadosAtualizacaoResturante){
        var restaurante = restauranteService.atualizarDadosRestaurante(id, dadosAtualizacaoResturante);
        return ResponseEntity.ok(new DadosDetalhamentoRestaurante(restaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarCadastro(@PathVariable Long id){
        restauranteService.inativarCadastroRestaurante(id);
        return ResponseEntity.noContent().build();
    }



}
