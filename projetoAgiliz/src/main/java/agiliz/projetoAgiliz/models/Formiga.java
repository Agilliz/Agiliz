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
     private List<String[]> rotasPercorridas;
     private double distanciaPercorrida;
     private String colonia;

     public Formiga(String localizacao){
        this.rota = new ArrayList<>();
        this.rotasPercorridas = new ArrayList<>();
        setLocalizacao(localizacao);
        this.distanciaPercorrida = 0.;
        this.colonia = localizacao;
     }

     public void atualizarDistancia(double distancia){
         this.distanciaPercorrida += distancia;
     }

     public void setLocalizacao(String localizacao){
         this.localizacao = localizacao;
         this.rota.add(localizacao);
     }

     public void limparIteracao(){
         this.rota = new ArrayList<>();
         this.rotasPercorridas = new ArrayList<>();
         this.distanciaPercorrida = 0.;
         this.setLocalizacao(colonia);
     }
}
