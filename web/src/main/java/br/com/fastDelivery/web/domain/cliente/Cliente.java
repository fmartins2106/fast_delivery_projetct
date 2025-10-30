package br.com.fastDelivery.web.domain.cliente;

import br.com.fastDelivery.web.domain.dto.cliente.DadosAtualizacaoCliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosCadastroCliente;
import br.com.fastDelivery.web.domain.dto.endereco.DadosAtualizacaoEnderecoCliente;
import br.com.fastDelivery.web.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @NotNull
    private String telefone;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @NotNull
    private boolean ativo;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cliente",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente(DadosCadastroCliente dadosCadastroCliente) {
        this.nomeCompleto = dadosCadastroCliente.nomeCompleto();
        this.telefone = dadosCadastroCliente.telefone();
        this.cpf = dadosCadastroCliente.cpf();
        this.ativo = true;
    }

    public void atualizarDadosCliente(DadosAtualizacaoCliente dados) {
        // Atualiza dados básicos
        if (dados.nomeCompleto() != null) {
            this.nomeCompleto = dados.nomeCompleto();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }

//        // Atualiza endereço existente pelo tipo
//        if (dados.endereco() != null && dados.tipoEndereco() != null) {
//            this.enderecos.stream()
//                    .filter(e -> e.getTipoEndereco().equalsIgnoreCase(dados.tipoEndereco()))
//                    .findFirst()
//                    .ifPresent(e -> e.atualizarDados(dados.endereco()));
//            // se não encontrar, simplesmente não faz nada
//        }
    }



}
