package br.com.fastDelivery.web.domain.dto.endereco;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import jakarta.validation.constraints.NotNull;

public record DadosEndereco(
        String logradouro,
        String endereco,
        String numero,
        String bairro,
        String cidade,
        String cep,
        String complemento,
        String uf,
        String tipoEndereco,
        Cliente cliente) {

}
