import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Produto;
import modelo.ProdutoPadrao;

public class CarregarProdutos {

    private final String caminho;

    public CarregarProdutos(String caminho) {
        this.caminho = caminho;
    }

    public List<Produto> carregar() {
        List<Produto> produtos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(caminho))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // ignora cabeçalho
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                Produto produto = parseLinha(linha);
                if (produto != null) {
                    produtos.add(produto);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + caminho);
            e.printStackTrace();
        }

        return produtos;
    }

    private Produto parseLinha(String linha) {
        String[] campos = linha.split(",");
        if (campos.length < 5) return null;

        int id            = Integer.parseInt(campos[0].trim());
        String descricao  = campos[1].trim();
        String categoria  = campos[2].trim();
        int qtdEstoque    = Integer.parseInt(campos[3].trim());
        double preco      = Double.parseDouble(campos[4].trim());

        return new ProdutoPadrao(id, descricao, categoria, qtdEstoque, preco);
    }
}