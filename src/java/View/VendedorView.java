package View;

import Controller.VendedorController;
import Model.Contato;
import Model.Vendedor;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
@ManagedBean(name = "vendedorView")
@ViewScoped
public class VendedorView implements Serializable {

    private List<Vendedor> vendedores;
    private List<Vendedor> vendedoresSelecionados;
    private List<Vendedor> vendedoresFiltrador;
    private Vendedor vendedor;
    private Boolean inserindo; 

    @ManagedProperty(value = "#{vendedorController}")
    private VendedorController vendedorController;

    @ManagedProperty(value = "#{contatoView}")
    private ContatoView contatoView;

    @PostConstruct
    public void init() {
        vendedores = vendedorController.createVendedores();
    }

    public void openDialogInsert() {
        this.inserindo = true;
        vendedor = new Vendedor();
        contatoView.setPessoaAuxiliar(vendedor);
        RequestContext.getCurrentInstance().execute("PF('insertVendedor').show();");
    }

    public String insertVendedor() {
        String retorno = "";
        
        if (vendedor.getId() == null){
            retorno = vendedorController.insertVendedor(vendedor);
        }else{
            retorno = vendedorController.updateVendedor(vendedor);
        }
        
        if (retorno != "") {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", retorno));
            return retorno;
        }
        RequestContext.getCurrentInstance().execute("PF('insertVendedor').hide();");
        this.init();
        return "";
    }

    public void deleteVendedores() {
        String retorno = null;
        for (Vendedor vendedor : vendedoresSelecionados) {

            for (Contato contato : contatoView.getContatoController().getContatosByPessoaID(vendedor.getId())) {
                retorno = contatoView.getContatoController().deleteContato(contato);
                if (retorno != "") {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro deletando", retorno));
                }
            }

            retorno = vendedorController.deleteVendedor(vendedor);
            if (retorno != "") {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro deletando", retorno));
            }
        }
        this.init();
    }

    public void openDialogEdit() {
        this.inserindo = false;
        Integer numeroSelecoes = 0;
        for (Vendedor vendedor : vendedoresSelecionados) {
            numeroSelecoes++;
            if (numeroSelecoes == 1) {
                this.vendedor = vendedor;
                contatoView.setPessoaAuxiliar(this.vendedor);
            }
        }
        if (numeroSelecoes == 1) {
            RequestContext.getCurrentInstance().execute("PF('insertVendedor').show();");
        } else if (numeroSelecoes > 1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Edite um (1) vendedor por vez."));
        }
    }  

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public VendedorController getVendedorController() {
        return vendedorController;
    }

    public void setVendedorController(VendedorController vendedorController) {
        this.vendedorController = vendedorController;
    }

    public List<Vendedor> getVendedoresSelecionados() {
        return vendedoresSelecionados;
    }

    public void setVendedoresSelecionados(List<Vendedor> vendedoresSelecionados) {
        this.vendedoresSelecionados = vendedoresSelecionados;
    }

    public List<Vendedor> getVendedoresFiltrador() {
        return vendedoresFiltrador;
    }

    public void setVendedoresFiltrador(List<Vendedor> vendedoresFiltrador) {
        this.vendedoresFiltrador = vendedoresFiltrador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ContatoView getContatoView() {
        return contatoView;
    }

    public void setContatoView(ContatoView contatoView) {
        this.contatoView = contatoView;
    }

    public Boolean getInserindo() {
        return inserindo;
    }

    public void setInserindo(Boolean inserindo) {
        this.inserindo = inserindo;
    }
    
    

}
