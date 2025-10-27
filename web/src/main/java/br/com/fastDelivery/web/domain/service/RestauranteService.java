package br.com.fastDelivery.web.domain.service;

import br.com.fastDelivery.web.domain.dto.restaurante.DadosAtualizacaoResturante;
import br.com.fastDelivery.web.domain.dto.restaurante.DadosCadastroRestaurante;
import br.com.fastDelivery.web.domain.dto.restaurante.DadosListagemRestaurante;
import br.com.fastDelivery.web.domain.repository.RestauranteRepository;
import br.com.fastDelivery.web.domain.restaurante.Restaurante;
import br.com.fastDelivery.web.domain.service.specification.RestauranteSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante pesquisaPorID(Long id){
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado."));
    }

    @Transactional
    public Restaurante cadastrarRestaurante(DadosCadastroRestaurante dadosCadastroRestaurante){
        var restaurante = new Restaurante(dadosCadastroRestaurante);
        restauranteRepository.save(restaurante);
        return restaurante;
    }

    public Page<DadosListagemRestaurante> listarRestaurantesCadastrados(String categoria, String nome, Pageable paginacao){
        Specification<Restaurante> specification = Specification.where(RestauranteSpecification.estaAtivo())
                .and(RestauranteSpecification.categoria(categoria))
                .and(RestauranteSpecification.nome(nome));

        Page<Restaurante> restaurantes = restauranteRepository.findAll(specification, paginacao);
        return restaurantes.map(DadosListagemRestaurante::new);
    }

    @Transactional
    public Restaurante atualizarDadosRestaurante(Long id, DadosAtualizacaoResturante dadosAtualizacaoResturante){
        var restaurante = pesquisaPorID(id);
        restaurante.atualizarDados(dadosAtualizacaoResturante);
        restauranteRepository.save(restaurante);
        return restaurante;
    }

    @Transactional
    public void inativarCadastroRestaurante(Long id){
        var restaurante = pesquisaPorID(id);
        restaurante.inativarCadastro();
        restauranteRepository.save(restaurante);
    }



}
