/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.controladores;

import com.mycompany.sistema_de_reporte_de_incidentes.controladores.exceptions.NonexistentEntityException;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.RRHH;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Tecnico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class RRHHJpaController implements Serializable {

    public RRHHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RRHHJpaController() {
        emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
     
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RRHH RRHH) {
        if (RRHH.getTecnicos() == null) {
            RRHH.setTecnicos(new ArrayList<Tecnico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Tecnico> attachedTecnicos = new ArrayList<Tecnico>();
            for (Tecnico tecnicosTecnicoToAttach : RRHH.getTecnicos()) {
                tecnicosTecnicoToAttach = em.getReference(tecnicosTecnicoToAttach.getClass(), tecnicosTecnicoToAttach.getId());
                attachedTecnicos.add(tecnicosTecnicoToAttach);
            }
            RRHH.setTecnicos(attachedTecnicos);
            em.persist(RRHH);
            for (Tecnico tecnicosTecnico : RRHH.getTecnicos()) {
                RRHH oldRhOfTecnicosTecnico = tecnicosTecnico.getRh();
                tecnicosTecnico.setRh(RRHH);
                tecnicosTecnico = em.merge(tecnicosTecnico);
                if (oldRhOfTecnicosTecnico != null) {
                    oldRhOfTecnicosTecnico.getTecnicos().remove(tecnicosTecnico);
                    oldRhOfTecnicosTecnico = em.merge(oldRhOfTecnicosTecnico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RRHH RRHH) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RRHH persistentRRHH = em.find(RRHH.class, RRHH.getId());
            ArrayList<Tecnico> tecnicosOld = persistentRRHH.getTecnicos();
            ArrayList<Tecnico> tecnicosNew = RRHH.getTecnicos();
            ArrayList<Tecnico> attachedTecnicosNew = new ArrayList<Tecnico>();
            for (Tecnico tecnicosNewTecnicoToAttach : tecnicosNew) {
                tecnicosNewTecnicoToAttach = em.getReference(tecnicosNewTecnicoToAttach.getClass(), tecnicosNewTecnicoToAttach.getId());
                attachedTecnicosNew.add(tecnicosNewTecnicoToAttach);
            }
            tecnicosNew = attachedTecnicosNew;
            RRHH.setTecnicos(tecnicosNew);
            RRHH = em.merge(RRHH);
            for (Tecnico tecnicosOldTecnico : tecnicosOld) {
                if (!tecnicosNew.contains(tecnicosOldTecnico)) {
                    tecnicosOldTecnico.setRh(null);
                    tecnicosOldTecnico = em.merge(tecnicosOldTecnico);
                }
            }
            for (Tecnico tecnicosNewTecnico : tecnicosNew) {
                if (!tecnicosOld.contains(tecnicosNewTecnico)) {
                    RRHH oldRhOfTecnicosNewTecnico = tecnicosNewTecnico.getRh();
                    tecnicosNewTecnico.setRh(RRHH);
                    tecnicosNewTecnico = em.merge(tecnicosNewTecnico);
                    if (oldRhOfTecnicosNewTecnico != null && !oldRhOfTecnicosNewTecnico.equals(RRHH)) {
                        oldRhOfTecnicosNewTecnico.getTecnicos().remove(tecnicosNewTecnico);
                        oldRhOfTecnicosNewTecnico = em.merge(oldRhOfTecnicosNewTecnico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = RRHH.getId();
                if (findRRHH(id) == null) {
                    throw new NonexistentEntityException("The rRHH with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RRHH RRHH;
            try {
                RRHH = em.getReference(RRHH.class, id);
                RRHH.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The RRHH with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Tecnico> tecnicos = RRHH.getTecnicos();
            for (Tecnico tecnicosTecnico : tecnicos) {
                tecnicosTecnico.setRh(null);
                tecnicosTecnico = em.merge(tecnicosTecnico);
            }
            em.remove(RRHH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RRHH> findRRHHEntities() {
        return findRRHHEntities(true, -1, -1);
    }

    public List<RRHH> findRRHHEntities(int maxResults, int firstResult) {
        return findRRHHEntities(false, maxResults, firstResult);
    }

    private List<RRHH> findRRHHEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RRHH.class));
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

    public RRHH findRRHH(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RRHH.class, id);
        } finally {
            em.close();
        }
    }

    public int getRRHHCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RRHH> rt = cq.from(RRHH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
