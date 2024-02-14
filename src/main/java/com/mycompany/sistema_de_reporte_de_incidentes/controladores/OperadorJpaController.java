/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.controladores;

import com.mycompany.sistema_de_reporte_de_incidentes.controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Operador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class OperadorJpaController implements Serializable {

    public OperadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public OperadorJpaController() {
        emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operador operador) {
        if (operador.getListaIncidentes() == null) {
            operador.setListaIncidentes(new ArrayList<Incidente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Incidente> attachedListaIncidentes = new ArrayList<Incidente>();
            for (Incidente listaIncidentesIncidenteToAttach : operador.getListaIncidentes()) {
                listaIncidentesIncidenteToAttach = em.getReference(listaIncidentesIncidenteToAttach.getClass(), listaIncidentesIncidenteToAttach.getId());
                attachedListaIncidentes.add(listaIncidentesIncidenteToAttach);
            }
            operador.setListaIncidentes(attachedListaIncidentes);
            em.persist(operador);
            for (Incidente listaIncidentesIncidente : operador.getListaIncidentes()) {
                Operador oldOperadorOfListaIncidentesIncidente = listaIncidentesIncidente.getOperador();
                listaIncidentesIncidente.setOperador(operador);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
                if (oldOperadorOfListaIncidentesIncidente != null) {
                    oldOperadorOfListaIncidentesIncidente.getListaIncidentes().remove(listaIncidentesIncidente);
                    oldOperadorOfListaIncidentesIncidente = em.merge(oldOperadorOfListaIncidentesIncidente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operador operador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operador persistentOperador = em.find(Operador.class, operador.getId());
            ArrayList<Incidente> listaIncidentesOld = persistentOperador.getListaIncidentes();
            ArrayList<Incidente> listaIncidentesNew = operador.getListaIncidentes();
            ArrayList<Incidente> attachedListaIncidentesNew = new ArrayList<Incidente>();
            for (Incidente listaIncidentesNewIncidenteToAttach : listaIncidentesNew) {
                listaIncidentesNewIncidenteToAttach = em.getReference(listaIncidentesNewIncidenteToAttach.getClass(), listaIncidentesNewIncidenteToAttach.getId());
                attachedListaIncidentesNew.add(listaIncidentesNewIncidenteToAttach);
            }
            listaIncidentesNew = attachedListaIncidentesNew;
            operador.setListaIncidentes(listaIncidentesNew);
            operador = em.merge(operador);
            for (Incidente listaIncidentesOldIncidente : listaIncidentesOld) {
                if (!listaIncidentesNew.contains(listaIncidentesOldIncidente)) {
                    listaIncidentesOldIncidente.setOperador(null);
                    listaIncidentesOldIncidente = em.merge(listaIncidentesOldIncidente);
                }
            }
            for (Incidente listaIncidentesNewIncidente : listaIncidentesNew) {
                if (!listaIncidentesOld.contains(listaIncidentesNewIncidente)) {
                    Operador oldOperadorOfListaIncidentesNewIncidente = listaIncidentesNewIncidente.getOperador();
                    listaIncidentesNewIncidente.setOperador(operador);
                    listaIncidentesNewIncidente = em.merge(listaIncidentesNewIncidente);
                    if (oldOperadorOfListaIncidentesNewIncidente != null && !oldOperadorOfListaIncidentesNewIncidente.equals(operador)) {
                        oldOperadorOfListaIncidentesNewIncidente.getListaIncidentes().remove(listaIncidentesNewIncidente);
                        oldOperadorOfListaIncidentesNewIncidente = em.merge(oldOperadorOfListaIncidentesNewIncidente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = operador.getId();
                if (findOperador(id) == null) {
                    throw new NonexistentEntityException("The operador with id " + id + " no longer exists.");
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
            Operador operador;
            try {
                operador = em.getReference(Operador.class, id);
                operador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operador with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Incidente> listaIncidentes = operador.getListaIncidentes();
            for (Incidente listaIncidentesIncidente : listaIncidentes) {
                listaIncidentesIncidente.setOperador(null);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
            }
            em.remove(operador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operador> findOperadorEntities() {
        return findOperadorEntities(true, -1, -1);
    }

    public List<Operador> findOperadorEntities(int maxResults, int firstResult) {
        return findOperadorEntities(false, maxResults, firstResult);
    }

    private List<Operador> findOperadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operador.class));
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

    public Operador findOperador(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operador.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operador> rt = cq.from(Operador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
