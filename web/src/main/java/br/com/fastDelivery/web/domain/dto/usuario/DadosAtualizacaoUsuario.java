package br.com.fastDelivery.web.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuario(
        @NotBlank(message = "Campo não pode ser vázio.")
        @Pattern(regexp = "^[\\p{L}]+( [\\p{L}]+)+$")
        String nomeCompleto,

        @NotBlank(message = "Erro. Digite o email completo.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        String email) {
}
