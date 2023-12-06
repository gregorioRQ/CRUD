/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Incidente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Tecnico;

/**
 *
 * @author PC
 */
public class TecnicoJpaController implements Serializable {

    public TecnicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TecnicoJpaController() {
         emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tecnico tecnico) {
        if (tecnico.getListaIncidentes() == null) {
            tecnico.setListaIncidentes(new ArrayList<Incidente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Incidente> attachedListaIncidentes = new ArrayList<Incidente>();
            for (Incidente listaIncidentesIncidenteToAttach : tecnico.getListaIncidentes()) {
                listaIncidentesIncidenteToAttach = em.getReference(listaIncidentesIncidenteToAttach.getClass(), listaIncidentesIncidenteToAttach.getId());
                attachedListaIncidentes.add(listaIncidentesIncidenteToAttach);
            }
            tecnico.setListaIncidentes(attachedListaIncidentes);
            em.persist(tecnico);
            for (Incidente listaIncidentesIncidente : tecnico.getListaIncidentes()) {
                Tecnico oldTecnicoOfListaIncidentesIncidente = listaIncidentesIncidente.getTecnico();
                listaIncidentesIncidente.setTecnico(tecnico);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
                if (oldTecnicoOfListaIncidentesIncidente != null) {
                    oldTecnicoOfListaIncidentesIncidente.getListaIncidentes().remove(listaIncidentesIncidente);
                    oldTecnicoOfListaIncidentesIncidente = em.merge(oldTecnicoOfListaIncidentesIncidente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tecnico tecnico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnico persistentTecnico = em.find(Tecnico.class, tecnico.getId());
            ArrayList<Incidente> listaIncidentesOld = persistentTecnico.getListaIncidentes();
            ArrayList<Incidente> listaIncidentesNew = tecnico.getListaIncidentes();
            ArrayList<Incidente> attachedListaIncidentesNew = new ArrayList<Incidente>();
            for (Incidente listaIncidentesNewIncidenteToAttach : listaIncidentesNew) {
                listaIncidentesNewIncidenteToAttach = em.getReference(listaIncidentesNewIncidenteToAttach.getClass(), listaIncidentesNewIncidenteToAttach.getId());
                attachedListaIncidentesNew.add(listaIncidentesNewIncidenteToAttach);
            }
            listaIncidentesNew = attachedListaIncidentesNew;
            tecnico.setListaIncidentes(listaIncidentesNew);
            tecnico = em.merge(tecnico);
            for (Incidente listaIncidentesOldIncidente : listaIncidentesOld) {
                if (!listaIncidentesNew.contains(listaIncidentesOldIncidente)) {
                    listaIncidentesOldIncidente.setTecnico(null);
                    listaIncidentesOldIncidente = em.merge(listaIncidentesOldIncidente);
                }
            }
            for (Incidente listaIncidentesNewIncidente : listaIncidentesNew) {
                if (!listaIncidentesOld.contains(listaIncidentesNewIncidente)) {
                    Tecnico oldTecnicoOfListaIncidentesNewIncidente = listaIncidentesNewIncidente.getTecnico();
                    listaIncidentesNewIncidente.setTecnico(tecnico);
                    listaIncidentesNewIncidente = em.merge(listaIncidentesNewIncidente);
                    if (oldTecnicoOfListaIncidentesNewIncidente != null && !oldTecnicoOfListaIncidentesNewIncidente.equals(tecnico)) {
                        oldTecnicoOfListaIncidentesNewIncidente.getListaIncidentes().remove(listaIncidentesNewIncidente);
                        oldTecnicoOfListaIncidentesNewIncidente = em.merge(oldTecnicoOfListaIncidentesNewIncidente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tecnico.getId();
                if (findTecnico(id) == null) {
                    throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.");
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
            Tecnico tecnico;
            try {
                tecnico = em.getReference(Tecnico.class, id);
                tecnico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Incidente> listaIncidentes = tecnico.getListaIncidentes();
            for (Incidente listaIncidentesIncidente : listaIncidentes) {
                listaIncidentesIncidente.setTecnico(null);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
            }
            em.remove(tecnico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tecnico> findTecnicoEntities() {
        return findTecnicoEntities(true, -1, -1);
    }

    public List<Tecnico> findTecnicoEntities(int maxResults, int firstResult) {
        return findTecnicoEntities(false, maxResults, firstResult);
    }

    private List<Tecnico> findTecnicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tecnico.class));
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

    public Tecnico findTecnico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tecnico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTecnicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tecnico> rt = cq.from(Tecnico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
