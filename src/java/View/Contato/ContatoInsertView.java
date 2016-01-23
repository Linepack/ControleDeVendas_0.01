package View.Contato;

import Controller.ContatoController;
import Model.Cidade;
import Model.Contato;
import Model.Endereco;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "contatoInsertView")
@ViewScoped
public class ContatoInsertView implements Serializable {

    private Contato contato;
    
    @ManagedProperty(value = "#{contatoController}")
    private ContatoController contatoController;
    
    
    public List<Cidade> getCidaesByLike(String filtro){
        return contatoController.getCidadesByLike(filtro);
    }
        
    public void openDialogInsert() {
        contato = new Contato();
        contato.setEndereco(new Endereco());
        contato.getEndereco().setCidade(new Cidade());
        RequestContext.getCurrentInstance().execute("PF('insertContato').show();");
    }
    
    public void insertContato(){
        String retorno = null;
        retorno = contatoController.insertContato(contato);
        if (retorno != ""){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro inserindo", retorno));            
        }
        RequestContext.getCurrentInstance().execute("PF('insertContato').hide();");
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ContatoController getContatoController() {
        return contatoController;
    }

    public void setContatoController(ContatoController contatoController) {
        this.contatoController = contatoController;
    }
    
    
    
}
