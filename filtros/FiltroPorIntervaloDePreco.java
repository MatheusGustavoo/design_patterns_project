package filtros;
import modelo.Produto;

public class FiltroPorIntervaloDePreco implements Filtro_interface {
    private double min, max;

    public FiltroPorIntervaloDePreco(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean filtrar(Produto p) {
        return p.getPreco() >= min && p.getPreco() <= max;
    }
}