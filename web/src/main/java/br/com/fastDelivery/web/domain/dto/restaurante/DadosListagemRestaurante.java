package br.com.fastDelivery.web.domain.dto.restaurante;

import br.com.fastDelivery.web.domain.dto.endereco.DadosEndereco;
import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.restaurante.Categoria;
import br.com.fastDelivery.web.domain.restaurante.Restaurante;

import java.math.BigDecimal;
import java.time.LocalTime;

public record DadosListagemRestaurante(
        String nome,
        Categoria categoria,
        DadosEndereco endereco,
        LocalTime horaAbertura,
        LocalTime horaFechamento,
        BigDecimal pedidoMinimo) {

    public DadosListagemRestaurante(Restaurante restaurante) {
        this(restaurante.getNome(), restaurante.getCategoria(),restaurante.getEndereco() != null
                        ? new DadosEndereco(
                        restaurante.getEndereco().getLogradouro(),
                        restaurante.getEndereco().getEndereco(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getBairro(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getCep(),
                        restaurante.getEndereco().getComplemento(),
                        restaurante.getEndereco().getUf(),
                        restaurante.getEndereco().getTipoEndereco(),
                        restaurante.getEndereco().getCliente()
                ) : null
                , restaurante.getHoraAbertura(),
                restaurante.getHoraFechamento(), restaurante.getPedidoMinimo());
    }
}
