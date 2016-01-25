package Model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author leandro
 */
@Entity
@DiscriminatorValue("Vendedor")
public class Vendedor extends Pessoa implements Serializable{
        
    private Float percentualComissao;

    public Float getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(Float percentualComissao) {
        this.percentualComissao = percentualComissao;
    }           
        
}
