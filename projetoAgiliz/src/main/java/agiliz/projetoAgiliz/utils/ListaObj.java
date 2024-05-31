package agiliz.projetoAgiliz.utils;

public class ListaObj <T> {
    private T[] vetor;

    private int nroElem;

    public ListaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length){
            throw  new IllegalStateException();
        } else {
            vetor[nroElem++] = elemento;
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i] == elementoBuscado){
                return i;
            }
        }
        return  - 1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice > nroElem  || indice < 0){
            return false;
        }
        else {
            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor [i + 1];
            }
            nroElem--;
        }
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getNroElem() {

        return nroElem;
    }

    public T getElemento(int indice) {

        if (indice <= vetor.length) {
            for (int i = 0; i < vetor.length; i++) {
                if (i == indice){
                    return vetor[i];
                }
            }
        }
        return null;
    }

    public void limpa() {
        vetor =  (T[]) new Object[vetor.length];
    }

    public void exibe() {
        for (int i = 0; i < nroElem ; i++) {
            System.out.println(vetor[i]);
        }
    }

    public T[] getVetor() {
        return vetor;
    }


}