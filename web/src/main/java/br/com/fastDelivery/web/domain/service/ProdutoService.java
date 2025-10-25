package br.com.fastDelivery.web.domain.service;

import br.com.fastDelivery.web.domain.dto.produto.DadosAtualizacaoProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosCadastroProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosListagemProduto;
import br.com.fastDelivery.web.domain.dto.restaurante.DadosAtualizacaoResturante;
import br.com.fastDelivery.web.domain.produto.Produto;
import br.com.fastDelivery.web.domain.repository.produto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    public final ProdutoRepository produtoRepository;


    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto encontrarPorID(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado."));
    }


    @Transactional
    public Produto cadastroProduto(DadosCadastroProduto dadosCadastroProduto){
        var produto = new Produto(dadosCadastroProduto);
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

