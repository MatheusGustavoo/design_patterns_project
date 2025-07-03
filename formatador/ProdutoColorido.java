package formatador;

import modelo.Produto;

public class ProdutoColorido extends FormataProduto {
    private String cor;

    public ProdutoColorido(Produto produto, String cor) {
        super(produto);
        this.cor = cor;
    }

    @Override
    public String formataParaImpressao() {
        return "<span style=\"color:" + cor + "\">" + super.formataParaImpressao() + "</span>";
    }
}
