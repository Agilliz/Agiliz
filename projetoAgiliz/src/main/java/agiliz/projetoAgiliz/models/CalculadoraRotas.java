package agiliz.projetoAgiliz.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculadoraRotas {
    private Set<String> enderecos;
    private Map<String, Map<String, Double>> mapaDeDistancia;
    private Map<String, List<String>> adjMap;

    public CalculadoraRotas(List<Endereco> enderecos){
        this.enderecos = new HashSet<>(enderecos.stream().map(Endereco::getId).toList());
        this.mapaDeDistancia = calcularDistancias(enderecos);
        this.adjMap = definirAdjacencia(enderecos);
    }

    public List<String> gerarRota(String vtxInicial, String vtxFinal){
        List<String> caminho = visitarNo(vtxFinal, vtxInicial, enderecos, new ArrayList<>());
        buscaGulosa();
        System.out.println();
        return caminho;
    }

    public void buscaGulosa(){
        for(Map.Entry<String, Map<String, Double>> mapa : this.mapaDeDistancia.entrySet()){
            System.out.println("Rotas de "+mapa.getKey());
            for(Map.Entry<String, Double> entrada : mapa.getValue().entrySet()){
                System.out.println(entrada.getKey()+": "+entrada.getValue()+"km");
            }
        }
    }

    private double calcularDistancia(int indiceAtual, List<String> enderecos, double distanciaTotal){
        if(indiceAtual + 1 == enderecos.size()) return distanciaTotal;

        String origem = enderecos.get(indiceAtual);
        String destino = enderecos.get(indiceAtual + 1);

        distanciaTotal += mapaDeDistancia.get(origem).get(destino);

        return calcularDistancia(indiceAtual + 1, enderecos, distanciaTotal);
    }

    private List<String> visitarNo(
            String vtxAtual,
            String fim,
            Set<String> naoVisitados,
            List<String> visitados
    ) {
        naoVisitados.remove(vtxAtual);
        visitados.add(vtxAtual);

        String proximoVtx = null;
        double menorDistancia = Double.MAX_VALUE;
        
        for (String vizinho : adjMap.get(vtxAtual)) {
            double distancia = mapaDeDistancia.get(vtxAtual).get(vizinho);
            if(visitados.contains(vizinho)) continue;
            if(distancia > menorDistancia) continue;
            if(vizinho.equals(fim) && naoVisitados.size() > 1) continue; 

            menorDistancia = distancia;
            proximoVtx = vizinho;
        }
        
        if(proximoVtx != null) visitarNo(proximoVtx, fim, naoVisitados, visitados);

        return visitados;
    }


    private static Map<String, List<String>> definirAdjacencia(List<Endereco> enderecos) {
        Map<String, List<String>> adjMap = new HashMap<>();
        for (Endereco endereco : enderecos) {
            adjMap.put(endereco.getId(), new ArrayList<>());
            for (Endereco enderecoMapa : enderecos) {
                if (enderecoMapa.equals(endereco))
                    continue;
                adjMap.get(endereco.getId()).add(enderecoMapa.getId());
            }
        }

        return adjMap;
    }

    private static Map<String, Map<String, Double>> calcularDistancias(List<Endereco> enderecos) {
        Map<String, Map<String, Double>> mapaDeDistancia = new HashMap<>();

        for (Endereco endereco : enderecos) {
            mapaDeDistancia.put(endereco.getId(), new HashMap<>());
            for (Endereco enderecoMapa : enderecos) {
                if (enderecoMapa.equals(endereco))
                    continue;
                double distancia = calcularHarvesine(endereco, enderecoMapa);
                mapaDeDistancia.get(endereco.getId()).put(enderecoMapa.getId(), distancia);
            }
        }

        return mapaDeDistancia;
    }

    public static double calcularHarvesine(Endereco endereco1, Endereco endereco2) {
        double deltaLatitude = Math.toRadians(endereco2.getLatitude() - endereco1.getLatitude());
        double deltaLongitude = Math.toRadians(endereco2.getLongitude() - endereco1.getLongitude());

        double latitude1 = Math.toRadians(endereco1.getLatitude());
        double latitude2 = Math.toRadians(endereco2.getLatitude());

        double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2) + Math.cos(latitude1) * Math.cos(latitude2) * (Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2));

        double c = 2 * Math.asin(Math.sqrt(a));

        return c * 6371.0072;
    }
}
