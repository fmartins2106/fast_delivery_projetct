package br.com.fastDelivery.web.domain.endereco;

import br.com.fastDelivery.web.domain.cliente.Cliente;
import br.com.fastDelivery.web.domain.dto.endereco.DadosAtualizacaoEnderecoCliente;
import br.com.fastDelivery.web.domain.dto.endereco.DadosCadastroEndereco;
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


    public Endereco(Cliente cliente, DadosCadastroEndereco dadosCadastroEndereco) {
        this.logradouro = dadosCadastroEndereco.logradouro();
        this.endereco = dadosCadastroEndereco.endereco();
        this.numero = dadosCadastroEndereco.numero();
        this.bairro = dadosCadastroEndereco.bairro();
        this.cidade = dadosCadastroEndereco.cidade();
        this.complemento = dadosCadastroEndereco.complemento();
        this.uf = dadosCadastroEndereco.uf();
        this.tipoEndereco = dadosCadastroEndereco.tipoEndereco();
        this.cliente = cliente;
    }


    public void atualizarEndereco(DadosAtualizacaoEnderecoCliente dados) {
        if (dados.logradouro() != null && !dados.logradouro().isBlank()) {
            this.logradouro = dados.logradouro();
        }
        if (dados.endereco() != null && !dados.endereco().isBlank()) {
            this.endereco = dados.endereco();
        }
        if (dados.numero() != null && !dados.numero().isBlank()) {
            this.numero = dados.numero();
        }
        if (dados.bairro() != null && !dados.bairro().isBlank()) {
            this.bairro = dados.bairro();
        }
        if (dados.cidade() != null && !dados.cidade().isBlank()) {
            this.cidade = dados.cidade();
        }
        if (dados.cep() != null && !dados.cep().isBlank()) {
            this.cep = dados.cep();
        }
        if (dados.complemento() != null && !dados.complemento().isBlank()) {
            this.complemento = dados.complemento();
        }
        if (dados.tipoEndereco() != null && !dados.tipoEndereco().isBlank()) {
            this.tipoEndereco = dados.tipoEndereco();
        }
        if (dados.uf() != null && !dados.uf().isBlank()) {
            this.uf = dados.uf();
        }
    }


}
