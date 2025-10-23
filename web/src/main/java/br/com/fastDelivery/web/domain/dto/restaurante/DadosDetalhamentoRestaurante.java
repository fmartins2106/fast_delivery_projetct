package br.com.fastDelivery.web.domain.dto.restaurante;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.restaurante.Categoria;
import br.com.fastDelivery.web.domain.restaurante.Restaurante;

import java.math.BigDecimal;
import java.time.LocalTime;

public record DadosDetalhamentoRestaurante(
        Long id,
        String nome,
        Categoria categoria,
        String cnpj,
        Endereco endereco,
        LocalTime horaAbertura,
        LocalTime horaFechamento,
        BigDecimal pedidoMinimo) {

    public DadosDetalhamentoRestaurante(Restaurante restaurante) {
        this(restaurante.getId(), restaurante.getNome(), restaurante.getCategoria(),restaurante.getCnpj(),
                restaurante.getEndereco(), restaurante.getHoraAbertura(), restaurante.getHoraFechamento(),
                restaurante.getPedidoMinimo());
    }
}
