/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.ArrayList;
import modelos.Cliente;
import modelos.Incidente;
import modelos.Servicio;
import modelos.Tecnico;

/**
 *
 * @author PC
 */
public class Controladores {
    PersistenceController control = new PersistenceController();
    
    //CLIENTE
    public void crearCliente(Cliente cliente){
        control.crearCliente(cliente);
    }
    
    public void eliminarCliente(int id){
        control.eliminarCliente(id);
    }
    
    public void editarCliente(Cliente cli)
    {
        control.editarCliente(cli);
    }
    
      public Cliente buscarCliente(int id)
    {
      return control.buscarCliente(id);
    }
    
    
    
    //TECNICO
    public void crearTecnico(Tecnico tec){
        control.crearTecnico(tec);
    }
    
    public void eliminarTecnico(int id){
        control.eliminarTecnico(id);
    }
    
    public void editarTecnico(Tecnico tec)
    {
        control.editarTecnico(tec);
    }
    
    
    
    public Tecnico buscarTecnico(int id) {
       return control.buscarTecnico(id);
    }
    
    
    public ArrayList<Tecnico> listaTecnico()     {
        ArrayList<Tecnico> tecnico = new ArrayList();
        tecnico = control.listaTecnico();
        return tecnico;
    }
    
    
    //SERVICIO
    public void crearServicio(Servicio ser){
        control.crearServicio(ser);
      }
    
    
    public void editarServicio(Servicio ser){
        control.editarServicio(ser);
    }
    
    public Servicio bucsarServicio(int id){
        return control.buscarServicio(id);
    }
    
    public ArrayList<Servicio> listaServicio(){
        ArrayList<Servicio> servicio = new ArrayList();
        servicio = control.listaServicio();
        return servicio;
    }
    
      public void eliminarServicio(int id){
        control.eliminarServicio(id);
    }
    
    //INCIDENTE
    public void crearIncidente(Incidente inc){
        control.crearIncidente(inc);
    }
    
    public void editarIncidente(Incidente inc){
        control.editarIncidente(inc);
    }
    
    public Incidente bucsarIncidente(int id){
        return control.buscarIncidente(id);
    }
    
    public ArrayList<Incidente> listaIncidente(){
        ArrayList<Incidente> incidente = new ArrayList();
        incidente = control.listaIncidente();
        return incidente;
    }
    
      public void eliminarIncidente(int id){
        control.eliminarIncidente(id);
    }
}
