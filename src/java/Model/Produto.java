package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author leandro
 */
@Entity
public class Produto implements Serializable{

    @Id    
    private Long id;
    private String descricao;
    private float valorVenda;
    private String unidadeMedida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
