package algoritmos;
import java.util.ArrayList;
import java.util.Comparator;
import modelo.Produto;

public class QuickSort implements Algoritmo_Interface {
    Comparator<Produto> tipoComparador;

    public QuickSort(Comparator<Produto> comparator) {
        this.tipoComparador = comparator;
        
    }

    @Override
    public void sort(int ini, int fim, ArrayList<Produto> produtos) {
        if (ini < fim) {

            int q = particiona(ini, fim, produtos);

            sort(ini, q, produtos);
            sort(q + 1, fim, produtos);
        }
    }

    private int particiona(int ini, int fim, ArrayList<Produto> produtos) {

        Produto x = produtos.get(ini);
        int i = (ini - 1);
        int j = (fim + 1);

        while (true) {
            do {
                j--;

            } while (tipoComparador.compare(produtos.get(j), x) > 0);

            do {
                i++;

            } while (tipoComparador.compare(produtos.get(i), x) < 0);

            if (i < j) {
                Produto temp = produtos.get(i);
                produtos.set(i, produtos.get(j));
                produtos.set(j, temp);
            } else
                return j;
        }
    }

}
