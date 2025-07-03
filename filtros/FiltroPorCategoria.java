package filtros;
import modelo.Produto;

public class FiltroPorCategoria implements Filtro_interface {
    private final String categoria;

    public FiltroPorCategoria(String categoria) {
        this.categoria = categoria.toLowerCase();
    }

    @Override
    public boolean filtrar(Produto p) {
        return p.getCategoria().toLowerCase().equals(categoria);
    }
}
