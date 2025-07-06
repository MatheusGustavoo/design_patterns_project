package comparadores;
import java.util.Comparator;
import modelo.Produto;
public class ComparadorDescricao implements Comparator<Produto> {

    public int compare(Produto p1, Produto p2) {
        return p1.getDescricao().compareToIgnoreCase(p2.getDescricao());
    }
}

