package Controller;

import DAO.VendedorDAO;
import Model.Vendedor;
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
        
}
