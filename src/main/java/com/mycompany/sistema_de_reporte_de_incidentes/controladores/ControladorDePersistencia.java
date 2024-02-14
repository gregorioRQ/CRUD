/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.controladores;

import com.mycompany.sistema_de_reporte_de_incidentes.controladores.exceptions.NonexistentEntityException;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.AreaComercial;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Especialidad;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.MesaDeAyuda;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Operador;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.RRHH;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Servicio;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Tecnico;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.TipoDeProblema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class ControladorDePersistencia {
    ClienteJpaController cjpa = new ClienteJpaController();
    AreaComercialJpaController acjpa = new AreaComercialJpaController();
    RRHHJpaController rhjpa = new RRHHJpaController();
    MesaDeAyudaJpaController majpa = new MesaDeAyudaJpaController();
    TecnicoJpaController tjpa = new TecnicoJpaController();
    EspecialidadJpaController espjpa = new EspecialidadJpaController();
    IncidenteJpaController ijpa = new IncidenteJpaController();
    ServicioJpaController sjpa = new ServicioJpaController();
    OperadorJpaController ojpa = new OperadorJpaController();
    TipoDeProblemaJpaController tpjpa = new TipoDeProblemaJpaController();
    
    //---CLIENTE
    void crearCliente(Cliente c) {
        cjpa.create(c);
    }
    
    void editarCliente(Cliente c){
        try{
            cjpa.edit(c);
        }catch(Exception ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void eliminarCliente(int id){
        try{
            cjpa.destroy(id);
        }catch(NonexistentEntityException ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public Cliente buscarCliente(long id){
        return cjpa.findCliente(id);
    }
    
    public ArrayList<Cliente> buscarClientes(){
        List<Cliente>clientes = cjpa.findClienteEntities();
        ArrayList<Cliente>clientes2 = new ArrayList<Cliente>(clientes);
        return clientes2;
    }
    
    //---AREA COMERCIAL
    void crearAreComercial(AreaComercial ac){
        acjpa.create(ac);
    }
    
    void editarAreaComercial(AreaComercial ac){
        try {
            acjpa.edit(ac);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void eliminarAreaComercial(int id){
        try{
            acjpa.destroy(id);
        }catch(NonexistentEntityException ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    //---TECNICO
    void crearTecnico(Tecnico t){
        tjpa.create(t);
    }
    
    void editarTecnico(Tecnico t){
        try{
            tjpa.edit(t);
        }catch(Exception ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    void eliminarTecnico(long id){
        try{
            tjpa.destroy(id);
        }catch(NonexistentEntityException ex){
             Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public Tecnico buscarTecnico(long id){
       return tjpa.findTecnico(id);
    }
    
    public ArrayList<Tecnico> buscarTecnicos(){
        List<Tecnico> tecnicos = tjpa.findTecnicoEntities();
        ArrayList<Tecnico> tecnicos2 = new ArrayList<Tecnico>(tecnicos);
        return tecnicos2;
    }
    
    //---ESPECIALIDAD
    void crearEspecialidad(Especialidad esp){
        espjpa.create(esp);
    }
    
    void editarEspecialidad(Especialidad esp){
        try{
            espjpa.edit(esp);
        }catch(Exception ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    void eliminarEspecialidad(long id){
        try{
            espjpa.destroy(id);
        }catch(NonexistentEntityException ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public Especialidad buscarEspecialidad(long id){
        return espjpa.findEspecialidad(id);
    }
    
    public ArrayList<Especialidad> buscarEspecialidades(){
        List<Especialidad> especialidades = espjpa.findEspecialidadEntities();
        ArrayList<Especialidad> especialidades2 = new ArrayList<Especialidad>(especialidades);
        return especialidades2;
    }
    
    //---INCIDENTE
    void crearIncidente(Incidente i){
        ijpa.create(i);
    }
    
    void editarIncidente(Incidente i){
        try{
            ijpa.edit(i);
        }catch(Exception ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    void eliminarIncidente(int id){
        try{
            ijpa.destroy(id);
        }catch(NonexistentEntityException ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Incidente buscarIncidente(int id){
        return ijpa.findIncidente(id);
    }
    
    public ArrayList<Incidente> buscarIncidentes(){
        List<Incidente>incidentes = ijpa.findIncidenteEntities();
        ArrayList<Incidente> incidentes2 = new ArrayList<Incidente>(incidentes);
        return incidentes2;
    }
    
    //---RH
    void crearRH(RRHH rh){
        rhjpa.create(rh);
    }
    
    void editarRH(RRHH rh){
        try{
            rhjpa.edit(rh);
        }catch(Exception ex){
             Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    void eliminarRH(int id){
        try{
            rhjpa.destroy(id);
        }catch(NonexistentEntityException ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public RRHH buscarRH(int id){
        return rhjpa.findRRHH(id);
    }
    
    //---MESA DE AYUDA
    void crearMesaDeAyuda(MesaDeAyuda ma){
        majpa.create(ma);
    }
    
    void editarMesaDeAyuda(MesaDeAyuda ma){
        try{
            majpa.edit(ma);
        }catch(Exception ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    void eliminarMesaDeAyuda(int id){
        try{
            majpa.destroy(id);
        }catch(NonexistentEntityException ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public MesaDeAyuda buscarMesaDeAyuda(int id){
        return majpa.findMesaDeAyuda(id);
    }
    
    //---SERVICIO
    
    void crearServicio(Servicio s){
        sjpa.create(s);
    }
    
    void editarServicio(Servicio s){
        try{
            sjpa.edit(s);
        }catch(Exception ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void eliminarServicio(int id){
        try{
        sjpa.destroy(id);
    }catch(NonexistentEntityException ex){
        Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public Servicio buscarServicio(int id){
       return sjpa.findServicio(id);
    }
    
    public ArrayList<Servicio> buscarServicios(){
        List<Servicio> servicios = sjpa.findServicioEntities();
        ArrayList<Servicio>servicios2 = new ArrayList<Servicio>(servicios);
        return servicios2;
    }
    
    //---OPERADOR
    void crearOperador(Operador o){
        ojpa.create(o);
    }
    
    void editarOperador(Operador o){
        try{
            ojpa.edit(o);
        }catch(Exception ex){
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      void eliminarOperador(int id){
          try{
              ojpa.destroy(id);
          }catch(NonexistentEntityException ex){
              Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
      public Operador buscarOperador(int id){
          return ojpa.findOperador(id);
      }
      
      //---TIPO DE PROBLEMA
      void crearTipoDeProblema(TipoDeProblema tp){
          tpjpa.create(tp);
      }
      
      void editarTipoDeProblema(TipoDeProblema tp){
        try {
            tpjpa.edit(tp);
        } catch (Exception ex) {
            Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      void eliminarTipoDeProblema(long id){
          try{
              tpjpa.destroy(id);
          }catch(NonexistentEntityException ex){
              Logger.getLogger(ControladorDePersistencia.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
      public TipoDeProblema buscarTipoDeProblema(long id){
          return tpjpa.findTipoDeProblema(id);
      }
      
      public ArrayList<TipoDeProblema> buscarTiposDeProblemas(){
          List<TipoDeProblema>tiposDeProblemas = tpjpa.findTipoDeProblemaEntities();
          ArrayList<TipoDeProblema> tiposDeProblemas2 = new ArrayList<TipoDeProblema>(tiposDeProblemas);
          return tiposDeProblemas2;
      }
}