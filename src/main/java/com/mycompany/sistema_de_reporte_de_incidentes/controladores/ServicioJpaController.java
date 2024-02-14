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
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Servicio;
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
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ServicioJpaController() {
        emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getTiposDeProblemas() == null) {
            servicio.setTiposDeProblemas(new ArrayList<TipoDeProblema>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = servicio.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                servicio.setCliente(cliente);
            }
            ArrayList<TipoDeProblema> attachedTiposDeProblemas = new ArrayList<TipoDeProblema>();
            for (TipoDeProblema tiposDeProblemasTipoDeProblemaToAttach : servicio.getTiposDeProblemas()) {
                tiposDeProblemasTipoDeProblemaToAttach = em.getReference(tiposDeProblemasTipoDeProblemaToAttach.getClass(), tiposDeProblemasTipoDeProblemaToAttach.getId());
                attachedTiposDeProblemas.add(tiposDeProblemasTipoDeProblemaToAttach);
            }
            servicio.setTiposDeProblemas(attachedTiposDeProblemas);
            em.persist(servicio);
            if (cliente != null) {
                cliente.getListaServicios().add(servicio);
                cliente = em.merge(cliente);
            }
            for (TipoDeProblema tiposDeProblemasTipoDeProblema : servicio.getTiposDeProblemas()) {
                Servicio oldServicioOfTiposDeProblemasTipoDeProblema = tiposDeProblemasTipoDeProblema.getServicio();
                tiposDeProblemasTipoDeProblema.setServicio(servicio);
                tiposDeProblemasTipoDeProblema = em.merge(tiposDeProblemasTipoDeProblema);
                if (oldServicioOfTiposDeProblemasTipoDeProblema != null) {
                    oldServicioOfTiposDeProblemasTipoDeProblema.getTiposDeProblemas().remove(tiposDeProblemasTipoDeProblema);
                    oldServicioOfTiposDeProblemasTipoDeProblema = em.merge(oldServicioOfTiposDeProblemasTipoDeProblema);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getId());
            Cliente clienteOld = persistentServicio.getCliente();
            Cliente clienteNew = servicio.getCliente();
            ArrayList<TipoDeProblema> tiposDeProblemasOld = persistentServicio.getTiposDeProblemas();
            ArrayList<TipoDeProblema> tiposDeProblemasNew = servicio.getTiposDeProblemas();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                servicio.setCliente(clienteNew);
            }
            ArrayList<TipoDeProblema> attachedTiposDeProblemasNew = new ArrayList<TipoDeProblema>();
            for (TipoDeProblema tiposDeProblemasNewTipoDeProblemaToAttach : tiposDeProblemasNew) {
                tiposDeProblemasNewTipoDeProblemaToAttach = em.getReference(tiposDeProblemasNewTipoDeProblemaToAttach.getClass(), tiposDeProblemasNewTipoDeProblemaToAttach.getId());
                attachedTiposDeProblemasNew.add(tiposDeProblemasNewTipoDeProblemaToAttach);
            }
            tiposDeProblemasNew = attachedTiposDeProblemasNew;
            servicio.setTiposDeProblemas(tiposDeProblemasNew);
            servicio = em.merge(servicio);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getListaServicios().remove(servicio);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getListaServicios().add(servicio);
                clienteNew = em.merge(clienteNew);
            }
            for (TipoDeProblema tiposDeProblemasOldTipoDeProblema : tiposDeProblemasOld) {
                if (!tiposDeProblemasNew.contains(tiposDeProblemasOldTipoDeProblema)) {
                    tiposDeProblemasOldTipoDeProblema.setServicio(null);
                    tiposDeProblemasOldTipoDeProblema = em.merge(tiposDeProblemasOldTipoDeProblema);
                }
            }
            for (TipoDeProblema tiposDeProblemasNewTipoDeProblema : tiposDeProblemasNew) {
                if (!tiposDeProblemasOld.contains(tiposDeProblemasNewTipoDeProblema)) {
                    Servicio oldServicioOfTiposDeProblemasNewTipoDeProblema = tiposDeProblemasNewTipoDeProblema.getServicio();
                    tiposDeProblemasNewTipoDeProblema.setServicio(servicio);
                    tiposDeProblemasNewTipoDeProblema = em.merge(tiposDeProblemasNewTipoDeProblema);
                    if (oldServicioOfTiposDeProblemasNewTipoDeProblema != null && !oldServicioOfTiposDeProblemasNewTipoDeProblema.equals(servicio)) {
                        oldServicioOfTiposDeProblemasNewTipoDeProblema.getTiposDeProblemas().remove(tiposDeProblemasNewTipoDeProblema);
                        oldServicioOfTiposDeProblemasNewTipoDeProblema = em.merge(oldServicioOfTiposDeProblemasNewTipoDeProblema);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getId();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = servicio.getCliente();
            if (cliente != null) {
                cliente.getListaServicios().remove(servicio);
                cliente = em.merge(cliente);
            }
            ArrayList<TipoDeProblema> tiposDeProblemas = servicio.getTiposDeProblemas();
            for (TipoDeProblema tiposDeProblemasTipoDeProblema : tiposDeProblemas) {
                tiposDeProblemasTipoDeProblema.setServicio(null);
                tiposDeProblemasTipoDeProblema = em.merge(tiposDeProblemasTipoDeProblema);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
