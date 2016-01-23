package DAO;

import Model.Contato;
import Utils.EntityManagerUtil;
import Utils.TransactionUtil;
import javax.persistence.EntityManager;

/**
 *
 * @author leandro
 */
public class ContatoDAO {

    private static EntityManager em = EntityManagerUtil.createEntityManager();

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
}
