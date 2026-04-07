package algoritmos;

import java.util.Comparator;
import java.util.List;
import modelo.Produto;

public class InsertionSort implements AlgoritmoOrdenacao {

    private final Comparator<Produto> comparador;

    public InsertionSort(Comparator<Produto> comparador) {
        this.comparador = comparador;
    }

    @Override
    public void ordenar(List<Produto> produtos) {
        for (int i = 1; i < produtos.size(); i++) {
            Produto chave = produtos.get(i);
            int j = i - 1;

            while (j >= 0 && comparador.compare(chave, produtos.get(j)) < 0) {
                produtos.set(j + 1, produtos.get(j));
                j--;
            }
            produtos.set(j + 1, chave);
        }
    }
}