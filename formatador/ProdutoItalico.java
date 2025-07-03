package formatador;

import modelo.Produto;

public class ProdutoItalico extends FormataProduto {

    public ProdutoItalico(Produto produto) {
        super(produto);
    }

    @Override
    public String formataParaImpressao() {
        return "<i>" + super.formataParaImpressao() + "</i>";
    }
}
