package agiliz.projetoAgiliz.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CalculadoraRotas {
    private Set<String> enderecos;
    private Map<String, Map<String, Double>> mapaDeDistancia;
    private Map<String, Map<String, Double>> mapaFeromonios;
    private List<Formiga> formigas;

    public CalculadoraRotas(List<Endereco> enderecos) {
        this.enderecos = new HashSet<>(enderecos.stream().map(Endereco::getId).toList());
        this.mapaDeDistancia = calcularDistancias(enderecos);
        this.mapaFeromonios = inicializarFeromonios(enderecos);
    }

    public List<String> gerarRotaIdeal(String inicio) {
        this.formigas = new ArrayList<>();


        for (String endereco : this.enderecos) {
            this.formigas.add(new Formiga(endereco));
        }

        List<String> rota = new ArrayList<>();
        
        for (int c = 0; c < 100; c++) {
            double menorDistancia = Double.MAX_VALUE;
            
            for (Formiga formiga : this.formigas) {
                Set<String> cidadesRestantes = new HashSet<>(this.enderecos);
                cidadesRestantes.remove(formiga.getColonia());

                while (!cidadesRestantes.isEmpty()) {
                    String proximoVtx = escolherAleatoriamente(formiga.getLocalizacao(), cidadesRestantes);

                    double distancia = mapaDeDistancia.get(formiga.getLocalizacao()).get(proximoVtx);

                    formiga.getRotasPercorridas().add(new String[] { formiga.getLocalizacao(), proximoVtx });

                    formiga.atualizarDistancia(distancia);
                    formiga.setLocalizacao(proximoVtx);

                    cidadesRestantes.remove(proximoVtx);
                }

                if (formiga.getDistanciaPercorrida() < menorDistancia) {
                    rota = formiga.getRota();
                    menorDistancia = formiga.getDistanciaPercorrida();
                }
            }

            atualizarFeromonios();
        }

        return rota;
    }

    public void atualizarFeromonios() {
        int q = 500;

        double taxaEvaporacao = .95;
        this.mapaFeromonios
                .forEach((k, v) -> this.mapaFeromonios.get(k).replaceAll((chave, valor) -> valor * taxaEvaporacao));

        for (Formiga formiga : this.formigas) {
            double distanciaFormiga = formiga.getDistanciaPercorrida();
            double contribuicao = q / distanciaFormiga;

            for (String[] rota : formiga.getRotasPercorridas()) {
                double distanciaIndo = this.mapaFeromonios.get(rota[0]).get(rota[1]);
                double distanciaVoltando = this.mapaFeromonios.get(rota[1]).get(rota[0]);
                this.mapaFeromonios.get(rota[0]).put(rota[1], distanciaIndo + contribuicao);
                this.mapaFeromonios.get(rota[1]).put(rota[0], distanciaVoltando + contribuicao);
            }

            formiga.limparIteracao();
        }

    }

    public List<String> gerarRota(String vtxInicial, String vtxFinal) {
        List<String> rotaFormigas = gerarRotaIdeal(vtxInicial);
        List<String> rota = calcularRota(enderecos, vtxInicial, vtxFinal);
        System.out.println("Rota normal: " + calcularDistancia(rota));
        System.out.println("Rota formigas: " + calcularDistancia(kOpt(rotaFormigas)));
        System.out.println();
        return kOpt(rota);
    }

    public Map<String, Double> gerarProbabilidades(String vtxAtual, Set<String> cidadesRestantes) {
        Map<String, Double> distancias = this.mapaDeDistancia.get(vtxAtual);
        Map<String, Double> feromonios = this.mapaFeromonios.get(vtxAtual);
        Map<String, Double> probabilidades = new HashMap<>();

        double alpha = 1;
        double beta = 1;

        double totalPorcentagens = 0.;

        for (String cidade : cidadesRestantes) {
            double distancia = distancias.get(cidade);
            double quantidadeFeromonio = feromonios.get(cidade);
            double fatorDistancia = Math.pow((1. / distancia), beta);
            double fatorFeromonio = Math.pow(quantidadeFeromonio, alpha);
            // System.out.println("Distancia: "+fatorDistancia);
            // System.out.println("Feromonio: " + fatorFeromonio);

            double desejabilidade = fatorDistancia * fatorFeromonio;

            probabilidades.put(cidade, desejabilidade);

            totalPorcentagens += desejabilidade;
        }

        double total = totalPorcentagens;
        probabilidades.replaceAll((k, v) -> v / total);

        return probabilidades;
    }

    public Map<String, Map<String, Double>> inicializarFeromonios(List<Endereco> enderecos) {
        Map<String, Map<String, Double>> mapaFeromonios = new HashMap<>();

        double feromonio = .6;

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

    public String escolherAleatoriamente(String localizacao, Set<String> cidades) {
        Map<String, Double> opcoes = gerarProbabilidades(localizacao, cidades);

        if (opcoes.size() == 1)
            return opcoes.keySet().iterator().next();

        double aleatorio = ThreadLocalRandom.current().nextDouble();

        List<Map.Entry<String, Double>> opcoesOrdenadas = opcoes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        String proximoVtx = opcoesOrdenadas.get(0).getKey();

        // System.out.println("Aleaotio: " + aleatorio);

        for (Map.Entry<String, Double> entry : opcoesOrdenadas) {
            // System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
            if (aleatorio >= entry.getValue())
                proximoVtx = entry.getKey();
        }

        return proximoVtx;
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
