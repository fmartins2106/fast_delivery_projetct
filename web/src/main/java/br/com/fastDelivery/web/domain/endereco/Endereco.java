package br.com.fastDelivery.web.domain.endereco;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Endereco")
@Table(name = "enderecos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String logradouro;

    @NotNull
    private String endereco;
    private String numero;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String cep;
    private String complemento;

    @NotNull
    private String uf;
}
