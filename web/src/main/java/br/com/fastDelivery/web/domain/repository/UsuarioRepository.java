package br.com.fastDelivery.web.domain.repository;


import br.com.fastDelivery.web.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
