package br.com.fastDelivery.web.domain.usuario;

import br.com.fastDelivery.web.domain.dto.usuario.DadosAlteracaoSenha;
import br.com.fastDelivery.web.domain.dto.usuario.DadosAtualizacaoUsuario;
import br.com.fastDelivery.web.domain.dto.usuario.DadosCadastroUsuario;
import br.com.fastDelivery.web.domain.usuario.perfil.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nomeCompleto;

    @NotNull
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private String token;

    @NotNull
    private boolean verificado;

    @NotNull
    private LocalDateTime expiracaoToken;

    @NotNull
    private boolean ativo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "perfil_usuario",
    joinColumns = @JoinColumn(name = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfis = new ArrayList<>();


    public Usuario(DadosCadastroUsuario dadosCadastroUsuario, PasswordEncoder passwordEncoder, Perfil perfil) {
        this.nomeCompleto = dadosCadastroUsuario.nomeCompleto();
        this.email = dadosCadastroUsuario.email();
        this.senha = passwordEncoder.encode(dadosCadastroUsuario.senha());
        this.token = UUID.randomUUID().toString();
        this.expiracaoToken = LocalDateTime.now().plusMinutes(5);
        this.ativo = false;
        this.perfis.add(perfil);
    }

    public Usuario(DadosCadastroUsuario dadosCadastroUsuario, String senhaCriptografada, Perfil perfil) {
        this.nomeCompleto = dadosCadastroUsuario.nomeCompleto();
        this.senha = senhaCriptografada;
        this.perfis.add(perfil);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void alterarDadosUsuario(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        if (dadosAtualizacaoUsuario.nomeCompleto() != null){
            this.nomeCompleto = dadosAtualizacaoUsuario.nomeCompleto();
        }
        if (dadosAtualizacaoUsuario.email() != null){
            this.email = dadosAtualizacaoUsuario.email();
        }
    }

    public void verificadoTrue() {
        this.verificado = true;
    }


    public void alterarSenha(String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }
}
