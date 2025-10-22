package br.com.fastDelivery.web.domain.restaurante;

import br.com.fastDelivery.web.domain.dto.restaurante.DadosAtualizacaoResturante;
import br.com.fastDelivery.web.domain.dto.restaurante.DadosCadastroRestaurante;
import br.com.fastDelivery.web.domain.endereco.Endereco;
import br.com.fastDelivery.web.domain.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Restaurante")
@Table(name = "restaurantes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(nullable = false, name = "hora_abertura")
    private LocalTime horaAbertura;

    @Column(nullable = false, name = "hora_fechamento")
    private LocalTime horaFechamento;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false, name = "pedido_minimo")
    private BigDecimal pedidoMinimo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();

    @Column(nullable = false)
    private boolean ativo;


    public Restaurante(DadosCadastroRestaurante dadosCadastroRestaurante) {
        this.nome = dadosCadastroRestaurante.nome();
        this.categoria = dadosCadastroRestaurante.categoria();
        this.endereco = dadosCadastroRestaurante.endereco();
        this.horaAbertura = dadosCadastroRestaurante.horaAbertura();
        this.horaFechamento = dadosCadastroRestaurante.horaFechamento();
        this.pedidoMinimo = dadosCadastroRestaurante.pedidoMinimo();
        this.ativo = true;
    }

    public void atualizarDados(DadosAtualizacaoResturante dadosAtualizacaoResturante) {
        this.nome = dadosAtualizacaoResturante.nome();
        this.categoria = dadosAtualizacaoResturante.categoria();
        this.endereco = dadosAtualizacaoResturante.endereco();
        this.horaAbertura = dadosAtualizacaoResturante.horaAbertura();
        this.horaFechamento = dadosAtualizacaoResturante.horaFechamento();
    }

    public void inativarCadastro() {
        this.ativo = false;
    }
}
