package DAO;

import Model.Contato;
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

    public static List getContatosByPessoID(Integer pessoaID) {

        List<Contato> contatos = new ArrayList<>();
        try {            
            TransactionUtil.begin(em);
            Query q = em.createQuery("select c from Contato where c.id_pessoa = " + pessoaID);
            contatos = (List<Contato>) (Contato) q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contatos;
    }

}
