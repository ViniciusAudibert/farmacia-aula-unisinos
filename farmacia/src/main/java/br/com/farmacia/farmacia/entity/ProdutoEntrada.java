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
@Table(name = "PRODUTO_ENTRADA")
public class ProdutoEntrada implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToOne
    @Column(nullable = false)
    private Produto produto;

    @OneToOne
    @Column(nullable = false)
    private Fornecedor fornecedor;
    
    @Column(nullable = false)
    private Integer quatidadeEntrada;

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

    public Integer getQuatidadeEntrada() {
        return quatidadeEntrada;
    }

    public void setQuatidadeEntrada(Integer quatidadeEntrada) {
        this.quatidadeEntrada = quatidadeEntrada;
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
}
