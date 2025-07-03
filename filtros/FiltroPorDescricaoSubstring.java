package filtros;
import modelo.Produto;

public class FiltroPorDescricaoSubstring implements Filtro_interface {
    private String termo;

    public FiltroPorDescricaoSubstring(String termo) {
        this.termo = termo.toLowerCase();
    }

    @Override
    public boolean filtrar(Produto p) {
        return p.getDescricao().toLowerCase().contains(termo);
    }
}