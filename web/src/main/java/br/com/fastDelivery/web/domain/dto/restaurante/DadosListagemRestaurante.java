package br.com.fastDelivery.web.domain.dto.restaurante;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.restaurante.Categoria;
import br.com.fastDelivery.web.domain.restaurante.Restaurante;

import java.math.BigDecimal;
import java.time.LocalTime;

public record DadosListagemRestaurante(
        String nome,
        Categoria categoria,
        Endereco endereco,
        LocalTime horaAbertura,
        LocalTime horaFechamento,
        BigDecimal pedidoMinimo) {

    public DadosListagemRestaurante(Restaurante restaurante) {
        this(restaurante.getNome(), restaurante.getCategoria() , restaurante.getEndereco(), restaurante.getHoraAbertura(),
                restaurante.getHoraFechamento(), restaurante.getPedidoMinimo());
    }
}
