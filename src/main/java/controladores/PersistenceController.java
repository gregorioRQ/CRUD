/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import modelos.Incidente;
import modelos.Servicio;
import modelos.Tecnico;

/**
 *
 * @author PC
 */
public class PersistenceController {
    ClienteJpaController clienteJpa = new ClienteJpaController();
    TecnicoJpaController tecnicoJpa = new TecnicoJpaController();
    ServicioJpaController servicioJpa = new ServicioJpaController();
    IncidenteJpaController incidenteJpa = new IncidenteJpaController();
    
    
    //CLIENTE
    public void crearCliente(Cliente cliente){  
            clienteJpa.create(cliente);     
    }
    
    public void eliminarCliente(int id){
        try {
            clienteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void editarCliente(Cliente cli) {
        try {
            clienteJpa.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public Cliente buscarCliente(int id) {
        return clienteJpa.findCliente(id);
    }
      
       public ArrayList<Cliente> listaCliente() {
        List<Cliente> listaTemp = clienteJpa.findClienteEntities();
        ArrayList<Cliente> lista = new ArrayList(listaTemp);
        return lista;
    }
    
    
    //TECNICO
    void eliminarTecnico(int id) {
        try {
            tecnicoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void crearTecnico(Tecnico tec) {
       tecnicoJpa.create(tec);
    }

    void editarTecnico(Tecnico tec) {
        try {
            tecnicoJpa.edit(tec);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Tecnico> listaTecnico() {
        List<Tecnico> listaTemp = tecnicoJpa.findTecnicoEntities();
        ArrayList<Tecnico> lista = new ArrayList(listaTemp);
        return lista;
    }
    
    Tecnico buscarTecnico(int id) {
       return tecnicoJpa.findTecnico(id);
    }
    
    
    
    //SERVICIO
    void crearServicio(Servicio ser){
        servicioJpa.create(ser);
    }
    
    void editarServicio(Servicio ser) {
        try {
            servicioJpa.edit(ser);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void eliminarServicio(int id){
        try {
            servicioJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Servicio buscarServicio(int id) {
        return servicioJpa.findServicio(id);
    }

   public  ArrayList<Servicio> listaServicio() {
       List<Servicio> listaS = servicioJpa.findServicioEntities();
       ArrayList<Servicio> lista = new ArrayList(listaS);
       return lista;
    }
    
    //INCIDENTE
    void crearIncidente(Incidente inc){
        incidenteJpa.create(inc);
    }
    
    void editarIncidente(Incidente inc){
        try {
            incidenteJpa.edit(inc);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
      public ArrayList<Incidente> listaIncidente() {
        List<Incidente> listaI = incidenteJpa.findIncidenteEntities();
        ArrayList<Incidente> lista = new ArrayList(listaI);
        return lista;
    }
    
    public Incidente buscarIncidente(int id) {
       return incidenteJpa.findIncidente(id);
    }


    void eliminarIncidente(int id) {
        try {
            incidenteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
