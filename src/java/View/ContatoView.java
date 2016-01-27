/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ContatoController;
import Model.Cidade;
import Model.Contato;
import Model.Endereco;
import Model.Pessoa;
import Model.TiposDeContato;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
@ManagedBean(name = "contatoView")
@ViewScoped
public class ContatoView implements Serializable {

    private List<Contato> contatos;
    private List<Contato> contatosSelecionados;
    private List<Contato> contatosFiltrados;
    private Contato contato = new Contato();
    private Pessoa pessoaAuxiliar;

    @ManagedProperty(value = "#{contatoController}")
    private ContatoController contatoController;

    @ManagedProperty(value = "#{cidadeView}")
    private CidadeView cidadeView;

    @PostConstruct
    public void init() {
        if (pessoaAuxiliar != null) {
            contatos = contatoController.getContatosByPessoaID(pessoaAuxiliar.getId());
        }
    }

    public List<TiposDeContato> getTiposDeContato() {
        return Arrays.asList(TiposDeContato.values());
    }

    public List<Cidade> getCidadesByLike(String filtro) {
        return contatoController.getCidadesByLike(filtro);
    }

    public void openDialogInsertCidadeIfNew() {
        if (contato.getEndereco().getCidade() == null) {            
            cidadeView.setCidade(new Cidade());
            RequestContext.getCurrentInstance().execute("PF('insertCidade').show();");            
        }
    }

    public void openDialogInsertContato() {
        contato = new Contato();
        contato.setEndereco(new Endereco());
        contato.getEndereco().setCidade(new Cidade());
        contato.setPessoa(pessoaAuxiliar);
        RequestContext.getCurrentInstance().execute("PF('insertContato').show();");
    }

    public String insertContato() {
        String retorno = "";
        FacesContext context = FacesContext.getCurrentInstance();

        for (Object contatoObj : contatoController.getContatosByPessoaID(pessoaAuxiliar.getId())) {
            Contato contatoCast = (Contato) contatoObj;
            if ("Sim".equals(contatoCast.getPrincipal())
                    && "Sim".equals(contato.getPrincipal())
                    && !Objects.equals(contatoCast.getId(), contato.getId())) {
                retorno = "Já existe um contato PRINCIPAL para esta pessoa, verifique!";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro inserindo.", retorno));
                contato.setPrincipal("Não");
                return "";
            }
        }

        if (contato.getId() == null) {
            retorno = contatoController.insertContato(contato);
        } else {
            retorno = contatoController.updateContato(contato);
        }

        if (retorno != "") {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro inserindo.", retorno));
            return retorno;
        } else {
            this.init();
            RequestContext.getCurrentInstance().execute("PF('insertContato').hide();");
            return "";
        }

    }

    public void deleteContato() {
        String retorno = "";
        for (Contato contato : contatosSelecionados) {
            retorno = contatoController.deleteContato(contato);
            if (retorno != "") {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro deletando", retorno));
            }
        }
        this.init();
    }

    public void openDialogEditContato() {
        this.init();
        RequestContext.getCurrentInstance().execute("PF('editContato').show();");
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

    public ContatoController getContatoController() {
        return contatoController;
    }

    public void setContatoController(ContatoController contatoController) {
        this.contatoController = contatoController;
    }

    public Pessoa getPessoaAuxiliar() {
        return pessoaAuxiliar;
    }

    public void setPessoaAuxiliar(Pessoa pessoaAuxiliar) {
        this.pessoaAuxiliar = pessoaAuxiliar;
    }

    public CidadeView getCidadeView() {
        return cidadeView;
    }

    public void setCidadeView(CidadeView cidadeView) {
        this.cidadeView = cidadeView;
    }

}
