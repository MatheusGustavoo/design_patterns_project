import algoritmos.AlgoritmoOrdenacao;
import algoritmos.InsertionSort;
import algoritmos.QuickSort;
import comparadores.ComparadorDescricao;
import comparadores.ComparadorEstoque;
import comparadores.ComparadorPreco;
import filtros.*;
import formatador.*;
import modelo.Produto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Ponto de entrada da aplicação.
 *
 * Uso:
 *   java Main <algoritmo> <criterio_ord> <criterio_filtro> <parametro_filtro> <arquivo_csv> [formatacao]
 *
 * algoritmo:        quick | insertion
 * criterio_ord:     preco | descricao | estoque
 * criterio_filtro:  todos | estoque_menor_igual | categoria | preco_intervalo | descricao_contendo
 * parametro_filtro: valor dependente do critério (ex: "10", "eletronicos", "10.0-50.0", "notebook")
 * arquivo_csv:      caminho para o arquivo de entrada
 * formatacao:       negrito, italico, vermelho, azul (combináveis, ex: "negrito italico")
 */
public class Main {

    private static final String ARQUIVO_SAIDA = "relatorio.html";

    public static void main(String[] args) {
        if (args.length < 5) {
            exibirUso();
            return;
        }

        String opcaoAlgoritmo      = args[0];
        String opcaoCriterioOrd    = args[1];
        String opcaoCriterioFiltro = args[2];
        String opcaoParametroFiltro = args[3];
        String arquivoEntrada      = args[4];
        String opcaoFormatacao     = args.length > 5 ? args[5].toLowerCase() : "";

        List<Produto> produtos = new CarregarProdutos(arquivoEntrada).carregar();
        List<Produto> produtosFormatados = aplicarFormatacao(produtos, opcaoFormatacao);

        Comparator<Produto> comparador = resolverComparador(opcaoCriterioOrd);
        if (comparador == null) return;

        AlgoritmoOrdenacao algoritmo = resolverAlgoritmo(opcaoAlgoritmo, comparador);
        if (algoritmo == null) return;

        FiltroProduto filtro = resolverFiltro(opcaoCriterioFiltro, opcaoParametroFiltro);
        if (filtro == null) return;

        GeradorDeRelatorios gerador = new GeradorDeRelatorios(produtosFormatados);
        gerador.setAlgoritmoOrdenacao(algoritmo);
        gerador.setFiltroProduto(filtro);
        gerador.gerar(ARQUIVO_SAIDA);

        System.out.println("Relatório gerado: " + ARQUIVO_SAIDA);
    }

    /**
     * Aplica decorators de formatação em cadeia sobre cada produto.
     * A ordem de aplicação define o aninhamento dos decorators.
     */
    private static List<Produto> aplicarFormatacao(List<Produto> produtos, String opcoes) {
        List<Produto> resultado = new ArrayList<>();

        for (Produto produto : produtos) {
            Produto formatado = produto;

            if (opcoes.contains("negrito")) {
                formatado = new ProdutoNegrito(formatado);
            }
            if (opcoes.contains("italico")) {
                formatado = new ProdutoItalico(formatado);
            }
            if (opcoes.contains("vermelho")) {
                formatado = new ProdutoColorido(formatado, "red");
            }
            if (opcoes.contains("azul")) {
                formatado = new ProdutoColorido(formatado, "blue");
            }

            resultado.add(formatado);
        }

        return resultado;
    }

    private static Comparator<Produto> resolverComparador(String opcao) {
        switch (opcao) {
            case "preco":     return new ComparadorPreco();
            case "descricao": return new ComparadorDescricao();
            case "estoque":   return new ComparadorEstoque();
            default:
                System.err.println("Critério de ordenação inválido: " + opcao);
                System.err.println("Valores aceitos: preco, descricao, estoque");
                return null;
        }
    }

    private static AlgoritmoOrdenacao resolverAlgoritmo(String opcao, Comparator<Produto> comparador) {
        switch (opcao) {
            case "quick":     return new QuickSort(comparador);
            case "insertion": return new InsertionSort(comparador);
            default:
                System.err.println("Algoritmo inválido: " + opcao);
                System.err.println("Valores aceitos: quick, insertion");
                return null;
        }
    }

    private static FiltroProduto resolverFiltro(String opcao, String parametro) {
        switch (opcao) {
            case "todos":
                return new FiltroTodos();

            case "estoque_menor_igual":
                return new FiltroPorEstoqueMenorIgual(Integer.parseInt(parametro));

            case "categoria":
                return new FiltroPorCategoria(parametro);

            case "preco_intervalo":
                String[] partes = parametro.split("-");
                if (partes.length != 2) {
                    System.err.println("Formato inválido para preco_intervalo. Use: min-max (ex: 10.0-50.0)");
                    return null;
                }
                return new FiltroPorIntervaloDePreco(
                        Double.parseDouble(partes[0]),
                        Double.parseDouble(partes[1])
                );

            case "descricao_contendo":
                return new FiltroPorDescricaoSubstring(parametro);

            default:
                System.err.println("Critério de filtragem inválido: " + opcao);
                System.err.println("Valores aceitos: todos, estoque_menor_igual, categoria, preco_intervalo, descricao_contendo");
                return null;
        }
    }

    private static void exibirUso() {
        System.out.println("Uso:");
        System.out.println("  java Main <algoritmo> <criterio_ord> <criterio_filtro> <parametro_filtro> <arquivo_csv> [formatacao]");
        System.out.println();
        System.out.println("  algoritmo:        quick | insertion");
        System.out.println("  criterio_ord:     preco | descricao | estoque");
        System.out.println("  criterio_filtro:  todos | estoque_menor_igual | categoria | preco_intervalo | descricao_contendo");
        System.out.println("  parametro_filtro: valor para o filtro (ex: 10, eletronicos, 10.0-50.0)");
        System.out.println("  formatacao:       negrito, italico, vermelho, azul (entre aspas se combinar)");
        System.out.println();
        System.out.println("Exemplo:");
        System.out.println("  java Main quick preco categoria eletronicos produtos.csv \"negrito vermelho\"");
    }
}