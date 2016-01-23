package Controller;

import DAO.CidadeDAO;
import DAO.ContatoDAO;
import Model.Cidade;
import Model.Contato;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "contatoController")
@ApplicationScoped
public class ContatoController implements Serializable{
    
    public List<Cidade> getCidadesByLike(String filtro){
        List<Cidade> cidades =  new ArrayList<>();
        cidades = CidadeDAO.getCidadeListByLike(filtro);        
        return cidades;
    }
    
    public String insertContato(Contato contato){
        return ContatoDAO.insertContato(contato);
    }
    
    public List<Contato> getContatosByPessoaID(Integer pessoaID){
        return ContatoDAO.getContatosByPessoID(pessoaID);
    }
    
}
