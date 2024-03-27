package agiliz.projetoAgiliz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "emissao_pagamento")
public class EmissaoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmissaoPagamento;

    @ManyToOne
    @JoinColumn(name = "fk_pagamento")
    private Pagamento pagamento;

    private LocalDate data;
    private Double valor;

    public EmissaoPagamento(Double valor) {
        this.valor = valor;
        this.data = LocalDate.now();
    }
}
