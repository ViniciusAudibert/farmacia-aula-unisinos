package br.com.farmacia.farmacia.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column(nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quatidadeMovimentacao;

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

    public Integer getQuatidadeMovimentacao() {
        return quatidadeMovimentacao;
    }

    public void setQuatidadeMovimentacao(Integer quatidadeMovimentacao) {
        this.quatidadeMovimentacao = quatidadeMovimentacao;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
}
