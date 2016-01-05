package DAO;

import Model.Pessoa;
import Model.Vendedor;
import Utils.EntityManagerUtil;
import Utils.TransactionUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author leandro
 */
public class VendedorDAO {

    private static EntityManager em = EntityManagerUtil.createEntityManager();

    public static List getVendedorList() {
        List<Vendedor> vendedorList = new ArrayList<>();
        TransactionUtil.begin(em);
        Query q = em.createQuery("select p from Pessoa p");
        vendedorList = (List<Vendedor>) q.getResultList();

        return vendedorList;
    }

    public static String insertVendedor(Vendedor vendedor) {
        try {
            TransactionUtil.begin(em);
            em.persist(vendedor.getEndereco());
            em.persist(vendedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            return e.toString();
        }
        
        return "";
    }

}
