package Controller;

import DAO.CidadeDAO;
import Model.Cidade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "cidadeController")
@ApplicationScoped
public class CidadeController implements Serializable{
              
    public List<Cidade> createCidades() {
        List<Cidade> cidadeList = null;       
        cidadeList = CidadeDAO.getCidadeList();
        return cidadeList;
    }
    
    public void insertCidade(Cidade cidade){
        CidadeDAO.insertCidade(cidade);
    }
    
    public String deleteCidade(Cidade cidade){        
        return CidadeDAO.deleteCidade(cidade);
    }
    
    public void updateCidade(Cidade cidade){
        CidadeDAO.updateCidade(cidade);        
    }
            
        
}
