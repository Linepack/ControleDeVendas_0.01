package View;

import Controller.VendedorController;
import Model.Cidade;
import Model.Endereco;
import Model.Pessoa;
import Model.Vendedor;
import java.io.Serializable;
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
public class VendedorView implements Serializable {

    private List<Vendedor> vendedores;
    private List<Vendedor> vendedoresSelecionados;
    private List<Vendedor> vendedoresFiltrador;
    private Vendedor vendedorInsert;    

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

    public void insertVendedor() {
        vendedorController.insertVendedor(vendedorInsert);
        RequestContext.getCurrentInstance().execute("PF('insertVendedor').hide();");

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
    
}
