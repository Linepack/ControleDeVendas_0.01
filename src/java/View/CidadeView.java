package View;

import Controller.CidadeController;
import Model.Cidade;
import Model.UF;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
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
@ManagedBean(name = "cidadeView")
@ViewScoped
public class CidadeView implements Serializable {

    private List<Cidade> cidades;
    private List<Cidade> cidadesSelecionadas;
    private List<Cidade> cidadesFiltradas;
    private Cidade cidade = new Cidade();
    private String mensagem;

    @ManagedProperty("#{cidadeController}")
    private CidadeController controller;

    @PostConstruct
    public void init() {
        cidades = controller.createCidades();
    }

    public List<UF> getUF() {
        return Arrays.asList(UF.values());
    }

    public void openDialogInsert() {
        this.cidade = new Cidade();
        RequestContext.getCurrentInstance().execute("PF('insertCidade').show();");
    }

    public void insertCidade() throws IOException {
        if (cidade.getId() == null) {
            controller.insertCidade(cidade);
        } else {
            controller.updateCidade(cidade);
        }
        RequestContext.getCurrentInstance().execute("PF('insertCidade').hide();");
        this.init();
    }

    public void deleteCidade() throws IOException {
        String retorno = null;
        for (Cidade cidade : cidadesSelecionadas) {
            retorno = controller.deleteCidade(cidade);
            if (retorno != "") {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Deletando", retorno));
            }

        }
        this.init();
    }

    public void openDialogEdit() {
        Integer numeroSelecoes = 0;
        for (Cidade cidade : cidadesSelecionadas) {
            numeroSelecoes++;
            if (numeroSelecoes == 1) {
                this.cidade = new Cidade();
                this.cidade = cidade;
            }
        }
        if (numeroSelecoes == 1) {
            RequestContext.getCurrentInstance().execute("PF('insertCidade').show();");
        } else if (numeroSelecoes >= 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Selecione apenas um registro!"));
        }
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public CidadeController getController() {
        return controller;
    }

    public void setController(CidadeController controller) {
        this.controller = controller;
    }

}
