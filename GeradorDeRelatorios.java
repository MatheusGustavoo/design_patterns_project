import comparadores.ComparadorDescricao;
import comparadores.ComparadorEstoque;
import comparadores.ComparadorPreco;
import decoradores.*;
import filtros.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import modelo.Produto;

public class GeradorDeRelatorios {
	private ArrayList<Produto> produtos;
	private Algoritmo_Interface algoritmo_ordenacao;
	private Comparator<Produto> criterio_odenacao;
	private Filtro_interface filtro_produtos;

	public GeradorDeRelatorios(ArrayList<Produto> produtos) {

		this.produtos = new ArrayList<>((produtos));
	}

	 public void gerar(String nomeArquivoSaida) {
        algoritmo_ordenacao.sort(0, produtos.size() - 1, produtos);

        try (PrintWriter writer = new PrintWriter(nomeArquivoSaida)) {
            writer.println("<html><body><ul>");

            for (Produto p : produtos) {
                if (filtro_produtos.filtrar(p)) {
                    writer.println("<li>" + p.formataParaImpressao() + "</li>");
                }
            }

            writer.println("</ul></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	// public void debug() {
	// 	System.out.println("Gerando relatório para array contendo " + produtos.length + " produto(s)");
	// 	System.out.println("parametro filtro = '" + argFiltro + "'");
	// }

	public void setAlgoritmo_ordenacao(Algoritmo_Interface algoritmo_ordenacao) {
		this.algoritmo_ordenacao = algoritmo_ordenacao;
	}
	public void setCriterio_odenacao(Comparator<Produto> criterio_odenacao) {
		this.criterio_odenacao = criterio_odenacao;
	}
	public void setFiltro_produtos(Filtro_interface filtro_produtos) {
		this.filtro_produtos = filtro_produtos;
	}

	public Algoritmo_Interface getAlgoritmo_ordenacao() {
		return algoritmo_ordenacao;
	}
	public Comparator<Produto> getCriterio_odenacao() {
		return criterio_odenacao;
	}
	public Filtro_interface getFiltro_produtos() {
		return filtro_produtos;
	}	

	public static void main(String[] args) {

		if (args.length < 5) {
        	System.out.println("Uso:");
        	System.out.println("java GeradorDeRelatorios <algoritmo> <criterio_ord> <criterio_filtro> <parametro_filtro> <arquivo_csv> [opcoes_formatacao]");
        return;
    }

		// if(args.length < 4){

		// System.out.println("Uso:");
		// System.out.println("\tjava " + GeradorDeRelatorios.class.getName() + "
		// <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de
		// filtragem> <opções de formatação>");
		// Sytstem.out.println("Onde:");
		// System.out.println("\talgoritmo: 'quick' ou 'insertion'");
		// System.out.println("\tcriterio de ordenação: 'preco_c' ou 'descricao_c' ou
		// 'estoque_c'");
		// System.out.println("\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual'
		// ou 'categoria_igual'");
		// System.out.println("\tparâmetro de filtragem: argumentos adicionais
		// necessários para a filtragem");
		// System.out.println("\topções de formatação: 'negrito' e/ou 'italico'");
		// System.out.println();
		// System.exit(1);
		// }

		String opcao_algoritmo = args[0];
		String opcao_criterio_ord = args[1];
		String opcao_criterio_filtro = args[2];
		String opcao_parametro_filtro = args[3];
		String entrada = args[4];
		String formatacao = (args.length > 5) ? args[5].toLowerCase() : "";
		;

		CarregarProdutos carregarProdutos = new CarregarProdutos(entrada);
		ArrayList<Produto> produtos = carregarProdutos.carregar();
		ArrayList<Produto> produtosFormatados = new ArrayList<>();

		for (Produto p : produtos) {
            Produto formatado = p;

            if (formatacao.contains("negrito")) {
                formatado = new ProdutoNegrito(formatado);
            }
            if (formatacao.contains("italico")) {
                formatado = new ProdutoItalico(formatado);
            }
            if (formatacao.contains("vermelho")) {
                formatado = new ProdutoColorido(formatado, "red");
            }
            if (formatacao.contains("azul")) {
                formatado = new ProdutoColorido(formatado, "blue");
            }

            produtosFormatados.add(formatado);
        }

		GeradorDeRelatorios gerador = new GeradorDeRelatorios(produtosFormatados);
		
		switch (opcao_criterio_ord) {
			case "preco_c":
				gerador.setCriterio_odenacao(new ComparadorPreco());
				break;
			case "descricao_c":
				gerador.setCriterio_odenacao(new ComparadorDescricao());
				break;
			case "estoque_c":
				gerador.setCriterio_odenacao(new ComparadorEstoque());
				break;
			default:
				System.out.println("Criterio de ordenação inválido");
				System.exit(1);
		}
		switch (opcao_algoritmo) {
			case "quick":
				gerador.setAlgoritmo_ordenacao(new QuickSort(gerador.getCriterio_odenacao()));
				break;
			case "insertion":
				gerador.setAlgoritmo_ordenacao(new InsertionSort(gerador.getCriterio_odenacao()));
				break;
			default:
				System.out.println("Algoritmo de ordenação inválido");
				System.exit(1);
		}

		Filtro_interface filtro;

        switch (opcao_criterio_filtro) {
            case "todos":
                filtro = new FiltroTodos();
                break;
            case "estoque_menor_igual":
                int limite = Integer.parseInt(opcao_parametro_filtro);
                filtro = new FiltroPorEstoqueMenorIgual(limite);
                break;
            case "categoria_igual":
                filtro = new FiltroPorCategoria(opcao_parametro_filtro);
                break;
            case "preco_intervalo":
                String[] partes = opcao_parametro_filtro.split("-");
                if (partes.length != 2) {
                    System.out.println("Formato do intervalo de preço inválido. Use: min-max (ex: 10.0-30.0)");
                    return;
                }
                double min = Double.parseDouble(partes[0]);
                double max = Double.parseDouble(partes[1]);
                filtro = new FiltroPorIntervaloDePreco(min, max);
                break;
            case "descricao_contendo":
                filtro = new FiltroPorDescricaoContendo(opcao_parametro_filtro);
                break;
            default:
                System.out.println("Critério de filtragem inválido");
                return;
        }

        gerador.setFiltro_produtos(filtro);
        gerador.gerar("relatorio.html");
	}
}
