package filtros;
 
import modelo.Produto;
 
/**
 * Strategy para critérios de filtragem de produtos.
 */
public interface FiltroProduto {
    boolean filtrar(Produto produto);
}