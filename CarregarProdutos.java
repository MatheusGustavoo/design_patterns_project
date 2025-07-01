import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import modelo.Produto;
import modelo.ProdutoPadrao;

public class CarregarProdutos {
    public String caminho;
    public HashMap<Integer, ArrayList<String>> formatacao;
    public CarregarProdutos(String caminho) {
        this.caminho = caminho;
    }

    public ArrayList<Produto> carregar() {
        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            Scanner linha = new Scanner(new File(caminho));

            // Ignorando a primeira linha
            if (linha.hasNextLine()) {
                linha.nextLine();
            }

            while (linha.hasNextLine()) {
                String dados = linha.nextLine();
                String[] campos = dados.split(",");
                int id = Integer.parseInt(campos[0]);
                String descricao = campos[1];
                String categoria = campos[3];
                int qtdEstoque = Integer.parseInt(campos[4]);
                double preco = Double.parseDouble(campos[5]);

                produtos.add(new ProdutoPadrao(id, descricao, categoria, qtdEstoque, preco)); // Assumindo que você tenha esse construtor
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}
