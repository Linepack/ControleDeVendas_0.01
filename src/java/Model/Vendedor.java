package Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author leandro
 */
@Entity
@DiscriminatorValue("Vendedor")
public class Vendedor extends Pessoa{
        
    @Column(precision = 2)
    private Float percentualComissao;

    public Float getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(Float percentualComissao) {
        this.percentualComissao = percentualComissao;
    }           
        
}
