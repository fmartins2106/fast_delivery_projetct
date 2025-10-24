package br.com.fastDelivery.web.domain.service.specification;

import br.com.fastDelivery.web.domain.restaurante.Restaurante;
import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecification {

    public static Specification<Restaurante> estaAtivo() {
        return (root, query, builder) ->
                builder.isTrue(root.get("ativo"));
    }

    public static Specification<Restaurante> categoria(String categoria) {
        return (root, query, builder) -> {
            if (categoria == null || categoria.isBlank()) return null;

            try {
                // converte a string da URL para o enum correspondente
                var categoriaEnum = Enum.valueOf(br.com.fastDelivery.web.domain.restaurante.Categoria.class, categoria.toUpperCase());
                return builder.equal(root.get("categoria"), categoriaEnum);
            } catch (IllegalArgumentException e) {
                // se o valor informado não existir no enum, retorna sem resultados
                return builder.disjunction();
            }
        };
    }


    public static Specification<Restaurante> nome(String nome) {
        return (root, query, builder) ->
                nome == null ? null :
                        builder.equal(root.get("nome"), nome);
    }
}
