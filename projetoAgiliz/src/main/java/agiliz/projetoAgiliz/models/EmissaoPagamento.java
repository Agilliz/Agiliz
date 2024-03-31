package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "emissao_pagamento")
@NoArgsConstructor
public class EmissaoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmissaoPagamento;

    @ManyToOne
    @JoinColumn(name = "fk_colaborador")
    private Colaborador colaborador;

    private LocalDate data;
    private Double valor;
    private boolean fechada;

    public EmissaoPagamento(Double valor, Colaborador colaborador) {
        this.valor = valor;
        this.data = LocalDate.now();
        this.colaborador = colaborador;
    }

    public void atualizarValor(double valor) {
        this.valor += valor;
    }
}
