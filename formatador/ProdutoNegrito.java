package formatador;

import modelo.Produto;

public class ProdutoNegrito extends FormataProduto {

    public ProdutoNegrito(Produto produto) {
        super(produto);
    }

    @Override
    public String formataParaImpressao() {
        return "<b>" + super.formataParaImpressao() + "</b>";
    }
}
