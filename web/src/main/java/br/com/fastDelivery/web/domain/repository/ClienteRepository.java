package br.com.fastDelivery.web.domain.repository;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
