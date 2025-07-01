import java.io.PrintWriter;
import java.io.IOException;

import java.util.*;

import modelo.Produto;
import modelo.ProdutoPadrao;

public class GeradorDeRelatorios {
	private ArrayList<Produto> produtos;
	private Algoritmo_Interface algoritmo_ordenacao;
	private Comparator<Produto> criterio_odenacao;
	private Filtro_interface filtro_produtos;

	public GeradorDeRelatorios(ArrayList<Produto> produtos) {

		this.produtos = new ArrayList<>((produtos));
	}

	public void gerar() {
		algoritmo_ordenacao.sort(0, produtos.size() - 1, produtos);

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
		;

		CarregarProdutos carregarProdutos = new CarregarProdutos(entrada);
		ArrayList<Produto> produtos = carregarProdutos.carregar();
		GeradorDeRelatorios gerador = new GeradorDeRelatorios(produtos);
		
		switch (opcao_criterio_ord) {
			case "preco_c":
				gerador.setCriterio_odenacao(new ComparatorPreco());
				break;
			case "descricao_c":
				gerador.setCriterio_odenacao(new ComparatorDescricao());
				break;
			case "estoque_c":
				gerador.setCriterio_odenacao(new ComparatorEstoque());
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
		
	}
}
