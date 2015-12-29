package Controller;

import DAO.CidadeDAO;
import Model.Cidade;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "cidadeController")
@ApplicationScoped
public class CidadeController {

    public List<Cidade> createCidades() {
        List<Cidade> cidadeList = null;

        CidadeDAO helper = new CidadeDAO();
        cidadeList = helper.getCidadeList();

        return cidadeList;
    }

}
