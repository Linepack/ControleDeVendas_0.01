package Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author leandro
 */
@Entity
@DiscriminatorValue("Vendedor")
public class Vendedor extends Pessoa{
        
    private Float percentualComissao;

    public Float getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(Float percentualComissao) {
        this.percentualComissao = percentualComissao;
    }           
        
}
