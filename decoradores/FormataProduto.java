package decoradores;

import modelo.Produto;

public abstract class FormataProduto implements Produto {
    protected Produto produto;

    public FormataProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int getId() {
        return produto.getId();
    }

    @Override
    public String getDescricao() {
        return produto.getDescricao();
    }

    @Override
    public String getCategoria() {
        return produto.getCategoria();
    }

    @Override
    public int getQtdEstoque() {
        return produto.getQtdEstoque();
    }

    @Override
    public void setQtdEstoque(int qtd) {
        produto.setQtdEstoque(qtd);
    }

    @Override
    public double getPreco() {
        return produto.getPreco();
    }

    @Override
    public void setPreco(double preco) {
        produto.setPreco(preco);
    }

    @Override
    public String formataParaImpressao() {
        return produto.formataParaImpressao();
    }
}
