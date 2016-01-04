package View;

import Controller.VendedorController;
import Model.Cidade;
import Model.Pessoa;
import Model.Vendedor;
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
@ManagedBean(name = "vendedorView")
@ViewScoped
public class VendedorView {
    
    private List<Vendedor> vendedores;
    private List<Vendedor> vendedoresSelecionados;
    private List<Vendedor> vendedoresFiltrador;
    private Vendedor vendedorInsert;
    private Cidade cidade;
    
    @ManagedProperty(value = "#{vendedorController}")
    private VendedorController vendedorController;
        
    @PostConstruct
    public void init(){
        vendedores = vendedorController.createVendedores();
    }
        
    public List<Cidade> getCidades(String filtro){
        return vendedorController.getCidadeListByLike(filtro);                        
    }
    
    public void openDialogInsert(){
        RequestContext.getCurrentInstance().execute("PF('insertVendedor').show();");
    }
    
    public void insertVendedor(){
        
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    

}