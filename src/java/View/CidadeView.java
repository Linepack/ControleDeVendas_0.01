package View;

import Controller.CidadeController;
import Model.Cidade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "dtCidadeView")
@ViewScoped
public class CidadeView implements Serializable{
    
    private List<Cidade> cidades;
    
    @ManagedProperty("#{cidadeController}")
    private CidadeController controller;
    
    @PostConstruct
    public void init(){
        cidades = controller.createCidades();
    }
    
    public List<Cidade> getCidades(){
        return this.cidades;
    }
    
    public void setController(CidadeController controller){
        this.controller = controller;
    }
                    
}
