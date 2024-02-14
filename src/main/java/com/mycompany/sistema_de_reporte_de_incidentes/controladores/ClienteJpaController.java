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
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.AreaComercial;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Servicio;
import java.util.ArrayList;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
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
        if (cliente.getListaIncidentes() == null) {
            cliente.setListaIncidentes(new ArrayList<Incidente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaComercial areaComercial = cliente.getAreaComercial();
            if (areaComercial != null) {
                areaComercial = em.getReference(areaComercial.getClass(), areaComercial.getId());
                cliente.setAreaComercial(areaComercial);
            }
            ArrayList<Servicio> attachedListaServicios = new ArrayList<Servicio>();
            for (Servicio listaServiciosServicioToAttach : cliente.getListaServicios()) {
                listaServiciosServicioToAttach = em.getReference(listaServiciosServicioToAttach.getClass(), listaServiciosServicioToAttach.getId());
                attachedListaServicios.add(listaServiciosServicioToAttach);
            }
            cliente.setListaServicios(attachedListaServicios);
            ArrayList<Incidente> attachedListaIncidentes = new ArrayList<Incidente>();
            for (Incidente listaIncidentesIncidenteToAttach : cliente.getListaIncidentes()) {
                listaIncidentesIncidenteToAttach = em.getReference(listaIncidentesIncidenteToAttach.getClass(), listaIncidentesIncidenteToAttach.getId());
                attachedListaIncidentes.add(listaIncidentesIncidenteToAttach);
            }
            cliente.setListaIncidentes(attachedListaIncidentes);
            em.persist(cliente);
            if (areaComercial != null) {
                areaComercial.getClientes().add(cliente);
                areaComercial = em.merge(areaComercial);
            }
            for (Servicio listaServiciosServicio : cliente.getListaServicios()) {
                Cliente oldClienteOfListaServiciosServicio = listaServiciosServicio.getCliente();
                listaServiciosServicio.setCliente(cliente);
                listaServiciosServicio = em.merge(listaServiciosServicio);
                if (oldClienteOfListaServiciosServicio != null) {
                    oldClienteOfListaServiciosServicio.getListaServicios().remove(listaServiciosServicio);
                    oldClienteOfListaServiciosServicio = em.merge(oldClienteOfListaServiciosServicio);
                }
            }
            for (Incidente listaIncidentesIncidente : cliente.getListaIncidentes()) {
                Cliente oldClienteOfListaIncidentesIncidente = listaIncidentesIncidente.getCliente();
                listaIncidentesIncidente.setCliente(cliente);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
                if (oldClienteOfListaIncidentesIncidente != null) {
                    oldClienteOfListaIncidentesIncidente.getListaIncidentes().remove(listaIncidentesIncidente);
                    oldClienteOfListaIncidentesIncidente = em.merge(oldClienteOfListaIncidentesIncidente);
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
            AreaComercial areaComercialOld = persistentCliente.getAreaComercial();
            AreaComercial areaComercialNew = cliente.getAreaComercial();
            ArrayList<Servicio> listaServiciosOld = persistentCliente.getListaServicios();
            ArrayList<Servicio> listaServiciosNew = cliente.getListaServicios();
            ArrayList<Incidente> listaIncidentesOld = persistentCliente.getListaIncidentes();
            ArrayList<Incidente> listaIncidentesNew = cliente.getListaIncidentes();
            if (areaComercialNew != null) {
                areaComercialNew = em.getReference(areaComercialNew.getClass(), areaComercialNew.getId());
                cliente.setAreaComercial(areaComercialNew);
            }
            ArrayList<Servicio> attachedListaServiciosNew = new ArrayList<Servicio>();
            for (Servicio listaServiciosNewServicioToAttach : listaServiciosNew) {
                listaServiciosNewServicioToAttach = em.getReference(listaServiciosNewServicioToAttach.getClass(), listaServiciosNewServicioToAttach.getId());
                attachedListaServiciosNew.add(listaServiciosNewServicioToAttach);
            }
            listaServiciosNew = attachedListaServiciosNew;
            cliente.setListaServicios(listaServiciosNew);
            ArrayList<Incidente> attachedListaIncidentesNew = new ArrayList<Incidente>();
            for (Incidente listaIncidentesNewIncidenteToAttach : listaIncidentesNew) {
                listaIncidentesNewIncidenteToAttach = em.getReference(listaIncidentesNewIncidenteToAttach.getClass(), listaIncidentesNewIncidenteToAttach.getId());
                attachedListaIncidentesNew.add(listaIncidentesNewIncidenteToAttach);
            }
            listaIncidentesNew = attachedListaIncidentesNew;
            cliente.setListaIncidentes(listaIncidentesNew);
            cliente = em.merge(cliente);
            if (areaComercialOld != null && !areaComercialOld.equals(areaComercialNew)) {
                areaComercialOld.getClientes().remove(cliente);
                areaComercialOld = em.merge(areaComercialOld);
            }
            if (areaComercialNew != null && !areaComercialNew.equals(areaComercialOld)) {
                areaComercialNew.getClientes().add(cliente);
                areaComercialNew = em.merge(areaComercialNew);
            }
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
            for (Incidente listaIncidentesOldIncidente : listaIncidentesOld) {
                if (!listaIncidentesNew.contains(listaIncidentesOldIncidente)) {
                    listaIncidentesOldIncidente.setCliente(null);
                    listaIncidentesOldIncidente = em.merge(listaIncidentesOldIncidente);
                }
            }
            for (Incidente listaIncidentesNewIncidente : listaIncidentesNew) {
                if (!listaIncidentesOld.contains(listaIncidentesNewIncidente)) {
                    Cliente oldClienteOfListaIncidentesNewIncidente = listaIncidentesNewIncidente.getCliente();
                    listaIncidentesNewIncidente.setCliente(cliente);
                    listaIncidentesNewIncidente = em.merge(listaIncidentesNewIncidente);
                    if (oldClienteOfListaIncidentesNewIncidente != null && !oldClienteOfListaIncidentesNewIncidente.equals(cliente)) {
                        oldClienteOfListaIncidentesNewIncidente.getListaIncidentes().remove(listaIncidentesNewIncidente);
                        oldClienteOfListaIncidentesNewIncidente = em.merge(oldClienteOfListaIncidentesNewIncidente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = cliente.getId();
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

    public void destroy(long id) throws NonexistentEntityException {
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
            AreaComercial areaComercial = cliente.getAreaComercial();
            if (areaComercial != null) {
                areaComercial.getClientes().remove(cliente);
                areaComercial = em.merge(areaComercial);
            }
            ArrayList<Servicio> listaServicios = cliente.getListaServicios();
            for (Servicio listaServiciosServicio : listaServicios) {
                listaServiciosServicio.setCliente(null);
                listaServiciosServicio = em.merge(listaServiciosServicio);
            }
            ArrayList<Incidente> listaIncidentes = cliente.getListaIncidentes();
            for (Incidente listaIncidentesIncidente : listaIncidentes) {
                listaIncidentesIncidente.setCliente(null);
                listaIncidentesIncidente = em.merge(listaIncidentesIncidente);
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

    public Cliente findCliente(long id) {
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
