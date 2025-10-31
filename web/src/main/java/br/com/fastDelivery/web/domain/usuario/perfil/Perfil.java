package br.com.fastDelivery.web.domain.usuario.perfil;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "Perfil")
@Table(name = "perfis")
@EqualsAndHashCode(of = "id")
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PerfilNome perfilNome;

    @Override
    public String getAuthority() {
        return "ROLE_" + perfilNome;
    }
}
