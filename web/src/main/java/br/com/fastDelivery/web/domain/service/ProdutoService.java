package br.com.fastDelivery.web.domain.service;

import br.com.fastDelivery.web.domain.dto.produto.DadosAtualizacaoProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosCadastroProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosListagemProduto;
import br.com.fastDelivery.web.domain.produto.Produto;
import br.com.fastDelivery.web.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public final ProdutoRepository produtoRepository;
    private final RestauranteService restauranteService;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteService restauranteService) {
        this.produtoRepository = produtoRepository;
        this.restauranteService = restauranteService;
    }

    @Transactional
    public Produto encontrarPorID(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado."));
    }


    @Transactional
    public Produto cadastroProduto(Long idRestaurante, DadosCadastroProduto dadosCadastroProduto){
        var restaurante = restauranteService.pesquisaPorID(idRestaurante);
        var produto = new Produto(restaurante, dadosCadastroProduto);
        produtoRepository.save(produto);
        return produto;
    }

    public Page<DadosListagemProduto> listarProdutos(Pageable paginacao){
        return produtoRepository.findAll(paginacao).map(DadosListagemProduto::new);
    }

    @Transactional
    public Produto atualizarDadosProduto(Long id, DadosAtualizacaoProduto dadosAtualizacaoProduto){
        var produto = encontrarPorID(id);
        produto.atualizarDados(dadosAtualizacaoProduto);
        produtoRepository.save(produto);
        return produto;
    }

    @Transactional
    public void excluirProduto(Long id){
        var produto = encontrarPorID(id);
        produtoRepository.delete(produto);
    }

}

