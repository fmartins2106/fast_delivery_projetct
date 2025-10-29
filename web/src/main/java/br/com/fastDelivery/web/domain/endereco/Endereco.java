package br.com.fastDelivery.web.domain.endereco;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.dto.endereco.DadosEndereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;

    public Endereco(DadosEndereco dadosEndereco, Cliente cliente) {
        this.logradouro = dadosEndereco.logradouro();
        this.endereco = dadosEndereco.endereco();
        this.numero = dadosEndereco.numero();
        this.bairro = dadosEndereco.bairro();
        this.cidade = dadosEndereco.cidade();
        this.cep = dadosEndereco.cep();
        this.complemento = dadosEndereco.complemento();
        this.uf = dadosEndereco.uf();
        this.tipoEndereco = dadosEndereco.tipoEndereco();
        this.cliente = cliente;
    }


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
        if (this.cliente != null){
            this.cliente = novoEndereco.getCliente();
        }
    }

}
