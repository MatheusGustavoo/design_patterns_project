package filtros;

import modelo.Produto;

public class FiltroPorEstoqueMenorIgual implements FiltroProduto {

    private final int limite;

    public FiltroPorEstoqueMenorIgual(int limite) {
        this.limite = limite;
    }

    @Override
    public boolean filtrar(Produto produto) {
        return produto.getQtdEstoque() <= limite;
    }
}