package agiliz.projetoAgiliz.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.RowFilter.Entry;

public class CalculadoraRotas {
    private Set<String> enderecos;
    private Map<String, Map<String, Double>> mapaDeDistancia;
    private Map<String, Map<String, Double>> mapaFeromonios;
    private List<Formiga> formigas;

    public CalculadoraRotas(List<Endereco> enderecos) {
        this.enderecos = new HashSet<>(enderecos.stream().map(Endereco::getId).toList());
        this.mapaDeDistancia = calcularDistancias(enderecos);
        this.mapaFeromonios = inicializarFeromonios(enderecos);
        this.formigas = new ArrayList<>();

        for (Endereco endereco : enderecos) {
            this.formigas.add(new Formiga(endereco.getId()));
        }
    }

    public void gerarRotaIdeal() {

        for (int c = 0; c < 1; c++) {

            for (Formiga formiga : this.formigas) {
                Set<String> cidadesRestantes = new HashSet<>(this.enderecos);
            }
        }
    }

    public Map<String, Double> gerarProbabilidades(String vtxAtual, Set<String> cidadesRestantes) {
        Map<String, Double> distancias = this.mapaDeDistancia.get(vtxAtual);
        Map<String, Double> feromonios = this.mapaFeromonios.get(vtxAtual);
        Map<String, Double> probabilidades = new HashMap<>();

        int alpha = 1;
        int beta = 1;

        double totalPorcentagens = 0.;
        for(String cidade : cidadesRestantes){
            double distancia = distancias.get(cidade);
            double quantidadeFeromonio = feromonios.get(cidade);

            double fatorDistancia = Math.pow((1 / distancia), alpha);
            double fatorFeromonio = Math.pow(quantidadeFeromonio, beta);

            double desejabilidade = fatorDistancia * fatorFeromonio;

            totalPorcentagens += totalPorcentagens;
        }

    }

    public Map<String, Map<String, Double>> inicializarFeromonios(List<Endereco> enderecos) {
        Map<String, Map<String, Double>> mapaFeromonios = new HashMap<>();

        double feromonio = .5;

        for (Endereco origem : enderecos) {
            mapaFeromonios.put(origem.getId(), new HashMap<>());
            for (Endereco destino : enderecos) {
                if (origem.equals(destino))
                    continue;
                mapaFeromonios.get(origem.getId()).put(destino.getId(), feromonio);
            }
        }

        return mapaFeromonios;
    }

    public void inicializarColonia() {

    }

    public double calcularProbabilidade() {
        return 0.;
    }

    public String escolherAleatoriamente(Map<String, Double> opcoes) {
        double aleatorio = ThreadLocalRandom.current().nextDouble();

        List<Map.Entry<String, Double>> opcoesOrdenadas = opcoes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        String proximoVtx = null;

        for (Map.Entry<String, Double> entry : opcoesOrdenadas) {
            if (aleatorio >= entry.getValue())
                proximoVtx = entry.getKey();
        }

        return proximoVtx;
    }

    public List<String> gerarRota(String vtxInicial, String vtxFinal) {

        // for (Map.Entry<String, Map<String, Double>> mapaEntry :
        // this.mapaFeromonios.entrySet()) {
        // System.out.println("Origem: " + mapaEntry.getKey());

        // for (Map.Entry<String, Double> entry : mapaEntry.getValue().entrySet()) {
        // System.out.println("Destino: " + entry.getKey() + ": " + entry.getValue());
        // }

        // System.out.println();
        // }

        List<String> rota = calcularRota(enderecos, vtxInicial, vtxFinal);
        return kOpt(rota);
    }

    private List<String> kOpt(List<String> rota) {
        double menorDistancia = calcularDistancia(rota);

        for (int i = 1; i < rota.size() - 2; i++) {
            List<String> rotaAux = new ArrayList<>(rota);
            String vtxI = rotaAux.get(i);
            rotaAux.set(i, rotaAux.get(i + 1));
            rotaAux.set(i + 1, vtxI);
            double distancia = calcularDistancia(rotaAux);

            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                rota = new ArrayList<>(rotaAux);
            }
        }

        return rota;
    }

    private List<String> calcularRota(Set<String> vtxNaoVisitados, String inicio, String fim) {
        List<String> rota = new ArrayList<>(List.of(inicio, getMaisDistante(inicio), fim));
        vtxNaoVisitados.removeAll(rota);

        while (!vtxNaoVisitados.isEmpty()) {
            String j = getVtxMaisDistanteDaRota(rota, vtxNaoVisitados);

            double impactoMinimo = Double.MAX_VALUE;
            int indiceInsercao = 0;

            for (int i = 0; i < rota.size() - 1; i++) {
                int k = i + 1;

                double distanciaIJ = mapaDeDistancia.get(rota.get(i)).get(j);
                double distanciaJK = mapaDeDistancia.get(j).get(rota.get(k));
                double distanciaIK = mapaDeDistancia.get(rota.get(i)).get(rota.get(k));

                double impacto = distanciaIJ + distanciaJK - distanciaIK;

                if (impacto < impactoMinimo) {
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
                if (vizinho.equals(vtx))
                    continue;

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

    private double calcularDistancia(List<String> rota) {
        double distanciaTotal = 0.;

        for (int i = 0; i < rota.size() - 1; i++) {
            distanciaTotal += mapaDeDistancia.get(rota.get(i)).get(rota.get(i + 1));
        }

        return distanciaTotal;
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
