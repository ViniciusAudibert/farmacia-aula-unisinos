package br.com.farmacia.farmacia.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author vini
 */
@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String descricao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Boolean ativo;

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Produto)) {
            return false;
        }
        Produto p = (Produto) o;
        
        if (this.getId().equals(p.getId())) {
            if (this.getDescricao().equals(p.getDescricao())){
                if (this.getQuantidade().equals(p.getQuantidade())) {
                    if (this.getAtivo().equals(p.getAtivo())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Produto {ID: "+this.getId()+"; Desc: " + this.getDescricao() +
                "; Quant: " + this.getQuantidade() + "; Ativo: " +
                this.getAtivo() + ";}";                
    }
}
