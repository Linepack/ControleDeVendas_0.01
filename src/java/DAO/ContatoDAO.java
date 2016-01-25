/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            
            if (contato.getPessoa().getId() == null){
                em.persist(contato.getPessoa());                
            }
            
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
