/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmacia.farmacia.entity;

import br.com.farmacia.farmacia.entity.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author vini
 */
public class ProdutoEntradaJpaController implements Serializable {

    public ProdutoEntradaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(ProdutoEntrada produtoEntrada) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produtoEntrada);
            em.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProdutoEntrada produtoEntrada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            produtoEntrada = em.merge(produtoEntrada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = produtoEntrada.getId();
                if (findProdutoEntrada(id) == null) {
                    throw new NonexistentEntityException("The produtoEntrada with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProdutoEntrada produtoEntrada;
            try {
                produtoEntrada = em.getReference(ProdutoEntrada.class, id);
                produtoEntrada.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produtoEntrada with id " + id + " no longer exists.", enfe);
            }
            em.remove(produtoEntrada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProdutoEntrada> findProdutoEntradaEntities() {
        return findProdutoEntradaEntities(true, -1, -1);
    }

    public List<ProdutoEntrada> findProdutoEntradaEntities(int maxResults, int firstResult) {
        return findProdutoEntradaEntities(false, maxResults, firstResult);
    }

    private List<ProdutoEntrada> findProdutoEntradaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProdutoEntrada.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProdutoEntrada findProdutoEntrada(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProdutoEntrada.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoEntradaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProdutoEntrada> rt = cq.from(ProdutoEntrada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
