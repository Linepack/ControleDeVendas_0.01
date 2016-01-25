package DAO;

import Model.Contato;
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

            if (vendedor.getId() == null) {
                em.persist(vendedor);
            } else {
                em.merge(vendedor);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            return e.toString();
        }

        return "";
    }

    public static String deleteVendedor(Vendedor vendedor) {
        try {
            TransactionUtil.begin(em);
            em.remove(vendedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (e.toString().contains("Integ")) {
                return "Vendedor já utilizado em outro processo.";
            } else {
                return e.toString();
            }
        }
        return "";
    }

    public static String updateVendedor(Vendedor vendedor) {
        try {
            TransactionUtil.begin(em);
            em.merge(vendedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            return e.toString();
        }
        return "";
    }

    public static String insertContato(Contato contato) {

        try {
            TransactionUtil.begin(em);
            em.persist(contato);
            em.getTransaction().commit();
        } catch (Exception e) {
            return e.toString();
        }
        return "";
    }

    public static List getContatosByPessoID(Integer pessoaID) {

        List<Contato> contatos = new ArrayList<>();
        try {
            TransactionUtil.begin(em);
            Query q = em.createQuery("select c from Contato c join c.pessoa d where d.id = " + pessoaID);
            contatos = (List<Contato>) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contatos;
    }

    public static String deleteContato(Contato contato) {
        try {
            TransactionUtil.begin(em);
            em.remove(contato);
            em.getTransaction().commit();
        } catch (Exception e) {
            return e.toString();
        }
        return "";
    }
    
    public static String updateContato(Contato contato) {

        try {
            TransactionUtil.begin(em);
            em.merge(contato);
            em.getTransaction().commit();
        } catch (Exception e) {
            return e.toString();
        }
        return "";
    }

}
