package br.com.fastDelivery.web.domain.repository;

import br.com.fastDelivery.web.domain.usuario.perfil.Perfil;
import br.com.fastDelivery.web.domain.usuario.perfil.PerfilNome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByEmail(PerfilNome perfilNome);
}
