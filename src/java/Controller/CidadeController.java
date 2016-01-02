package Controller;

import DAO.CidadeDAO;
import Model.Cidade;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "cidadeController")
@ApplicationScoped
public class CidadeController {
              
    public List<Cidade> createCidades() {
        List<Cidade> cidadeList = null;       
        cidadeList = CidadeDAO.getCidadeList();
        return cidadeList;
    }
    
    public void insertCidade(Cidade cidade){
        CidadeDAO.insertCidade(cidade);
    }
    
    public void deleteCidade(Cidade cidade){
        CidadeDAO.deleteCidade(cidade);
    }
    
    public void updateCidade(Cidade cidade){
        CidadeDAO.updateCidade(cidade);        
    }
            
        
}
