package View;

import Controller.CidadeController;
import Model.Cidade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@ManagedBean(name = "cidadeView")
@ViewScoped
public class CidadeView implements Serializable {

    private List<Cidade> cidades;
    private List<Cidade> cidadesSelecionadas;
    private List<Cidade> cidadesFiltradas;
    private Cidade cidadeUpdate;
    private Cidade cidadeInsert;
    private String mensagem;

    @ManagedProperty("#{cidadeController}")
    private CidadeController controller;

    @PostConstruct
    public void init() {
        cidades = controller.createCidades();
    }

    public void openDialogInsert() {
        this.cidadeInsert = new Cidade();
        RequestContext.getCurrentInstance().execute("PF('insertCidade').show();");
    }

    public void insertCidade() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (cidadeInsert.getCidade() == "") {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome deve ser informado!", null));
        } else if (cidadeInsert.getUf() == "") {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UF deve ser informado!", null));
        } else {
            controller.insertCidade(cidadeInsert);
            RequestContext.getCurrentInstance().execute("PF('insertCidade').hide();");
            this.init();
        }
    }

    public void deleteCidade() throws IOException {
        for (Cidade cidade : cidadesSelecionadas) {
            controller.deleteCidade(cidade);
        }
        this.init();
    }

    public void openDialogEdit() {
        Integer numeroSelecoes = 0;
        for (Cidade cidade : cidadesSelecionadas) {
            numeroSelecoes++;
            if (numeroSelecoes == 1) {
                this.cidadeUpdate = new Cidade();
                this.cidadeUpdate = cidade;
            }
        }
        if (numeroSelecoes == 1) {
            RequestContext.getCurrentInstance().execute("PF('editCidade').show();");
        } else if (numeroSelecoes >= 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Selecione apenas um registro!"));
        }
    }

    public void updateCidade() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (cidadeUpdate.getCidade() == "") {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome deve ser informado!", null));
        } else if (cidadeUpdate.getUf() == "") {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UF deve ser informado!", null));
        } else {
            controller.updateCidade(this.cidadeUpdate);
            RequestContext.getCurrentInstance().execute("PF('editCidade').hide();");
        }
    }

    public List<Cidade> getCidades() {
        return this.cidades;
    }

    public void setController(CidadeController controller) {
        this.controller = controller;
    }

    public List<Cidade> getCidadesSelecionadas() {
        return cidadesSelecionadas;
    }

    public void setCidadesSelecionadas(List<Cidade> cidadesSelecionadas) {
        this.cidadesSelecionadas = cidadesSelecionadas;
    }

    public List<Cidade> getCidadesFiltradas() {
        return cidadesFiltradas;
    }

    public void setCidadesFiltradas(List<Cidade> cidadesFiltradas) {
        this.cidadesFiltradas = cidadesFiltradas;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Cidade getCidadeUpdate() {
        return cidadeUpdate;
    }

    public void setCidadeUpdate(Cidade cidadeUpdate) {
        this.cidadeUpdate = cidadeUpdate;
    }

    public Cidade getCidadeInsert() {
        return cidadeInsert;
    }

    public void setCidadeInsert(Cidade cidadeInsert) {
        this.cidadeInsert = cidadeInsert;
    }

}
