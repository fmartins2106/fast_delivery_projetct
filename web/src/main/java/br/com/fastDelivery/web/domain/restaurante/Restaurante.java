package br.com.fastDelivery.web.domain.restaurante;

import br.com.fastDelivery.web.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    private String nome;
    private Categoria categoria;
    private Endereco endereco;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    private BigDecimal pedidoMinimo;
    private boolean ativo;


}
