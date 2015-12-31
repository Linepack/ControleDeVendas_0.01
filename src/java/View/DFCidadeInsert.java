package View;

import Controller.CidadeController;
import DAO.CidadeDAO;
import Model.Cidade;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "dfCidadeInsert")
@ViewScoped
public class DFCidadeInsert implements Serializable {

    @ManagedProperty("#{cidadeController}")
    private CidadeController controller;

    public String cidade;
    public String uf;

    public void viewCidadeInsert() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("modal", true);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        RequestContext.getCurrentInstance().openDialog("cidadeInsert", options, null);

    }

    public void insertCidade() throws IOException {
        controller.insertCidade(this.cidade, this.uf);
        RequestContext.getCurrentInstance().closeDialog("cidadeInsert");
    }

    public CidadeController getController() {
        return controller;
    }

    public void setController(CidadeController controller) {
        this.controller = controller;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
