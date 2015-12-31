package View;

import Controller.CidadeController;
import Model.Cidade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "dtCidadeView")
@ViewScoped
public class DTCidadeView implements Serializable {

    private List<Cidade> cidades;
    private Cidade cidadeSelecionada;
    private List<Cidade> cidadesSelecionadas;
    private List<Cidade> cidadesFiltradas;

    @ManagedProperty("#{cidadeController}")
    private CidadeController controller;

    @PostConstruct
    public void init() {
        cidades = controller.createCidades();
    }

    public void deleteCidade() throws IOException {
        for (Cidade cidade : cidadesSelecionadas) {
            controller.deleteCidade(cidade);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("cidadeView.xhtml");
    }

    public List<Cidade> getCidades() {
        return this.cidades;
    }

    public void setController(CidadeController controller) {
        this.controller = controller;
    }

    public Cidade getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(Cidade cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

    public List<Cidade> getCidadesSelecionadas() {
        return cidadesSelecionadas;
    }

    public void setCidadesSelecionadas(List<Cidade> cidadesSelecionadas) {
        this.cidadesSelecionadas = cidadesSelecionadas;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Cidade Selecionada", Integer.toString(((Cidade) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Cidade Desmarcada", Integer.toString(((Cidade) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Cidade> getCidadesFiltradas() {
        return cidadesFiltradas;
    }

    public void setCidadesFiltradas(List<Cidade> cidadesFiltradas) {
        this.cidadesFiltradas = cidadesFiltradas;
    }

}
