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

    public CalculadoraRotas(List<Endereco> enderecos) {
        this.enderecos = new HashSet<>(enderecos.stream().map(Endereco::getId).toList());
        this.mapaDeDistancia = calcularDistancias(enderecos);
        this.adjMap = definirAdjacencia(enderecos);
    }

    public List<String> gerarRota(String vtxInicial, String vtxFinal) {
        return calcularRota(enderecos, vtxInicial, vtxFinal);
    }

    private List<String> calcularRota(Set<String> vtxNaoVisitados, String inicio, String fim) {
        String vtxDistante = getMaisDistante(inicio);
        List<String> rota = new ArrayList<>(List.of(inicio, vtxDistante, fim));
        vtxNaoVisitados.removeAll(rota);

        while (!vtxNaoVisitados.isEmpty()) {
            String j = getVtxMaisDistanteDaRota(rota, vtxNaoVisitados);

            double impactoMinimo = Double.MAX_VALUE;
            int indiceInsercao = 0;

            for (int i = 0; i < rota.size() - 1; i++) {
                int k = i + 1;

                double dIj = mapaDeDistancia.get(rota.get(i)).get(j);
                double dJk = mapaDeDistancia.get(j).get(rota.get(k));
                double dIk = mapaDeDistancia.get(rota.get(i)).get(rota.get(k));
                double impacto = dIj + dJk - dIk;

                if(impacto < impactoMinimo){
                    impactoMinimo = impacto;
                    indiceInsercao = k;
                }
            }

            rota.add(indiceInsercao, j);
            vtxNaoVisitados.remove(j);
        }

        return rota;
    }

    private String getVtxMaisDistanteDaRota(List<String> rota, Set<String> possiveisNos) {
        double maiorDistancia = Double.MIN_VALUE;
        String vizinhoDistante = null;

        for (String vtx : rota) {
            double menorDistancia = Double.MAX_VALUE;
            String vizinhoProximo = null;

            for (String vizinho : possiveisNos) {
                if(vizinho.equals(vtx)) continue;

                if (mapaDeDistancia.get(vtx).get(vizinho) < menorDistancia) {
                    menorDistancia = mapaDeDistancia.get(vtx).get(vizinho);
                    vizinhoProximo = vizinho;
                }
            }
            
            if (menorDistancia > maiorDistancia) {
                maiorDistancia = menorDistancia;
                vizinhoDistante = vizinhoProximo;
            }
        }

        return vizinhoDistante;
    }

    private String getMaisDistante(String vtx) {
        double maiorDistancia = Double.MIN_VALUE;
        String vtxMaisDistante = null;

        for (Map.Entry<String, Double> entry : mapaDeDistancia.get(vtx).entrySet()) {
            if (entry.getValue() > maiorDistancia) {
                maiorDistancia = entry.getValue();
                vtxMaisDistante = entry.getKey();
            }
        }

        return vtxMaisDistante;
    }

    private double calcularDistancia(int indiceAtual, List<String> enderecos, double distanciaTotal) {
        if (indiceAtual + 1 >= enderecos.size())
            return distanciaTotal;

        String origem = enderecos.get(indiceAtual);
        String destino = enderecos.get(indiceAtual + 1);

        distanciaTotal += mapaDeDistancia.get(origem).get(destino);

        return calcularDistancia(indiceAtual + 1, enderecos, distanciaTotal);
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

        double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2) + Math.cos(latitude1) * Math.cos(latitude2)
                * (Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2));

        double c = 2 * Math.asin(Math.sqrt(a));

        return c * 6371.0072;
    }
}
