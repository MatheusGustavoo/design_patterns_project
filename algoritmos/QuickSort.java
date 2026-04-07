package algoritmos;

import java.util.Comparator;
import java.util.List;
import modelo.Produto;

public class QuickSort implements AlgoritmoOrdenacao {

    private final Comparator<Produto> comparador;

    public QuickSort(Comparator<Produto> comparador) {
        this.comparador = comparador;
    }

    @Override
    public void ordenar(List<Produto> produtos) {
        ordenar(produtos, 0, produtos.size() - 1);
    }

    private void ordenar(List<Produto> produtos, int ini, int fim) {
        if (ini < fim) {
            int q = particionar(produtos, ini, fim);
            ordenar(produtos, ini, q);
            ordenar(produtos, q + 1, fim);
        }
    }

    private int particionar(List<Produto> produtos, int ini, int fim) {
        Produto pivo = produtos.get(ini);
        int i = ini - 1;
        int j = fim + 1;

        while (true) {
            do { j--; } while (comparador.compare(produtos.get(j), pivo) > 0);
            do { i++; } while (comparador.compare(produtos.get(i), pivo) < 0);

            if (i < j) {
                Produto temp = produtos.get(i);
                produtos.set(i, produtos.get(j));
                produtos.set(j, temp);
            } else {
                return j;
            }
        }
    }
}