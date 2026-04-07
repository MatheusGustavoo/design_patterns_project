package filtros;

import modelo.Produto;

public class FiltroTodos implements FiltroProduto {

    @Override
    public boolean filtrar(Produto produto) {
        return true;
    }
}