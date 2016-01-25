/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ContatoDAO;
import Model.Contato;
import java.io.Serializable;
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
    
    public String insertContato(Contato contato) {
        return ContatoDAO.insertContato(contato);
    }
    
    public String updateContato(Contato contato) {
        return ContatoDAO.updateContato(contato);
    }

    public List<Contato> getContatosByPessoaID(Integer pessoaID) {
        return ContatoDAO.getContatosByPessoID(pessoaID);
    }

    public String deleteContato(Contato contato) {
        return ContatoDAO.deleteContato(contato);
    }
    
}
