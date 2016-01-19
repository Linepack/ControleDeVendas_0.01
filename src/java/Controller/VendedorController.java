package Controller;

import DAO.CidadeDAO;
import DAO.VendedorDAO;
import Model.Cidade;
import Model.Vendedor;
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
public class VendedorController {
              
    public List<Vendedor> createVendedores() {
        List<Vendedor> vendedorList = null;       
        vendedorList = VendedorDAO.getVendedorList();
        return vendedorList;
    }                
    
    public List<Cidade> getCidadeListByLike(String filtro) {
        List<Cidade> cidades = new ArrayList<>();
        cidades = CidadeDAO.getCidadeListByLike(filtro);        
        return cidades;
    }          
    
    public String insertVendedor(Vendedor vendedor){        
        return VendedorDAO.insertVendedor(vendedor);        
    }      
    
    public String deleteVendedor(Vendedor vendedor){
        return VendedorDAO.deleteVendedor(vendedor);
    }
    
    public String updateVendedor(Vendedor vendedor){
        return VendedorDAO.updateVendedor(vendedor);        
    }
}
