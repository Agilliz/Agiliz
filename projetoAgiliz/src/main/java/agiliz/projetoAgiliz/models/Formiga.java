package agiliz.projetoAgiliz.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Formiga {
     private String localizacao;
     private List<String> rota;
     private double distanciaIteracao;

     public Formiga(String localizacao){
        this.localizacao = localizacao;
        this.rota = new ArrayList<>();
        this.distanciaIteracao = 0.;
     }
}
