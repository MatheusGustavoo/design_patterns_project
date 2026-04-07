package formatador;

import modelo.Produto;

/**
 * Decorator base para formatação de produtos.
 * Delega todos os métodos ao produto envolvido,
 * cabendo às subclasses sobrescrever apenas formataParaImpressao().
 */
public abstract class FormataProduto implements Produto {

    protected final Produto produto;

    protected FormataProduto(Produto produto) {
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