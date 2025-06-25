import java.util.ArrayList;
import java.util.Comparator;
import modelo.Produto;

public class QuickSort implements Ordenador_Interface {
    Comparator<Produto> tipoComparador;
    ArrayList<Produto> produtos;

    public QuickSort(Comparator<Produto> comparator, ArrayList<Produto> produtos) {
        this.tipoComparador = comparator;
        this.produtos = produtos;
    }

    public void sort(int ini, int fim) {
        if (ini < fim) {

            int q = particiona(ini, fim);

            this.sort(ini, q);
            this.sort(q + 1, fim);
        }
    }

    private int particiona(int ini, int fim) {

        Produto x = produtos.get(ini);
        int i = (ini - 1);
        int j = (fim + 1);

        while (true) {
            do {
                j--;

            } while (tipoComparador.compare(this.produtos.get(j), x) > 0);

            do {
                i++;

            } while (tipoComparador.compare(this.produtos.get(i), x) < 0);

            if (i < j) {
                Produto temp = produtos.get(i);
                produtos.set(i, produtos.get(j));
                produtos.set(j, temp);
            } else
                return j;
        }
    }

}
