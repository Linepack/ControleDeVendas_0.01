package DAO;

import Model.Cidade;
import Utils.EntityManagerUtil;
import Utils.TransactionUtil;
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

    public static List getCidadeList() {
        List<Cidade> cidadeList = new ArrayList<>();
        TransactionUtil.begin(em);
        Query q = em.createQuery("select c from Cidade c order by c.cidade");
        cidadeList = (List<Cidade>) q.getResultList();

        return cidadeList;
    }
    
    public static List getCidadeListByLike(String filtro) {
        List<Cidade> cidadeList = new ArrayList<>();
        TransactionUtil.begin(em);
        Query q = em.createQuery("select c from Cidade c where upper(c.cidade) like '%"+filtro.toUpperCase()+"%' order by c.cidade");
        cidadeList = (List<Cidade>) q.getResultList();

        return cidadeList;
    }
    
    public static Cidade getCidadeByID(Integer id) {
        Cidade cidade;
        TransactionUtil.begin(em);
        Query q = em.createQuery("select c from Cidade c where c.id = "+id);
        cidade = (Cidade) q.getResultList().get(0);

        return cidade;
    }

    public static void insertCidade(Cidade cidade) {
        TransactionUtil.begin(em);
        em.persist(cidade);
        em.getTransaction().commit();
    }

    public static void deleteCidade(Cidade cidade) {
        TransactionUtil.begin(em);
        em.remove(cidade);
        em.getTransaction().commit();
    }

    public static void updateCidade(Cidade cidade) {
        TransactionUtil.begin(em);
        em.merge(cidade);
        em.getTransaction().commit();
    }

}
