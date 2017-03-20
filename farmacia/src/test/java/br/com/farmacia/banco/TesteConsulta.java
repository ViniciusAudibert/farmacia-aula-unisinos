/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmacia.banco;

import br.com.farmacia.farmacia.ConnectionFactory;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author fabiovarisco
 */
public class TesteConsulta {
    
    private static ProdutoJpaController produtosJpaController;
    private static FornecedorJpaController fornecedoresJpaController;
    private static ProdutoEntradaJpaController produtoEntradaJpaController;
    private static ProdutoMovimentacaoJpaController produtoMovimentacaoJpaController;
   
    @BeforeClass
    public static void setUpClass() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        produtosJpaController = new ProdutoJpaController(connectionFactory.getSessionFactory());
        fornecedoresJpaController = new FornecedorJpaController(connectionFactory.getSessionFactory());
        produtoEntradaJpaController = new ProdutoEntradaJpaController(connectionFactory.getSessionFactory());
        produtoMovimentacaoJpaController = new ProdutoMovimentacaoJpaController(connectionFactory.getSessionFactory());
   
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
    @Test(dependsOnMethods = {"br.com.farmacia.banco.TesteInsercao.testPopularProdutos"})
    public void testConsultaProdutos(){
        List<Produto> produtos = JSONUtils.readProdutosFromJSON(TesteInsercao.FILENAME_PRODUTOS);
        System.out.println("Comparando produtos: ");
        for (Produto p: produtos) {
            Produto pSalvo = produtosJpaController.findProduto(p.getId());
            if (pSalvo==null) {
                fail("Produto com id "+p.getId()+" não existe no banco ou "
                        + "ocorreu um erro na consulta.");
            } else {
                System.out.println("JSON: " + p.toString());
                System.out.println("Banco: " + pSalvo.toString());
                assertTrue(pSalvo.equals(p), 
                        "Produto no arquivo JSON deve ser igual ao produto "
                        + "de mesmo ID no banco. ID: " + p.getId());
            }
        }
    }
    
    @Test(dependsOnMethods = {"br.com.farmacia.banco.TesteInsercao.testPopularFornecedores"})
    public void testConsultaFornecedores(){
        List<Fornecedor> fornecedores = JSONUtils.readFornecedoresFromJSON(TesteInsercao.FILENAME_FORNECEDORES);
        System.out.println("Comparando fornecedores: ");
        for (Fornecedor f: fornecedores) {
            Fornecedor fSalvo = fornecedoresJpaController.findFornecedor(f.getId());
            if (fSalvo==null){
                fail("Fornecedor ID " + f.getId() + " não foi salvo no banco ou"
                        + " ocorreu um erro durante a consulta.");
            } else {
                System.out.println("JSON: "+f.toString());
                System.out.println("Banco: "+fSalvo.toString());
                assertTrue(fSalvo.equals(f), 
                        "Fornecedor no arquivo JSON deve ser igual ao "
                        + "fornecedor de mesmo ID no banco. ID: " + f.getId());
            }
        }
    }
    
    @Test(dependsOnMethods = {"br.com.farmacia.banco.TesteInsercao.testPopularProdutoEntradas"})
    public void testConsultaProdutoEntradas(){
        List<ProdutoEntrada> entradas = JSONUtils.
                readProdutoEntradasFromJSON(TesteInsercao.FILENAME_PRODUTO_ENTRADAS);
        for (ProdutoEntrada pe : entradas){
            ProdutoEntrada peSalvo = produtoEntradaJpaController.
                    findProdutoEntrada(pe.getId());
            if (peSalvo==null) {
                fail("Produto Entrada ID " + pe.getId() + " não foi salvo no"
                        + "banco ou ocorreu um erro durante a consulta.");
            } else {
                System.out.println("JSON: " + pe.toString());
                System.out.println("Banco: " + peSalvo.toString());
                assertTrue(peSalvo.equals(pe), "Registro de entrada de produto"
                        + " no arquivo JSON deve ser igual ao registro de mesmo "
                        + "ID no banco. ID: " + pe.getId());
            }
        }
    }
    
    @Test(dependsOnMethods = {"br.com.farmacia.banco.TesteInsercao.testPopularProdutoMovimentacoes"})
    public void testConsultaProdutoMovimentacoes(){
        List<ProdutoMovimentacao> movimentacoes = JSONUtils.
                readProdutoMovimentacoesFromJSON(TesteInsercao.FILENAME_PRODUTO_MOVIMENTACOES);
        for (ProdutoMovimentacao pm : movimentacoes){
            ProdutoMovimentacao pmSalvo = produtoMovimentacaoJpaController.
                    findProdutoMovimentacao(pm.getId());
            if (pmSalvo==null) {
                fail("Produto Movimentacao ID " + pm.getId() + " não foi salvo no"
                        + "banco ou ocorreu um erro durante a consulta.");
            } else {
                System.out.println("JSON: " + pm.toString());
                System.out.println("Banco: " + pmSalvo.toString());
                assertTrue(pmSalvo.equals(pm), "Registro de movimentacao de "
                        + "produto no arquivo JSON deve ser igual ao registro "
                        + "de mesmo ID no banco. ID: " + pm.getId());
            }
        }
    }
}
