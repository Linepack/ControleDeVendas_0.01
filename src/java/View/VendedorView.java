package View;

import Controller.VendedorController;
import Model.Cidade;
import Model.Endereco;
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
    private Vendedor vendedorInsert;
    private Vendedor vendedorUpdate;    

    @ManagedProperty(value = "#{vendedorController}")
    private VendedorController vendedorController;

    @PostConstruct
    public void init() {
        vendedores = vendedorController.createVendedores();
    }

    public List<Cidade> getCidades(String filtro) {
        return vendedorController.getCidadeListByLike(filtro);
    }

    public void openDialogInsert() {
        vendedorInsert = new Vendedor();
        vendedorInsert.setEndereco(new Endereco());
        vendedorInsert.getEndereco().setCidade(new Cidade());
        RequestContext.getCurrentInstance().execute("PF('insertVendedor').show();");
    }

    public String insertVendedor() {
        String retorno = vendedorController.insertVendedor(vendedorInsert);
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
            retorno = vendedorController.deleteVendedor(vendedor);
            if (retorno != "") {                
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro deletando", retorno));
            }
        }
        this.init();
    }
    
    public void openDialogEdit(){
        Integer numeroSelecoes = 0;
        for (Vendedor vendedor : vendedoresSelecionados){
            numeroSelecoes++;
            if (numeroSelecoes == 1){
                this.vendedorUpdate = vendedor;
            }            
        }        
        if (numeroSelecoes == 1){
            RequestContext.getCurrentInstance().execute("PF('editVendedor').show();");
        }else{   
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

    public Vendedor getVendedorInsert() {
        return vendedorInsert;
    }

    public void setVendedorInsert(Vendedor vendedorInsert) {
        this.vendedorInsert = vendedorInsert;
    }

    public Vendedor getVendedorUpdate() {
        return vendedorUpdate;
    }

    public void setVendedorUpdate(Vendedor vendedorUpdate) {
        this.vendedorUpdate = vendedorUpdate;
    }

}
