package br.com.fastDelivery.web.domain.service;

import br.com.fastDelivery.web.domain.dto.usuario.DadosAlteracaoSenha;
import br.com.fastDelivery.web.domain.dto.usuario.DadosAtualizacaoUsuario;
import br.com.fastDelivery.web.domain.dto.usuario.DadosCadastroUsuario;
import br.com.fastDelivery.web.domain.repository.PerfilRepository;
import br.com.fastDelivery.web.domain.repository.UsuarioRepository;
import br.com.fastDelivery.web.domain.usuario.Usuario;
import br.com.fastDelivery.web.domain.usuario.perfil.Perfil;
import br.com.fastDelivery.web.domain.usuario.perfil.PerfilNome;
import br.com.fastDelivery.web.infra.email.EmailService;
import br.com.fastDelivery.web.infra.exception.ValidationRegraDeNegocio;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PerfilRepository perfilRepository;
    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, PerfilRepository perfilRepository, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.perfilRepository = perfilRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não foi encontrado."));
    }

    @Transactional
    public Usuario cadastroCliente(DadosCadastroUsuario dadosCadastroUsuario){
        var senhaCriptografada = passwordEncoder.encode(dadosCadastroUsuario.senha());
        var perfil = perfilRepository.findByEmail(PerfilNome.CLIENTE)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado."));
        var usuario = new Usuario(dadosCadastroUsuario, senhaCriptografada, perfil);
        usuarioRepository.save(usuario);
        emailService.enviarEmailVerificacao(usuario);
        return usuario;
    }

    @Transactional
    public Usuario cadastroRestaurante(DadosCadastroUsuario dadosCadastroUsuario){
        var senhaCriptografada = passwordEncoder.encode(dadosCadastroUsuario.senha());
        var perfil = perfilRepository.findByEmail(PerfilNome.RESTAURANTE)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado."));
        var usuario = new Usuario(dadosCadastroUsuario, senhaCriptografada, perfil);
        usuarioRepository.save(usuario);
        emailService.enviarEmailVerificacao(usuario);
        return usuario;
    }

    public Usuario pesquisaIdUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Transactional
    public Usuario alterarDadosUsuario(Long idUsuario, DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
        var usuario = pesquisaIdUsuario(idUsuario);
        usuario.alterarDadosUsuario(dadosAtualizacaoUsuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void verificarEmail(String codigo){
        var usuario = usuarioRepository.findByToken(codigo)
                .orElseThrow(() -> new RuntimeException("Token inválido ou não encontrado."));
        usuario.verificadoTrue();
    }

    @Transactional
    public void alterarSenha(Usuario usuario, DadosAlteracaoSenha dadosAlteracaoSenha){
        if (!passwordEncoder.matches(dadosAlteracaoSenha.senha(), usuario.getPassword())){
            throw new ValidationRegraDeNegocio("Senha digitada não confere com senha Atual.");
        }
        if (!dadosAlteracaoSenha.senha().equals(dadosAlteracaoSenha.confirmacaoNovaSenha())){
            throw new ValidationRegraDeNegocio("Senha e confirmação não conferem.");
        }
        String senhaCriptografada = passwordEncoder.encode(dadosAlteracaoSenha.senha());
        usuario.alterarSenha(senhaCriptografada);
        usuarioRepository.save(usuario);
    }





}
