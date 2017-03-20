package br.com.farmacia.farmacia;

import br.com.farmacia.farmacia.entity.Fornecedor;
import br.com.farmacia.farmacia.entity.FornecedorJpaController;
import br.com.farmacia.farmacia.entity.Produto;
import br.com.farmacia.farmacia.entity.ProdutoEntrada;
import br.com.farmacia.farmacia.entity.ProdutoEntradaJpaController;
import br.com.farmacia.farmacia.entity.ProdutoJpaController;
import br.com.farmacia.farmacia.entity.ProdutoMovimentacao;
import br.com.farmacia.farmacia.entity.ProdutoMovimentacaoJpaController;
import br.com.farmacia.json.JSONUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vini
 */
public class Run {

    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            PopularBanco popularBanco = new PopularBanco(connectionFactory);
            popularBanco.popularProdutos();
            popularBanco.popularFornecedores();
            popularBanco.popularProdutoEntradas();
            popularBanco.popularProdutoMovimentacoes();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    
}
