package comparadores;
import java.util.Comparator;
import Produto;
public  class ComparadorPreco implements Comparator<Produto> {
    public int compare(Produto p1, Produto p2) {
        return Double.compare(p1.getPreco(), p2.getPreco());
    }
    
}
