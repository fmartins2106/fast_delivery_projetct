package br.com.fastDelivery.web.domain.service.specification;

import br.com.fastDelivery.web.domain.restaurante.Restaurante;
import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecification {

    public static Specification<Restaurante> estaAtivo() {
        return (root, query, builder) ->
                builder.isTrue(root.get("ativo"));
    }

    public static Specification<Restaurante> categoria(String categoria) {
        return (root, query, builder)
                -> categoria == null ? null : builder.equal(root.get("categoria"), categoria);
    }


    public static Specification<Restaurante> nome(String nome) {
        return (root, query, builder)
                -> nome == null ? null : builder.equal(root.get("nome"), nome);
    }
}
