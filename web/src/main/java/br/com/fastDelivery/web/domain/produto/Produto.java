package br.com.fastDelivery.web.domain.produto;

import br.com.fastDelivery.web.domain.dto.produto.DadosAtualizacaoProduto;
import br.com.fastDelivery.web.domain.dto.produto.DadosCadastroProduto;
import br.com.fastDelivery.web.domain.restaurante.Restaurante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Produto")
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id_restaurante")
    private Restaurante restaurante;


    public Produto(Restaurante restaurante, DadosCadastroProduto dadosCadastroProduto) {
        this.descricao = dadosCadastroProduto.descricao();
        this.preco = dadosCadastroProduto.preco();
        this.restaurante = restaurante;
    }

    public void atualizarDados(DadosAtualizacaoProduto dadosAtualizacaoProduto) {
        if (dadosAtualizacaoProduto.descricao() != null){
            this.descricao = dadosAtualizacaoProduto.descricao();
        }
        if (dadosAtualizacaoProduto.preco() != null){
            this.preco = dadosAtualizacaoProduto.preco();
        }
    }
}
