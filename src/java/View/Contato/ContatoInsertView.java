package View.Contato;

import Controller.ContatoController;
import Model.Cidade;
import Model.Contato;
import Model.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "contatoInsertView")
@ViewScoped
public class ContatoInsertView implements Serializable {

    private Contato contato;
    private List<Contato> contatosInseridos;

    @ManagedProperty(value = "#{contatoController}")
    private ContatoController contatoController;
    
    @PostConstruct
    public void init(){
        contatosInseridos = new ArrayList<>();
    }

    public List<Cidade> getCidaesByLike(String filtro) {
        return contatoController.getCidadesByLike(filtro);
    }

    public void openDialogInsert() {
        contato = new Contato();
        contato.setEndereco(new Endereco());
        contato.getEndereco().setCidade(new Cidade());
        RequestContext.getCurrentInstance().execute("PF('insertContato').show();");
    }

    public void insertContato() {
        contatosInseridos.add(contato);
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

    public List<Contato> getContatosInseridos() {
        return contatosInseridos;
    }

    public void setContatosInseridos(List<Contato> contatosInseridos) {
        this.contatosInseridos = contatosInseridos;
    }
    
    
}
