package Model;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author leandro
 */
@Entity(name = "Vendedor")
public class Vendedor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private Integer id;        
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    private Float percentualComissao;

    public Float getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(Float percentualComissao) {
        this.percentualComissao = percentualComissao;
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
        
}
