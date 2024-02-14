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
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.MesaDeAyuda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class MesaDeAyudaJpaController implements Serializable {

    public MesaDeAyudaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public MesaDeAyudaJpaController() {
        emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MesaDeAyuda mesaDeAyuda) {
        if (mesaDeAyuda.getIncidentes() == null) {
            mesaDeAyuda.setIncidentes(new ArrayList<Incidente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Incidente> attachedIncidentes = new ArrayList<Incidente>();
            for (Incidente incidentesIncidenteToAttach : mesaDeAyuda.getIncidentes()) {
                incidentesIncidenteToAttach = em.getReference(incidentesIncidenteToAttach.getClass(), incidentesIncidenteToAttach.getId());
                attachedIncidentes.add(incidentesIncidenteToAttach);
            }
            mesaDeAyuda.setIncidentes(attachedIncidentes);
            em.persist(mesaDeAyuda);
            for (Incidente incidentesIncidente : mesaDeAyuda.getIncidentes()) {
                MesaDeAyuda oldMesaDeAyudaOfIncidentesIncidente = incidentesIncidente.getMesaDeAyuda();
                incidentesIncidente.setMesaDeAyuda(mesaDeAyuda);
                incidentesIncidente = em.merge(incidentesIncidente);
                if (oldMesaDeAyudaOfIncidentesIncidente != null) {
                    oldMesaDeAyudaOfIncidentesIncidente.getIncidentes().remove(incidentesIncidente);
                    oldMesaDeAyudaOfIncidentesIncidente = em.merge(oldMesaDeAyudaOfIncidentesIncidente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MesaDeAyuda mesaDeAyuda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MesaDeAyuda persistentMesaDeAyuda = em.find(MesaDeAyuda.class, mesaDeAyuda.getId());
            ArrayList<Incidente> incidentesOld = persistentMesaDeAyuda.getIncidentes();
            ArrayList<Incidente> incidentesNew = mesaDeAyuda.getIncidentes();
            ArrayList<Incidente> attachedIncidentesNew = new ArrayList<Incidente>();
            for (Incidente incidentesNewIncidenteToAttach : incidentesNew) {
                incidentesNewIncidenteToAttach = em.getReference(incidentesNewIncidenteToAttach.getClass(), incidentesNewIncidenteToAttach.getId());
                attachedIncidentesNew.add(incidentesNewIncidenteToAttach);
            }
            incidentesNew = attachedIncidentesNew;
            mesaDeAyuda.setIncidentes(incidentesNew);
            mesaDeAyuda = em.merge(mesaDeAyuda);
            for (Incidente incidentesOldIncidente : incidentesOld) {
                if (!incidentesNew.contains(incidentesOldIncidente)) {
                    incidentesOldIncidente.setMesaDeAyuda(null);
                    incidentesOldIncidente = em.merge(incidentesOldIncidente);
                }
            }
            for (Incidente incidentesNewIncidente : incidentesNew) {
                if (!incidentesOld.contains(incidentesNewIncidente)) {
                    MesaDeAyuda oldMesaDeAyudaOfIncidentesNewIncidente = incidentesNewIncidente.getMesaDeAyuda();
                    incidentesNewIncidente.setMesaDeAyuda(mesaDeAyuda);
                    incidentesNewIncidente = em.merge(incidentesNewIncidente);
                    if (oldMesaDeAyudaOfIncidentesNewIncidente != null && !oldMesaDeAyudaOfIncidentesNewIncidente.equals(mesaDeAyuda)) {
                        oldMesaDeAyudaOfIncidentesNewIncidente.getIncidentes().remove(incidentesNewIncidente);
                        oldMesaDeAyudaOfIncidentesNewIncidente = em.merge(oldMesaDeAyudaOfIncidentesNewIncidente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = mesaDeAyuda.getId();
                if (findMesaDeAyuda(id) == null) {
                    throw new NonexistentEntityException("The mesaDeAyuda with id " + id + " no longer exists.");
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
            MesaDeAyuda mesaDeAyuda;
            try {
                mesaDeAyuda = em.getReference(MesaDeAyuda.class, id);
                mesaDeAyuda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mesaDeAyuda with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Incidente> incidentes = mesaDeAyuda.getIncidentes();
            for (Incidente incidentesIncidente : incidentes) {
                incidentesIncidente.setMesaDeAyuda(null);
                incidentesIncidente = em.merge(incidentesIncidente);
            }
            em.remove(mesaDeAyuda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MesaDeAyuda> findMesaDeAyudaEntities() {
        return findMesaDeAyudaEntities(true, -1, -1);
    }

    public List<MesaDeAyuda> findMesaDeAyudaEntities(int maxResults, int firstResult) {
        return findMesaDeAyudaEntities(false, maxResults, firstResult);
    }

    private List<MesaDeAyuda> findMesaDeAyudaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MesaDeAyuda.class));
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

    public MesaDeAyuda findMesaDeAyuda(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MesaDeAyuda.class, id);
        } finally {
            em.close();
        }
    }

    public int getMesaDeAyudaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MesaDeAyuda> rt = cq.from(MesaDeAyuda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
