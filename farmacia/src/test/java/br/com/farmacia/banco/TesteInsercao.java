/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmacia.banco;

import br.com.farmacia.farmacia.ConnectionFactory;
import static br.com.farmacia.farmacia.PopularBanco.FILENAME_FORNECEDORES;
import static br.com.farmacia.farmacia.PopularBanco.FILENAME_PRODUTOS;
import static br.com.farmacia.farmacia.PopularBanco.FILENAME_PRODUTO_ENTRADAS;
import static br.com.farmacia.farmacia.PopularBanco.FILENAME_PRODUTO_MOVIMENTACOES;
import br.com.farmacia.farmacia.entity.Fornecedor;
import br.com.farmacia.farmacia.entity.FornecedorJpaController;
import br.com.farmacia.farmacia.entity.Produto;
import br.com.farmacia.farmacia.entity.ProdutoEntrada;
import br.com.farmacia.farmacia.entity.ProdutoEntradaJpaController;
import br.com.farmacia.farmacia.entity.ProdutoJpaController;
import br.com.farmacia.farmacia.entity.ProdutoMovimentacao;
import br.com.farmacia.farmacia.entity.ProdutoMovimentacaoJpaController;
import br.com.farmacia.json.JSONUtils;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author fabiovarisco
 */
public class TesteInsercao {
    
    private static ProdutoJpaController produtosJpaController;
    private static FornecedorJpaController fornecedoresJpaController;
    private static ProdutoEntradaJpaController produtoEntradaJpaController;
    private static ProdutoMovimentacaoJpaController produtoMovimentacaoJpaController;
   
    public static final String FILENAME_PRODUTOS = "./resources/produtos.json",
            FILENAME_FORNECEDORES = "./resources/fornecedores.json",
            FILENAME_PRODUTO_ENTRADAS = "./resources/produto_entrada.json",
            FILENAME_PRODUTO_MOVIMENTACOES = "./resources/produto_movimentacoes.json";

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        produtosJpaController = new ProdutoJpaController(connectionFactory.getSessionFactory());
        fornecedoresJpaController = new FornecedorJpaController(connectionFactory.getSessionFactory());
        produtoEntradaJpaController = new ProdutoEntradaJpaController(connectionFactory.getSessionFactory());
        produtoMovimentacaoJpaController = new ProdutoMovimentacaoJpaController(connectionFactory.getSessionFactory());
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
    @Test
    public void testPopularProdutos(){
        List<Produto> produtos = JSONUtils.readProdutosFromJSON(FILENAME_PRODUTOS);
        for (Produto p: produtos) { 
            System.out.println("Inserindo produto: " + p.toString());
            assertTrue(produtosJpaController.create(p),
                    "Erro ao inserir produto ID " + p.getId());
        }
    }
    
    @Test
    public void testPopularFornecedores(){
        List<Fornecedor> fornecedores = JSONUtils.readFornecedoresFromJSON(FILENAME_FORNECEDORES);
        for (Fornecedor f: fornecedores) { 
            System.out.println("Inseringo fornecedor: " + f.toString());
            assertTrue(fornecedoresJpaController.create(f),
                    "Erro ao inserir fornecedor ID " + f.getId());
        }
    }
    
    @Test(dependsOnMethods = {"testPopularProdutos", "testPopularFornecedores"})
    public void testPopularProdutoEntradas() { 
        List<ProdutoEntrada> entradas = JSONUtils.readProdutoEntradasFromJSON(FILENAME_PRODUTO_ENTRADAS);
        for (ProdutoEntrada e : entradas) { 
            System.out.println("Inserindo entrada de produto: " + e.toString());
            assertTrue(produtoEntradaJpaController.create(e),
                    "Erro ao inserir entrada de produto ID " + e.getId());
        }
    }
    
    @Test(dependsOnMethods = {"testPopularProdutos"})
    public void testPopularProdutoMovimentacoes() { 
       List<ProdutoMovimentacao> movimentacoes = JSONUtils.readProdutoMovimentacoesFromJSON(FILENAME_PRODUTO_MOVIMENTACOES);
       for (ProdutoMovimentacao m : movimentacoes) { 
           System.out.println("Inserindo movimentacao de produto: " + m.toString());
           assertTrue(produtoMovimentacaoJpaController.create(m),
                   "Erro ao inserir movimentacao de produto ID " + m.getId());
       }
    }
    
}
