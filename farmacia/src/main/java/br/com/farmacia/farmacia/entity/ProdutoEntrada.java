package br.com.farmacia.farmacia.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author vini
 */
@Entity
@Table(name = "PRODUTO_ENTRADA")
public class ProdutoEntrada implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @OneToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;
    
    @Column(nullable = false)
    private Integer quantidadeEntrada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataEntrada;

    public ProdutoEntrada() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Integer quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof ProdutoEntrada)) {
            return false;
        }
        ProdutoEntrada pe = (ProdutoEntrada) o;
        if (this.getId().equals(pe.getId())){
            if (this.getProduto().getId().equals(pe.getProduto().getId())) {
                if (this.getFornecedor().getId().equals(pe.getFornecedor().getId())){
                    if (this.getQuantidadeEntrada().equals(pe.getQuantidadeEntrada())){
                        long d1 = (this.getQuantidadeEntrada()/1000) * 1000;
                        long d2 = (this.getQuantidadeEntrada()/1000) * 1000;
                        if (d1==d2){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override 
    public String toString(){
        return "Produto entrada {ID: " + this.getId() + "; Produto ID: "
                + this.getProduto().getId() + "; Fornecedor ID: " 
                + this.getFornecedor().getId() + "; Quant: " 
                + this.getQuantidadeEntrada() + "; Data: " 
                + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(
                         this.getDataEntrada());
    }
}
