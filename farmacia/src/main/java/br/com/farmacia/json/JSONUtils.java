/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmacia.json;

import br.com.farmacia.farmacia.entity.Fornecedor;
import br.com.farmacia.farmacia.entity.Produto;
import br.com.farmacia.farmacia.entity.ProdutoEntrada;
import br.com.farmacia.farmacia.entity.ProdutoMovimentacao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabiovarisco
 */
public class JSONUtils {
    
    public static boolean salvarProdutosToJSON(ArrayList<Produto> produtos, String filename) { 
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            mapper.writeValue(new File(filename), produtos);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean salvarFornecedoresToJSON(ArrayList<Fornecedor> fornecedores, String filename) { 
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filename), fornecedores);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean salvarProdutoEntradaToJSON(ArrayList<ProdutoEntrada> entradas, String filename) { 
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filename), entradas);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean salvarProdutoMovimentacaoToJSON(ArrayList<ProdutoMovimentacao> movimentacoes, String filename) { 
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filename), movimentacoes);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static List<Produto> readProdutosFromJSON(String filename){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filename), new TypeReference<List<Produto>>(){});
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static List<Fornecedor> readFornecedoresFromJSON(String filename){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filename), new TypeReference<List<Fornecedor>>(){});
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static List<ProdutoEntrada> readProdutoEntradasFromJSON(String filename){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filename), new TypeReference<List<ProdutoEntrada>>(){});
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static List<ProdutoMovimentacao> readProdutoMovimentacoesFromJSON(String filename){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filename), new TypeReference<List<ProdutoMovimentacao>>(){});
        } catch (Exception e) { 
            System.out.println(e.getMessage());
            return null;
        }
    }
}
