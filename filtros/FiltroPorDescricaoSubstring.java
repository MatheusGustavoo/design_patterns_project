package filtros;

import modelo.Produto;

public class FiltroPorDescricaoSubstring implements FiltroProduto {

    private final String substring;

    public FiltroPorDescricaoSubstring(String substring) {
        this.substring = substring.toLowerCase();
    }

    @Override
    public boolean filtrar(Produto produto) {
        return produto.getDescricao().toLowerCase().contains(substring);
    }
}