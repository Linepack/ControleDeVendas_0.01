package Controller;

import DAO.CidadeDAO;
import DAO.VendedorDAO;
import Model.Cidade;
import Model.Contato;
import Model.Vendedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author leandro
 */
@ManagedBean(name = "vendedorController")
@ApplicationScoped
public class VendedorController implements Serializable {

    public List<Vendedor> createVendedores() {
        List<Vendedor> vendedorList = null;
        vendedorList = VendedorDAO.getVendedorList();
        return vendedorList;
    }

    public String insertVendedor(Vendedor vendedor) {
        return VendedorDAO.insertVendedor(vendedor);
    }

    public String deleteVendedor(Vendedor vendedor) {
        return VendedorDAO.deleteVendedor(vendedor);
    }

    public String updateVendedor(Vendedor vendedor) {
        return VendedorDAO.updateVendedor(vendedor);
    }

    public List<Cidade> getCidadesByLike(String filtro) {
        List<Cidade> cidades = new ArrayList<>();
        cidades = CidadeDAO.getCidadeListByLike(filtro);
        return cidades;
    }

    public String insertContato(Contato contato) {
        return VendedorDAO.insertContato(contato);
    }
    
    public String updateContato(Contato contato) {
        return VendedorDAO.updateContato(contato);
    }

    public List<Contato> getContatosByPessoaID(Integer pessoaID) {
        return VendedorDAO.getContatosByPessoID(pessoaID);
    }

    public String deleteContato(Contato contato) {
        return VendedorDAO.deleteContato(contato);
    }

}
