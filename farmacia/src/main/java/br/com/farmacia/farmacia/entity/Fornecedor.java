package br.com.farmacia.farmacia.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Table(name = "FORNECEDOR")
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(nullable = false, length = 50)
    private String nomeCompleto;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataCadastro;

    public Fornecedor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Fornecedor)) {
            return false;
        }  
        Fornecedor f = (Fornecedor) o;
        if (this.getId().equals(f.getId())) {
            if (this.getNomeCompleto().equals(f.getNomeCompleto())) {
                if (this.getCnpj().equals(f.getCnpj())) {
                    if (this.getEndereco().equals(f.getEndereco())) {
                        long d1 = (this.getDataCadastro().getTime()/1000)*1000;
                        long d2 = (f.getDataCadastro().getTime()/1000)*1000;
                        if (d1==d2) {
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
        return "Fornecedor {ID: " + this.getId() + "; Nome: " + this.getNomeCompleto()
                + "; CNPJ: " + this.getCnpj() + "; Endereco: " + this.getEndereco()
                + "; Data cadastro: " + new SimpleDateFormat("dd/MM/yyyy "
                        + "HH:mm:ss").format(this.getDataCadastro());
    }
}
