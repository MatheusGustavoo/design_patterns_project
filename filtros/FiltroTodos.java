package filtros;
import modelo.Produto;

public class FiltroTodos implements Filtro_interface {
    @Override
    public boolean filtrar(Produto p) {
        return true;
    }
}