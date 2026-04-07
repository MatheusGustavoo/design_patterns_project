package filtros;

import modelo.Produto;

public class FiltroPorIntervaloDePreco implements FiltroProduto {

    private final double min;
    private final double max;

    public FiltroPorIntervaloDePreco(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean filtrar(Produto produto) {
        return produto.getPreco() >= min && produto.getPreco() <= max;
    }
}