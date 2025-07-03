package comparadores;
import java.util.Comparator;
import modelo.Produto;
public  class ComparadorEstoque implements Comparator<Produto> {
    public int compare(Produto p1, Produto p2) {
        return Integer.compare(p1.getQtdEstoque(), p2.getQtdEstoque());
    }
}
