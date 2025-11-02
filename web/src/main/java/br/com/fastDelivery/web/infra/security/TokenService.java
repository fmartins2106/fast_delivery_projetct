package br.com.fastDelivery.web.infra.security;

import br.com.fastDelivery.web.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TokenService {

    @Value("${token.api.authentication.secret}")
    private String secret;

    private String gerarToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.create()
                    .withIssuer("API FAST_DELIVEY")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(tempoExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token de acesso.", exception);
        }
    }


    private String refreshToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.create()
                    .withIssuer("API FAST_DELIVEY")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(tempoExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token de acesso.", exception);
        }
    }

    private String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API FAST_DELIVEY")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Login ou senha inválidos.", exception);
        }
    }

    private Instant tempoExpiracao() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .plusHours(6)
                .toInstant();
    }


}
