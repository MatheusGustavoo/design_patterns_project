import algoritmos.AlgoritmoOrdenacao;
import filtros.FiltroProduto;
import modelo.Produto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Gera relatórios HTML de produtos aplicando Strategy de ordenação e filtragem.
 *
 * Padrões aplicados:
 *   - Strategy: algoritmoOrdenacao e filtroProduto são injetados externamente,
 *     permitindo variar ordenação e filtragem sem alterar esta classe.
 *   - Decorator: a formatação já está encapsulada em cada Produto (via FormataProduto),
 *     portanto esta classe apenas itera e chama formataParaImpressao().
 */
public class GeradorDeRelatorios {

    private final List<Produto> produtos;
    private AlgoritmoOrdenacao algoritmoOrdenacao;
    private FiltroProduto filtroProduto;

    public GeradorDeRelatorios(List<Produto> produtos) {
        this.produtos = new ArrayList<>(produtos);
    }

    public void gerar(String nomeArquivoSaida) {
        algoritmoOrdenacao.ordenar(produtos);

        try (PrintWriter writer = new PrintWriter(nomeArquivoSaida)) {
            writer.println("<html><body><ul>");

            for (Produto produto : produtos) {
                if (filtroProduto.filtrar(produto)) {
                    writer.println("<li>" + produto.formataParaImpressao() + "</li>");
                }
            }

            writer.println("</ul></body></html>");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + nomeArquivoSaida);
            e.printStackTrace();
        }
    }

    public void setAlgoritmoOrdenacao(AlgoritmoOrdenacao algoritmoOrdenacao) {
        this.algoritmoOrdenacao = algoritmoOrdenacao;
    }

    public void setFiltroProduto(FiltroProduto filtroProduto) {
        this.filtroProduto = filtroProduto;
    }

    public AlgoritmoOrdenacao getAlgoritmoOrdenacao() {
        return algoritmoOrdenacao;
    }

    public FiltroProduto getFiltroProduto() {
        return filtroProduto;
    }
}