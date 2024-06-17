package agiliz.projetoAgiliz.utils;

import agiliz.projetoAgiliz.models.Colaborador;

import java.io.*;
import java.nio.file.Files;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GeradorArquivo {

    public static byte[] gravarArquivo(ListaObj <Colaborador> listaColaborador , String nomeArq) throws IOException {

        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

// Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

// Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < listaColaborador.getNroElem(); i++) {
                Colaborador colaborador = listaColaborador.getElemento(i);
//Recupere um elemento da lista e formate aqui:
                saida.format("%s;%s;%s;%s;%s;%s;%s;%s\n",

                        colaborador.getNomeColaborador(),
                        colaborador.getCpf(),
                        colaborador.getRg(),
                        colaborador.getClasseCarteira(),
                        colaborador.getDataNascimento().toString(),
                        colaborador.getEmailColaborador(),
                        colaborador.getDataAdmissao().toString(),
                        colaborador.getTelefoneColaborador());

            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }

        return Files.readAllBytes(new File("Arquivo dos colaboradores.csv").toPath());
    }


    public static void leArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

// Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

// Bloco try-catch para ler o arquivo
        try {
//Leia e formate a saída no console aqui:

            // Cabeçalho
            System.out.printf("%-45S %-11S %-9S %-15S %-10S %-30S %-10S %-11S \n",  "nome Colaborador", "cpf", "rg",
                    "classe carteira", "data nascimento", "email"
                    , "data admissão", "telefone");
            while (entrada.hasNext()) {

                //Corpo
                String nomeColaborador = entrada.next();
                String cpf = entrada.next();
                String rg = entrada.next();
                String classeCarteira = entrada.next();
                String dataNascimento = entrada.next();
                String email = entrada.next();
                String dataAdmissao = entrada.next();
                String telefone = entrada.next();

                System.out.printf("%-15S %-7S %5.1f %-15S %-15S \n");
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

}