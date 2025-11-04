package br.com.fastDelivery.web.domain.service;

import br.com.fastDelivery.web.domain.dto.autenticacao.DadosAutenticacao;
import br.com.fastDelivery.web.domain.dto.autenticacao.DadosRefreshToken;
import br.com.fastDelivery.web.domain.dto.autenticacao.DadosTokenJWT;
import br.com.fastDelivery.web.domain.repository.UsuarioRepository;
import br.com.fastDelivery.web.domain.usuario.Usuario;
import br.com.fastDelivery.web.infra.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;


    public AutenticacaoService(TokenService tokenService, AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public DadosTokenJWT autenticar(DadosAutenticacao dadosAutenticacao){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.email(), dadosAutenticacao.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        var refreshToken = tokenService.refreshToken((Usuario) authentication.getPrincipal());
        return new DadosTokenJWT(tokenJWT, refreshToken);
    }

    public DadosTokenJWT atualizarToken(DadosRefreshToken dadosRefreshToken){
        var refreshToken = dadosRefreshToken.refreshToken();
        var email = tokenService.getSubject(refreshToken);
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        var acessoToken = tokenService.gerarToken(usuario);
        var tokenAtualizado = tokenService.refreshToken(usuario);
        return new DadosTokenJWT(acessoToken, tokenAtualizado);
    }


}
