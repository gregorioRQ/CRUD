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
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.RRHH;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
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
        if (tecnico.getIncidentes() == null) {
            tecnico.setIncidentes(new ArrayList<Incidente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RRHH rh = tecnico.getRh();
            if (rh != null) {
                rh = em.getReference(rh.getClass(), rh.getId());
                tecnico.setRh(rh);
            }
            ArrayList<Incidente> attachedIncidentes = new ArrayList<Incidente>();
            for (Incidente incidentesIncidenteToAttach : tecnico.getIncidentes()) {
                incidentesIncidenteToAttach = em.getReference(incidentesIncidenteToAttach.getClass(), incidentesIncidenteToAttach.getId());
                attachedIncidentes.add(incidentesIncidenteToAttach);
            }
            tecnico.setIncidentes(attachedIncidentes);
            em.persist(tecnico);
            if (rh != null) {
                rh.getTecnicos().add(tecnico);
                rh = em.merge(rh);
            }
            for (Incidente incidentesIncidente : tecnico.getIncidentes()) {
                Tecnico oldTecnicoOfIncidentesIncidente = incidentesIncidente.getTecnico();
                incidentesIncidente.setTecnico(tecnico);
                incidentesIncidente = em.merge(incidentesIncidente);
                if (oldTecnicoOfIncidentesIncidente != null) {
                    oldTecnicoOfIncidentesIncidente.getIncidentes().remove(incidentesIncidente);
                    oldTecnicoOfIncidentesIncidente = em.merge(oldTecnicoOfIncidentesIncidente);
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
            RRHH rhOld = persistentTecnico.getRh();
            RRHH rhNew = tecnico.getRh();
            ArrayList<Incidente> incidentesOld = persistentTecnico.getIncidentes();
            ArrayList<Incidente> incidentesNew = tecnico.getIncidentes();
            if (rhNew != null) {
                rhNew = em.getReference(rhNew.getClass(), rhNew.getId());
                tecnico.setRh(rhNew);
            }
            ArrayList<Incidente> attachedIncidentesNew = new ArrayList<Incidente>();
            for (Incidente incidentesNewIncidenteToAttach : incidentesNew) {
                incidentesNewIncidenteToAttach = em.getReference(incidentesNewIncidenteToAttach.getClass(), incidentesNewIncidenteToAttach.getId());
                attachedIncidentesNew.add(incidentesNewIncidenteToAttach);
            }
            incidentesNew = attachedIncidentesNew;
            tecnico.setIncidentes(incidentesNew);
            tecnico = em.merge(tecnico);
            if (rhOld != null && !rhOld.equals(rhNew)) {
                rhOld.getTecnicos().remove(tecnico);
                rhOld = em.merge(rhOld);
            }
            if (rhNew != null && !rhNew.equals(rhOld)) {
                rhNew.getTecnicos().add(tecnico);
                rhNew = em.merge(rhNew);
            }
            for (Incidente incidentesOldIncidente : incidentesOld) {
                if (!incidentesNew.contains(incidentesOldIncidente)) {
                    incidentesOldIncidente.setTecnico(null);
                    incidentesOldIncidente = em.merge(incidentesOldIncidente);
                }
            }
            for (Incidente incidentesNewIncidente : incidentesNew) {
                if (!incidentesOld.contains(incidentesNewIncidente)) {
                    Tecnico oldTecnicoOfIncidentesNewIncidente = incidentesNewIncidente.getTecnico();
                    incidentesNewIncidente.setTecnico(tecnico);
                    incidentesNewIncidente = em.merge(incidentesNewIncidente);
                    if (oldTecnicoOfIncidentesNewIncidente != null && !oldTecnicoOfIncidentesNewIncidente.equals(tecnico)) {
                        oldTecnicoOfIncidentesNewIncidente.getIncidentes().remove(incidentesNewIncidente);
                        oldTecnicoOfIncidentesNewIncidente = em.merge(oldTecnicoOfIncidentesNewIncidente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = tecnico.getId();
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

    public void destroy(long id) throws NonexistentEntityException {
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
            RRHH rh = tecnico.getRh();
            if (rh != null) {
                rh.getTecnicos().remove(tecnico);
                rh = em.merge(rh);
            }
            ArrayList<Incidente> incidentes = tecnico.getIncidentes();
            for (Incidente incidentesIncidente : incidentes) {
                incidentesIncidente.setTecnico(null);
                incidentesIncidente = em.merge(incidentesIncidente);
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

    public Tecnico findTecnico(long id) {
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
