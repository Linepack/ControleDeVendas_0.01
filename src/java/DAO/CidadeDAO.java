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

    EntityManager em = null;

    public CidadeDAO() {
        this.em = EntityManagerUtil.createEntityManager();
    }

    public Cidade getCidadeByID(int id) {
        Cidade cidade = null;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("select c from Cidade c where c.id = " + id);
            cidade = (Cidade) q.getResultList();

        } catch (Exception e) {
            printStackTrace(e);
        }
        return cidade;
    }

    public List getCidadeList() {
        List<Cidade> cidadeList = new ArrayList<>();

        em.getTransaction().begin();
        Query q = em.createQuery("select c from Cidade c order by c.id");
        cidadeList = (List<Cidade>) q.getResultList();

        return cidadeList;
    }

}
