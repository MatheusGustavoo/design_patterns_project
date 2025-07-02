package filtros;
import modelo.Produto;

public class FiltroPorEstoqueMenorIgual implements Filtro_interface {
    private int limite;

    public FiltroPorEstoqueMenorIgual(int limite) {
        this.limite = limite;
    }

    @Override
    public boolean filtrar(Produto p) {
        return p.getQtdEstoque() <= limite;
    }
}