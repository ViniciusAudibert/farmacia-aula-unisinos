package br.com.farmacia.farmacia;

import br.com.farmacia.farmacia.entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.SessionFactory;

/**
 *
 * @author vini
 */
public class ConnectionFactory {
    
    private EntityManager entityManager;
    private EntityManagerFactory sessionFactory;

    public ConnectionFactory () {
        sessionFactory = Persistence.createEntityManagerFactory("br.com.farmacia_farmacia_jar_1.0-SNAPSHOTPU");
    }
    
    protected void setUp() throws Exception {
        
        sessionFactory = Persistence.createEntityManagerFactory("br.com.farmacia_farmacia_jar_1.0-SNAPSHOTPU");
        entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Produto());
        entityManager.persist(new ProdutoEntrada());
        entityManager.persist(new ProdutoMovimentacao());
        entityManager.persist(new Fornecedor());
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public EntityManagerFactory getSessionFactory(){ 
        return sessionFactory;
    }
    
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
}
