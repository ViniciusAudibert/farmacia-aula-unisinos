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
@Table(name = "PRODUTO_MOVIMENTACAO")
public class ProdutoMovimentacao implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidadeMovimentacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataMovimentacao;

    public ProdutoMovimentacao() {
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

    public Integer getQuantidadeMovimentacao() {
        return quantidadeMovimentacao;
    }

    public void setQuantidadeMovimentacao(Integer quantidadeMovimentacao) {
        this.quantidadeMovimentacao = quantidadeMovimentacao;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof ProdutoMovimentacao)) {
            return false;
        }
        ProdutoMovimentacao pm = (ProdutoMovimentacao) o;
        
        if (this.getId().equals(pm.getId())){
            if (this.getQuantidadeMovimentacao().equals(pm.getQuantidadeMovimentacao())){
                long d1 = (this.getDataMovimentacao().getTime()/1000) * 1000;
                long d2 = (pm.getDataMovimentacao().getTime()/1000) * 1000;
                if (d1 == d2){
                    if (this.getProduto().getId().equals(pm.getProduto().getId())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override 
    public String toString(){
        return "Produto movimentacao {ID: " + this.getId() + "; Produto ID: "
                + this.getProduto().getId() + "; Quant: " 
                + this.getQuantidadeMovimentacao() + "; Data: " 
                + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(
                         this.getDataMovimentacao());
    }
    
}
