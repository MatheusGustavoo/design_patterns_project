package modelo;

import java.text.NumberFormat;

public class ProdutoPadrao implements Produto {

    private static final String SEPARADOR = ", ";

    private final int id;
    private final String descricao;
    private final String categoria;
    private int qtdEstoque;
    private double preco;

    public ProdutoPadrao(int id, String descricao, String categoria, int qtdEstoque, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.qtdEstoque = qtdEstoque;
        this.preco = preco;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getCategoria() {
        return categoria;
    }

    @Override
    public int getQtdEstoque() {
        return qtdEstoque;
    }

    @Override
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String formataParaImpressao() {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return getDescricao() + SEPARADOR
                + getCategoria() + SEPARADOR
                + fmt.format(getPreco()) + SEPARADOR
                + getQtdEstoque() + " unidade(s) em estoque";
    }
}