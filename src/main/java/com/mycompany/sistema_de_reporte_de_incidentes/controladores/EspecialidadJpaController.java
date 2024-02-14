/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.controladores;

import com.mycompany.sistema_de_reporte_de_incidentes.controladores.exceptions.NonexistentEntityException;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Especialidad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Tecnico;
import java.util.ArrayList;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.TipoDeProblema;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class EspecialidadJpaController implements Serializable {

    public EspecialidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EspecialidadJpaController() {
        emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especialidad especialidad) {
        if (especialidad.getTecnicos() == null) {
            especialidad.setTecnicos(new ArrayList<Tecnico>());
        }
        if (especialidad.getTiposDeProblemas() == null) {
            especialidad.setTiposDeProblemas(new ArrayList<TipoDeProblema>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Tecnico> attachedTecnicos = new ArrayList<Tecnico>();
            for (Tecnico tecnicosTecnicoToAttach : especialidad.getTecnicos()) {
                tecnicosTecnicoToAttach = em.getReference(tecnicosTecnicoToAttach.getClass(), tecnicosTecnicoToAttach.getId());
                attachedTecnicos.add(tecnicosTecnicoToAttach);
            }
            especialidad.setTecnicos(attachedTecnicos);
            ArrayList<TipoDeProblema> attachedTiposDeProblemas = new ArrayList<TipoDeProblema>();
            for (TipoDeProblema tiposDeProblemasTipoDeProblemaToAttach : especialidad.getTiposDeProblemas()) {
                tiposDeProblemasTipoDeProblemaToAttach = em.getReference(tiposDeProblemasTipoDeProblemaToAttach.getClass(), tiposDeProblemasTipoDeProblemaToAttach.getId());
                attachedTiposDeProblemas.add(tiposDeProblemasTipoDeProblemaToAttach);
            }
            especialidad.setTiposDeProblemas(attachedTiposDeProblemas);
            em.persist(especialidad);
            for (Tecnico tecnicosTecnico : especialidad.getTecnicos()) {
                tecnicosTecnico.getEspecialidades().add(especialidad);
                tecnicosTecnico = em.merge(tecnicosTecnico);
            }
            for (TipoDeProblema tiposDeProblemasTipoDeProblema : especialidad.getTiposDeProblemas()) {
                tiposDeProblemasTipoDeProblema.getEspecialidades().add(especialidad);
                tiposDeProblemasTipoDeProblema = em.merge(tiposDeProblemasTipoDeProblema);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especialidad especialidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidad persistentEspecialidad = em.find(Especialidad.class, especialidad.getId());
            ArrayList<Tecnico> tecnicosOld = persistentEspecialidad.getTecnicos();
            ArrayList<Tecnico> tecnicosNew = especialidad.getTecnicos();
            ArrayList<TipoDeProblema> tiposDeProblemasOld = persistentEspecialidad.getTiposDeProblemas();
            ArrayList<TipoDeProblema> tiposDeProblemasNew = especialidad.getTiposDeProblemas();
            ArrayList<Tecnico> attachedTecnicosNew = new ArrayList<Tecnico>();
            for (Tecnico tecnicosNewTecnicoToAttach : tecnicosNew) {
                tecnicosNewTecnicoToAttach = em.getReference(tecnicosNewTecnicoToAttach.getClass(), tecnicosNewTecnicoToAttach.getId());
                attachedTecnicosNew.add(tecnicosNewTecnicoToAttach);
            }
            tecnicosNew = attachedTecnicosNew;
            especialidad.setTecnicos(tecnicosNew);
            ArrayList<TipoDeProblema> attachedTiposDeProblemasNew = new ArrayList<TipoDeProblema>();
            for (TipoDeProblema tiposDeProblemasNewTipoDeProblemaToAttach : tiposDeProblemasNew) {
                tiposDeProblemasNewTipoDeProblemaToAttach = em.getReference(tiposDeProblemasNewTipoDeProblemaToAttach.getClass(), tiposDeProblemasNewTipoDeProblemaToAttach.getId());
                attachedTiposDeProblemasNew.add(tiposDeProblemasNewTipoDeProblemaToAttach);
            }
            tiposDeProblemasNew = attachedTiposDeProblemasNew;
            especialidad.setTiposDeProblemas(tiposDeProblemasNew);
            especialidad = em.merge(especialidad);
            for (Tecnico tecnicosOldTecnico : tecnicosOld) {
                if (!tecnicosNew.contains(tecnicosOldTecnico)) {
                    tecnicosOldTecnico.getEspecialidades().remove(especialidad);
                    tecnicosOldTecnico = em.merge(tecnicosOldTecnico);
                }
            }
            for (Tecnico tecnicosNewTecnico : tecnicosNew) {
                if (!tecnicosOld.contains(tecnicosNewTecnico)) {
                    tecnicosNewTecnico.getEspecialidades().add(especialidad);
                    tecnicosNewTecnico = em.merge(tecnicosNewTecnico);
                }
            }
            for (TipoDeProblema tiposDeProblemasOldTipoDeProblema : tiposDeProblemasOld) {
                if (!tiposDeProblemasNew.contains(tiposDeProblemasOldTipoDeProblema)) {
                    tiposDeProblemasOldTipoDeProblema.getEspecialidades().remove(especialidad);
                    tiposDeProblemasOldTipoDeProblema = em.merge(tiposDeProblemasOldTipoDeProblema);
                }
            }
            for (TipoDeProblema tiposDeProblemasNewTipoDeProblema : tiposDeProblemasNew) {
                if (!tiposDeProblemasOld.contains(tiposDeProblemasNewTipoDeProblema)) {
                    tiposDeProblemasNewTipoDeProblema.getEspecialidades().add(especialidad);
                    tiposDeProblemasNewTipoDeProblema = em.merge(tiposDeProblemasNewTipoDeProblema);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = especialidad.getId();
                if (findEspecialidad(id) == null) {
                    throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.");
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
            Especialidad especialidad;
            try {
                especialidad = em.getReference(Especialidad.class, id);
                especialidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Tecnico> tecnicos = especialidad.getTecnicos();
            for (Tecnico tecnicosTecnico : tecnicos) {
                tecnicosTecnico.getEspecialidades().remove(especialidad);
                tecnicosTecnico = em.merge(tecnicosTecnico);
            }
            ArrayList<TipoDeProblema> tiposDeProblemas = especialidad.getTiposDeProblemas();
            for (TipoDeProblema tiposDeProblemasTipoDeProblema : tiposDeProblemas) {
                tiposDeProblemasTipoDeProblema.getEspecialidades().remove(especialidad);
                tiposDeProblemasTipoDeProblema = em.merge(tiposDeProblemasTipoDeProblema);
            }
            em.remove(especialidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especialidad> findEspecialidadEntities() {
        return findEspecialidadEntities(true, -1, -1);
    }

    public List<Especialidad> findEspecialidadEntities(int maxResults, int firstResult) {
        return findEspecialidadEntities(false, maxResults, firstResult);
    }

    private List<Especialidad> findEspecialidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especialidad.class));
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

    public Especialidad findEspecialidad(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especialidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecialidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especialidad> rt = cq.from(Especialidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
