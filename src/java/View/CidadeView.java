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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
    private Integer idCidade;
    private String nomeCidade;
    private String ufCidade;
    private String mensagem;

    @ManagedProperty("#{cidadeController}")
    private CidadeController controller;

    @PostConstruct
    public void init() {
        cidades = controller.createCidades();
    }

    public void insertCidade() throws IOException {
        controller.insertCidade(nomeCidade, ufCidade);
        FacesContext.getCurrentInstance().getExternalContext().redirect("cidadeView.xhtml");
    }

    public void deleteCidade() throws IOException {
        for (Cidade cidade : cidadesSelecionadas) {
            controller.deleteCidade(cidade);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("cidadeView.xhtml");
    }

    public void listEditCidade() {
        Integer numeroSelecoes = 0;
        for (Cidade cidade : cidadesSelecionadas) {
            idCidade = cidade.getId();
            nomeCidade = cidade.getCidade();
            ufCidade = cidade.getUf();
            numeroSelecoes++;
        }
        if (numeroSelecoes == 1) {                        
            RequestContext.getCurrentInstance().execute("PF('editCidade').show();");
        } else if (numeroSelecoes >= 2) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Selecione apenas um registro!"));
        }
    }
    
    public void updateCidade(){
        Cidade cidade  = new Cidade(idCidade, nomeCidade, ufCidade);
        controller.updateCidade(cidade);
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

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getUfCidade() {
        return ufCidade;
    }

    public void setUfCidade(String ufCidade) {
        this.ufCidade = ufCidade;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
