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
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Servicio;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Especialidad;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.TipoDeProblema;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class TipoDeProblemaJpaController implements Serializable {

    public TipoDeProblemaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public TipoDeProblemaJpaController() {
        emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }

    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDeProblema tipoDeProblema) {
        if (tipoDeProblema.getEspecialidades() == null) {
            tipoDeProblema.setEspecialidades(new ArrayList<Especialidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicio = tipoDeProblema.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getId());
                tipoDeProblema.setServicio(servicio);
            }
            ArrayList<Especialidad> attachedEspecialidades = new ArrayList<Especialidad>();
            for (Especialidad especialidadesEspecialidadToAttach : tipoDeProblema.getEspecialidades()) {
                especialidadesEspecialidadToAttach = em.getReference(especialidadesEspecialidadToAttach.getClass(), especialidadesEspecialidadToAttach.getId());
                attachedEspecialidades.add(especialidadesEspecialidadToAttach);
            }
            tipoDeProblema.setEspecialidades(attachedEspecialidades);
            em.persist(tipoDeProblema);
            if (servicio != null) {
                servicio.getTiposDeProblemas().add(tipoDeProblema);
                servicio = em.merge(servicio);
            }
            for (Especialidad especialidadesEspecialidad : tipoDeProblema.getEspecialidades()) {
                especialidadesEspecialidad.getTiposDeProblemas().add(tipoDeProblema);
                especialidadesEspecialidad = em.merge(especialidadesEspecialidad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDeProblema tipoDeProblema) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDeProblema persistentTipoDeProblema = em.find(TipoDeProblema.class, tipoDeProblema.getId());
            Servicio servicioOld = persistentTipoDeProblema.getServicio();
            Servicio servicioNew = tipoDeProblema.getServicio();
            ArrayList<Especialidad> especialidadesOld = persistentTipoDeProblema.getEspecialidades();
            ArrayList<Especialidad> especialidadesNew = tipoDeProblema.getEspecialidades();
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getId());
                tipoDeProblema.setServicio(servicioNew);
            }
            ArrayList<Especialidad> attachedEspecialidadesNew = new ArrayList<Especialidad>();
            for (Especialidad especialidadesNewEspecialidadToAttach : especialidadesNew) {
                especialidadesNewEspecialidadToAttach = em.getReference(especialidadesNewEspecialidadToAttach.getClass(), especialidadesNewEspecialidadToAttach.getId());
                attachedEspecialidadesNew.add(especialidadesNewEspecialidadToAttach);
            }
            especialidadesNew = attachedEspecialidadesNew;
            tipoDeProblema.setEspecialidades(especialidadesNew);
            tipoDeProblema = em.merge(tipoDeProblema);
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getTiposDeProblemas().remove(tipoDeProblema);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getTiposDeProblemas().add(tipoDeProblema);
                servicioNew = em.merge(servicioNew);
            }
            for (Especialidad especialidadesOldEspecialidad : especialidadesOld) {
                if (!especialidadesNew.contains(especialidadesOldEspecialidad)) {
                    especialidadesOldEspecialidad.getTiposDeProblemas().remove(tipoDeProblema);
                    especialidadesOldEspecialidad = em.merge(especialidadesOldEspecialidad);
                }
            }
            for (Especialidad especialidadesNewEspecialidad : especialidadesNew) {
                if (!especialidadesOld.contains(especialidadesNewEspecialidad)) {
                    especialidadesNewEspecialidad.getTiposDeProblemas().add(tipoDeProblema);
                    especialidadesNewEspecialidad = em.merge(especialidadesNewEspecialidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = tipoDeProblema.getId();
                if (findTipoDeProblema(id) == null) {
                    throw new NonexistentEntityException("The tipoDeProblema with id " + id + " no longer exists.");
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
            TipoDeProblema tipoDeProblema;
            try {
                tipoDeProblema = em.getReference(TipoDeProblema.class, id);
                tipoDeProblema.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDeProblema with id " + id + " no longer exists.", enfe);
            }
            Servicio servicio = tipoDeProblema.getServicio();
            if (servicio != null) {
                servicio.getTiposDeProblemas().remove(tipoDeProblema);
                servicio = em.merge(servicio);
            }
            ArrayList<Especialidad> especialidades = tipoDeProblema.getEspecialidades();
            for (Especialidad especialidadesEspecialidad : especialidades) {
                especialidadesEspecialidad.getTiposDeProblemas().remove(tipoDeProblema);
                especialidadesEspecialidad = em.merge(especialidadesEspecialidad);
            }
            em.remove(tipoDeProblema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDeProblema> findTipoDeProblemaEntities() {
        return findTipoDeProblemaEntities(true, -1, -1);
    }

    public List<TipoDeProblema> findTipoDeProblemaEntities(int maxResults, int firstResult) {
        return findTipoDeProblemaEntities(false, maxResults, firstResult);
    }

    private List<TipoDeProblema> findTipoDeProblemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDeProblema.class));
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

    public TipoDeProblema findTipoDeProblema(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDeProblema.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDeProblemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDeProblema> rt = cq.from(TipoDeProblema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
