package modelo;

public interface Produto {

    void setQtdEstoque(int qtdEstoque);
    void setPreco(double preco);

    int getId();
    String getDescricao();
    String getCategoria();
    int getQtdEstoque();
    double getPreco();

    String formataParaImpressao();
}
