package br.com.fastDelivery.web.domain.repository.restaurante;

import br.com.fastDelivery.web.domain.restaurante.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante> {

}
