package algoritmos;
 
import java.util.List;
import modelo.Produto;
 
/**
 * Strategy para algoritmos de ordenação de produtos.
 * Implementações recebem a lista completa e são responsáveis
 * por ordenar todos os elementos in-place.
 */
public interface AlgoritmoOrdenacao {
    void ordenar(List<Produto> produtos);
}
