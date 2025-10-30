package br.com.fastDelivery.web.domain.repository;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
