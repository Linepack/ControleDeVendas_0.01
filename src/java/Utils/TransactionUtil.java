package Utils;

import javax.persistence.EntityManager;

/**
 *
 * @author leandro
 */
public class TransactionUtil {

    public static void begin(EntityManager em) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

}
