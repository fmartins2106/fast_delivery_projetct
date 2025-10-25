package br.com.fastDelivery.web.domain.repository.produto;

import br.com.fastDelivery.web.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
