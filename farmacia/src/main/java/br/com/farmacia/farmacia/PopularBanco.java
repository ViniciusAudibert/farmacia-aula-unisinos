/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.List;

/**
 *
 * @author fabiovarisco
 */
public class PopularBanco {
    
    private ProdutoJpaController produtosJpaController;
    private FornecedorJpaController fornecedoresJpaController;
    private ProdutoEntradaJpaController produtoEntradaJpaController;
    private ProdutoMovimentacaoJpaController produtoMovimentacaoJpaController;
    
    public static final String FILENAME_PRODUTOS = "./data/produtos.json",
            FILENAME_FORNECEDORES = "./data/fornecedores.json",
            FILENAME_PRODUTO_ENTRADAS = "./data/produto_entrada.json",
            FILENAME_PRODUTO_MOVIMENTACOES = "./data/produto_movimentacoes.json";
    
    public PopularBanco(ConnectionFactory connectionFactory){
        produtosJpaController = new ProdutoJpaController(connectionFactory.getSessionFactory());
        fornecedoresJpaController = new FornecedorJpaController(connectionFactory.getSessionFactory());
        produtoEntradaJpaController = new ProdutoEntradaJpaController(connectionFactory.getSessionFactory());
        produtoMovimentacaoJpaController = new ProdutoMovimentacaoJpaController(connectionFactory.getSessionFactory());
    }
    
    public void popularProdutos(){
        List<Produto> produtos = JSONUtils.readProdutosFromJSON(FILENAME_PRODUTOS);
        for (Produto p: produtos) { 
            produtosJpaController.create(p);
        }
    }
    
    public void popularFornecedores(){
        List<Fornecedor> fornecedores = JSONUtils.readFornecedoresFromJSON(FILENAME_FORNECEDORES);
        for (Fornecedor f: fornecedores) { 
            fornecedoresJpaController.create(f);
        }
    }
    
    public void popularProdutoEntradas() { 
        List<ProdutoEntrada> entradas = JSONUtils.readProdutoEntradasFromJSON(FILENAME_PRODUTO_ENTRADAS);
        for (ProdutoEntrada e : entradas) { 
            produtoEntradaJpaController.create(e);
        }
    }
    
    public void popularProdutoMovimentacoes() { 
       List<ProdutoMovimentacao> movimentacoes = JSONUtils.readProdutoMovimentacoesFromJSON(FILENAME_PRODUTO_MOVIMENTACOES);
       for (ProdutoMovimentacao m : movimentacoes) { 
           produtoMovimentacaoJpaController.create(m);
       }
    }
    
}
