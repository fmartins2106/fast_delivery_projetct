package br.com.fastDelivery.web.domain.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosRefreshToken(
        @NotBlank
        String refreshToken) {
}
