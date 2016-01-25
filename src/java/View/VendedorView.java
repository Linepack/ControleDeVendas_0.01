package View;

import Controller.VendedorController;
import Model.Cidade;
import Model.Contato;
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
    private Vendedor vendedor;

    private List<Contato> contatos;
    private List<Contato> contatosSelecionados;
    private List<Contato> contatosFiltrados;
    private Contato contato;

    @ManagedProperty(value = "#{vendedorController}")
    private VendedorController vendedorController;

    @PostConstruct
    public void init() {
        vendedores = vendedorController.createVendedores();
    }

    public void openDialogInsert() {
        vendedor = new Vendedor();
        RequestContext.getCurrentInstance().execute("PF('insertVendedor').show();");
    }

    public String insertVendedor() {
        String retorno = vendedorController.insertVendedor(vendedor);
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

    public void openDialogEdit() {
        Integer numeroSelecoes = 0;
        for (Vendedor vendedor : vendedoresSelecionados) {
            numeroSelecoes++;
            if (numeroSelecoes == 1) {
                this.vendedor = vendedor;
            }
        }
        if (numeroSelecoes == 1) {
            RequestContext.getCurrentInstance().execute("PF('editVendedor').show();");
        } else if (numeroSelecoes > 1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Edite um (1) vendedor por vez."));
        }
    }

    public void updateVendedor() {
        String retorno = null;
        retorno = vendedorController.updateVendedor(vendedor);
        if (retorno != "") {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro atualizando", retorno));
        } else {
            RequestContext.getCurrentInstance().execute("PF('editVendedor').hide();");
            this.init();
        }
    }

    public List<Cidade> getCidadesByLike(String filtro) {
        return vendedorController.getCidadesByLike(filtro);
    }

    public void openDialogInsertContato() {
        contato = new Contato();
        contato.setEndereco(new Endereco());
        contato.getEndereco().setCidade(new Cidade());
        contato.setPessoa(vendedor);
        RequestContext.getCurrentInstance().execute("PF('insertContato').show();");
    }

    public void insertContato() {
        String retorno = null;
        if (contato.getId() == null) {
            retorno = vendedorController.insertContato(contato);
        } else {
            retorno = vendedorController.updateContato(contato);
        }
        contatos = vendedorController.getContatosByPessoaID(vendedor.getId());
        RequestContext.getCurrentInstance().execute("PF('insertContato').hide();");
    }

    public void openDialogEditContato() {
        contatos = vendedorController.getContatosByPessoaID(vendedor.getId());
        RequestContext.getCurrentInstance().execute("PF('editContato').show();");
    }

    public void deleteContato() {
        String retorno = null;
        for (Contato contato : contatosSelecionados) {
            retorno = vendedorController.deleteContato(contato);
            if (retorno != "") {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro deletando", retorno));
            }
        }
        contatos = vendedorController.getContatosByPessoaID(vendedor.getId());
    }

    public void openDialogEditContatoIndividual() {
        Integer numeroSelecoes = 0;
        for (Contato contato : contatosSelecionados) {
            numeroSelecoes++;
            if (numeroSelecoes == 1) {
                this.contato = contato;
            }
        }
        if (numeroSelecoes == 1) {
            RequestContext.getCurrentInstance().execute("PF('insertContato').show();");
        } else if (numeroSelecoes > 1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Edite um (1) contato por vez."));
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

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Contato> getContatosSelecionados() {
        return contatosSelecionados;
    }

    public void setContatosSelecionados(List<Contato> contatosSelecionados) {
        this.contatosSelecionados = contatosSelecionados;
    }

    public List<Contato> getContatosFiltrados() {
        return contatosFiltrados;
    }

    public void setContatosFiltrados(List<Contato> contatosFiltrados) {
        this.contatosFiltrados = contatosFiltrados;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

}
