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
    @Column(name = "tipo_endereco")
    private String tipoEndereco;

    @NotNull
    private String uf;


    public void atualizarDados(Endereco novoEndereco) {
        this.logradouro = novoEndereco.getLogradouro();
        this.endereco = novoEndereco.getEndereco();
        this.numero = novoEndereco.getNumero();
        this.bairro = novoEndereco.getBairro();
        this.cidade = novoEndereco.getCidade();
        this.cep = novoEndereco.getCep();
        this.complemento = novoEndereco.getComplemento();
        this.tipoEndereco = novoEndereco.getTipoEndereco();
        this.uf = novoEndereco.getUf();
    }

}
