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
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Tecnico;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Operador;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.MesaDeAyuda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafae
 */
public class IncidenteJpaController implements Serializable {

    public IncidenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public IncidenteJpaController() {
        emf  = Persistence.createEntityManagerFactory("sriJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Incidente incidente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnico tecnico = incidente.getTecnico();
            if (tecnico != null) {
                tecnico = em.getReference(tecnico.getClass(), tecnico.getId());
                incidente.setTecnico(tecnico);
            }
            Operador operador = incidente.getOperador();
            if (operador != null) {
                operador = em.getReference(operador.getClass(), operador.getId());
                incidente.setOperador(operador);
            }
            Cliente cliente = incidente.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                incidente.setCliente(cliente);
            }
            MesaDeAyuda mesaDeAyuda = incidente.getMesaDeAyuda();
            if (mesaDeAyuda != null) {
                mesaDeAyuda = em.getReference(mesaDeAyuda.getClass(), mesaDeAyuda.getId());
                incidente.setMesaDeAyuda(mesaDeAyuda);
            }
            em.persist(incidente);
            if (tecnico != null) {
                tecnico.getIncidentes().add(incidente);
                tecnico = em.merge(tecnico);
            }
            if (operador != null) {
                operador.getListaIncidentes().add(incidente);
                operador = em.merge(operador);
            }
            if (cliente != null) {
                cliente.getListaIncidentes().add(incidente);
                cliente = em.merge(cliente);
            }
            if (mesaDeAyuda != null) {
                mesaDeAyuda.getIncidentes().add(incidente);
                mesaDeAyuda = em.merge(mesaDeAyuda);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Incidente incidente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Incidente persistentIncidente = em.find(Incidente.class, incidente.getId());
            Tecnico tecnicoOld = persistentIncidente.getTecnico();
            Tecnico tecnicoNew = incidente.getTecnico();
            Operador operadorOld = persistentIncidente.getOperador();
            Operador operadorNew = incidente.getOperador();
            Cliente clienteOld = persistentIncidente.getCliente();
            Cliente clienteNew = incidente.getCliente();
            MesaDeAyuda mesaDeAyudaOld = persistentIncidente.getMesaDeAyuda();
            MesaDeAyuda mesaDeAyudaNew = incidente.getMesaDeAyuda();
            if (tecnicoNew != null) {
                tecnicoNew = em.getReference(tecnicoNew.getClass(), tecnicoNew.getId());
                incidente.setTecnico(tecnicoNew);
            }
            if (operadorNew != null) {
                operadorNew = em.getReference(operadorNew.getClass(), operadorNew.getId());
                incidente.setOperador(operadorNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                incidente.setCliente(clienteNew);
            }
            if (mesaDeAyudaNew != null) {
                mesaDeAyudaNew = em.getReference(mesaDeAyudaNew.getClass(), mesaDeAyudaNew.getId());
                incidente.setMesaDeAyuda(mesaDeAyudaNew);
            }
            incidente = em.merge(incidente);
            if (tecnicoOld != null && !tecnicoOld.equals(tecnicoNew)) {
                tecnicoOld.getIncidentes().remove(incidente);
                tecnicoOld = em.merge(tecnicoOld);
            }
            if (tecnicoNew != null && !tecnicoNew.equals(tecnicoOld)) {
                tecnicoNew.getIncidentes().add(incidente);
                tecnicoNew = em.merge(tecnicoNew);
            }
            if (operadorOld != null && !operadorOld.equals(operadorNew)) {
                operadorOld.getListaIncidentes().remove(incidente);
                operadorOld = em.merge(operadorOld);
            }
            if (operadorNew != null && !operadorNew.equals(operadorOld)) {
                operadorNew.getListaIncidentes().add(incidente);
                operadorNew = em.merge(operadorNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getListaIncidentes().remove(incidente);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getListaIncidentes().add(incidente);
                clienteNew = em.merge(clienteNew);
            }
            if (mesaDeAyudaOld != null && !mesaDeAyudaOld.equals(mesaDeAyudaNew)) {
                mesaDeAyudaOld.getIncidentes().remove(incidente);
                mesaDeAyudaOld = em.merge(mesaDeAyudaOld);
            }
            if (mesaDeAyudaNew != null && !mesaDeAyudaNew.equals(mesaDeAyudaOld)) {
                mesaDeAyudaNew.getIncidentes().add(incidente);
                mesaDeAyudaNew = em.merge(mesaDeAyudaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = incidente.getId();
                if (findIncidente(id) == null) {
                    throw new NonexistentEntityException("The incidente with id " + id + " no longer exists.");
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
            Incidente incidente;
            try {
                incidente = em.getReference(Incidente.class, id);
                incidente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The incidente with id " + id + " no longer exists.", enfe);
            }
            Tecnico tecnico = incidente.getTecnico();
            if (tecnico != null) {
                tecnico.getIncidentes().remove(incidente);
                tecnico = em.merge(tecnico);
            }
            Operador operador = incidente.getOperador();
            if (operador != null) {
                operador.getListaIncidentes().remove(incidente);
                operador = em.merge(operador);
            }
            Cliente cliente = incidente.getCliente();
            if (cliente != null) {
                cliente.getListaIncidentes().remove(incidente);
                cliente = em.merge(cliente);
            }
            MesaDeAyuda mesaDeAyuda = incidente.getMesaDeAyuda();
            if (mesaDeAyuda != null) {
                mesaDeAyuda.getIncidentes().remove(incidente);
                mesaDeAyuda = em.merge(mesaDeAyuda);
            }
            em.remove(incidente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Incidente> findIncidenteEntities() {
        return findIncidenteEntities(true, -1, -1);
    }

    public List<Incidente> findIncidenteEntities(int maxResults, int firstResult) {
        return findIncidenteEntities(false, maxResults, firstResult);
    }

    private List<Incidente> findIncidenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Incidente.class));
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

    public Incidente findIncidente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Incidente.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncidenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Incidente> rt = cq.from(Incidente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
