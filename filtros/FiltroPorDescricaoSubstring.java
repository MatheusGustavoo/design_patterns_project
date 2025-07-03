package filtros;
import modelo.Produto;

public class FiltroPorDescricaoSubstring implements Filtro_interface {
    private final String substring;

    public FiltroPorDescricaoSubstring(String substring) {
        this.substring = substring.toLowerCase();
    }

    @Override
    public boolean filtrar(Produto p) {
        return p.getDescricao().toLowerCase().contains(substring);
    }
}