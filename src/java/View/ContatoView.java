package View;

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
@ManagedBean(name = "contatoView")
@ViewScoped
public class ContatoView implements Serializable {

    private Contato contato;
    private List<Contato> contatos;     
    private List<Contato> contatosFiltrados;
    private List<Contato> contatosSelecionados;

    @ManagedProperty(value = "#{contatoController}")
    private ContatoController contatoController;
    
    @PostConstruct
    public void init(){
        contatos = new ArrayList<>();
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
        contatos.add(contato);
        RequestContext.getCurrentInstance().execute("PF('insertContato').hide();");
    }        
    
    public void openDataTableUpdate() {       
        RequestContext.getCurrentInstance().execute("PF('editContato').show();");
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

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Contato> getContatosFiltrados() {
        return contatosFiltrados;
    }

    public void setContatosFiltrados(List<Contato> contatosFiltrados) {
        this.contatosFiltrados = contatosFiltrados;
    }

    public List<Contato> getContatosSelecionados() {
        return contatosSelecionados;
    }

    public void setContatosSelecionados(List<Contato> contatosSelecionados) {
        this.contatosSelecionados = contatosSelecionados;
    }
    
    
    
    
}
