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
import modelos.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Cliente;

/**
 *
 * @author PC
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ClienteJpaController() {
         emf = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getListaServicios() == null) {
            cliente.setListaServicios(new ArrayList<Servicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Servicio> attachedListaServicios = new ArrayList<Servicio>();
            for (Servicio listaServiciosServicioToAttach : cliente.getListaServicios()) {
                listaServiciosServicioToAttach = em.getReference(listaServiciosServicioToAttach.getClass(), listaServiciosServicioToAttach.getId());
                attachedListaServicios.add(listaServiciosServicioToAttach);
            }
            cliente.setListaServicios(attachedListaServicios);
            em.persist(cliente);
            for (Servicio listaServiciosServicio : cliente.getListaServicios()) {
                Cliente oldClienteOfListaServiciosServicio = listaServiciosServicio.getCliente();
                listaServiciosServicio.setCliente(cliente);
                listaServiciosServicio = em.merge(listaServiciosServicio);
                if (oldClienteOfListaServiciosServicio != null) {
                    oldClienteOfListaServiciosServicio.getListaServicios().remove(listaServiciosServicio);
                    oldClienteOfListaServiciosServicio = em.merge(oldClienteOfListaServiciosServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            ArrayList<Servicio> listaServiciosOld = persistentCliente.getListaServicios();
            ArrayList<Servicio> listaServiciosNew = cliente.getListaServicios();
            ArrayList<Servicio> attachedListaServiciosNew = new ArrayList<Servicio>();
            for (Servicio listaServiciosNewServicioToAttach : listaServiciosNew) {
                listaServiciosNewServicioToAttach = em.getReference(listaServiciosNewServicioToAttach.getClass(), listaServiciosNewServicioToAttach.getId());
                attachedListaServiciosNew.add(listaServiciosNewServicioToAttach);
            }
            listaServiciosNew = attachedListaServiciosNew;
            cliente.setListaServicios(listaServiciosNew);
            cliente = em.merge(cliente);
            for (Servicio listaServiciosOldServicio : listaServiciosOld) {
                if (!listaServiciosNew.contains(listaServiciosOldServicio)) {
                    listaServiciosOldServicio.setCliente(null);
                    listaServiciosOldServicio = em.merge(listaServiciosOldServicio);
                }
            }
            for (Servicio listaServiciosNewServicio : listaServiciosNew) {
                if (!listaServiciosOld.contains(listaServiciosNewServicio)) {
                    Cliente oldClienteOfListaServiciosNewServicio = listaServiciosNewServicio.getCliente();
                    listaServiciosNewServicio.setCliente(cliente);
                    listaServiciosNewServicio = em.merge(listaServiciosNewServicio);
                    if (oldClienteOfListaServiciosNewServicio != null && !oldClienteOfListaServiciosNewServicio.equals(cliente)) {
                        oldClienteOfListaServiciosNewServicio.getListaServicios().remove(listaServiciosNewServicio);
                        oldClienteOfListaServiciosNewServicio = em.merge(oldClienteOfListaServiciosNewServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Servicio> listaServicios = cliente.getListaServicios();
            for (Servicio listaServiciosServicio : listaServicios) {
                listaServiciosServicio.setCliente(null);
                listaServiciosServicio = em.merge(listaServiciosServicio);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
