import java.util.ArrayList;
import java.util.Comparator;
import modelo.Produto;

public class InsertionSort implements Algoritmo_Interface {
    private Comparator<Produto> tipoComparador;

    public InsertionSort(Comparator<Produto> comparator) {
        this.tipoComparador = comparator;
    }
    
    @Override
    public void sort(int ini, int fim, ArrayList<Produto> produtos) {
        for (int i = ini; i <= fim; i++) {
            Produto x = produtos.get(i);
            int j = (i - 1);

            while (j >= ini) {
                if (tipoComparador.compare(x, produtos.get(j)) < 0) {

                    produtos.set(j + 1, produtos.get(j));
                    j--;
                } else
                    break;
            }
            produtos.set(j + 1, x);
        }
    }
}
