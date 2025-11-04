package br.com.fastDelivery.web.controller;

import br.com.fastDelivery.web.domain.dto.autenticacao.DadosAutenticacao;
import br.com.fastDelivery.web.domain.dto.autenticacao.DadosRefreshToken;
import br.com.fastDelivery.web.domain.dto.autenticacao.DadosTokenJWT;
import br.com.fastDelivery.web.domain.repository.UsuarioRepository;
import br.com.fastDelivery.web.domain.service.AutenticacaoService;
import br.com.fastDelivery.web.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final TokenService tokenService;
    private final AutenticacaoService autenticacaoService;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoController(TokenService tokenService, AutenticacaoService autenticacaoService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.autenticacaoService = autenticacaoService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dadosAutenticacao){
        var dadosToken = autenticacaoService.autenticar(dadosAutenticacao);
        return ResponseEntity.ok(dadosToken);
    }

    @PostMapping("/atualizar-token")
    public ResponseEntity<DadosTokenJWT> atualizarToken(@RequestBody @Valid DadosRefreshToken dadosRefreshToken){
        var refreshToken = autenticacaoService.atualizarToken(dadosRefreshToken);
        return ResponseEntity.ok(refreshToken);
    }


}
