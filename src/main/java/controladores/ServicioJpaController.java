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
import modelos.Cliente;
import modelos.Incidente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Servicio;

/**
 *
 * @author PC
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
        if (servicio.getListaIncidentes() == null) {
            servicio.setListaIncidentes(new ArrayList<Incidente>());
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
            ArrayList<Incidente> attachedListaIncidentes = new ArrayList<Incidente>();
            for (Incidente listaIncidentesIncidenteToAttach : servicio.getListaIncidentes()) {
                listaIncidentesIncidenteToAttach = em.getReference(listaIncidentesIncidenteToAttach.getClass(), listaIncidentesIncidenteToAttach.getId());
                attachedListaIncidentes.add(listaIncidentesIncidenteToAttach);
            }
            servicio.setListaIncidentes(attachedListaIncidentes);
            em.persist(servicio);
            if (cliente != null) {
                cliente.getListaServicios().add(servicio);
                cliente = em.merge(cliente);
            }
            for (Incidente listaIncidentesIncidente : servicio.getListaIncidentes()) {
                listaIncidentesIncidente.getServicio().add(servicio);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
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
            ArrayList<Incidente> listaIncidentesOld = persistentServicio.getListaIncidentes();
            ArrayList<Incidente> listaIncidentesNew = servicio.getListaIncidentes();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                servicio.setCliente(clienteNew);
            }
            ArrayList<Incidente> attachedListaIncidentesNew = new ArrayList<Incidente>();
            for (Incidente listaIncidentesNewIncidenteToAttach : listaIncidentesNew) {
                listaIncidentesNewIncidenteToAttach = em.getReference(listaIncidentesNewIncidenteToAttach.getClass(), listaIncidentesNewIncidenteToAttach.getId());
                attachedListaIncidentesNew.add(listaIncidentesNewIncidenteToAttach);
            }
            listaIncidentesNew = attachedListaIncidentesNew;
            servicio.setListaIncidentes(listaIncidentesNew);
            servicio = em.merge(servicio);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getListaServicios().remove(servicio);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getListaServicios().add(servicio);
                clienteNew = em.merge(clienteNew);
            }
            for (Incidente listaIncidentesOldIncidente : listaIncidentesOld) {
                if (!listaIncidentesNew.contains(listaIncidentesOldIncidente)) {
                    listaIncidentesOldIncidente.getServicio().remove(servicio);
                    listaIncidentesOldIncidente = em.merge(listaIncidentesOldIncidente);
                }
            }
            for (Incidente listaIncidentesNewIncidente : listaIncidentesNew) {
                if (!listaIncidentesOld.contains(listaIncidentesNewIncidente)) {
                    listaIncidentesNewIncidente.getServicio().add(servicio);
                    listaIncidentesNewIncidente = em.merge(listaIncidentesNewIncidente);
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
            ArrayList<Incidente> listaIncidentes = servicio.getListaIncidentes();
            for (Incidente listaIncidentesIncidente : listaIncidentes) {
                listaIncidentesIncidente.getServicio().remove(servicio);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
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
