package DAO;

import Model.Cidade;
import Utils.EntityManagerUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author leandro
 */
public class CidadeDAO {

    private static EntityManager em = EntityManagerUtil.createEntityManager();

    public static Cidade getCidadeByID(int id) {
        Cidade cidade = null;
        try {

            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Query q = em.createQuery("select c from Cidade c where c.id = " + id);
            cidade = (Cidade) q.getResultList();

        } catch (Exception e) {
            printStackTrace(e);
        }
        return cidade;
    }

    public static List getCidadeList() {
        List<Cidade> cidadeList = new ArrayList<>();

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }

        Query q = em.createQuery("select c from Cidade c order by c.id");
        cidadeList = (List<Cidade>) q.getResultList();

        return cidadeList;
    }

    public static void insertCidade(String cidade, String uf) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }        
        Cidade cid = new Cidade(cidade, uf);                
        em.persist(cid);
        em.getTransaction().commit();
    }

}
