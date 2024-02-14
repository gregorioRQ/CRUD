/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.controladores;

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

/**
 *
 * @author rafae
 */
public class Controlador {
    
    ControladorDePersistencia cp = new ControladorDePersistencia();
    
    //---CLIENTE
    public void crearCliente(Cliente c){
        cp.crearCliente(c);
    }
    
    public void editarCliente(Cliente c){
        cp.editarCliente(c);
    }
    
    public void eliminarCliente(int id){
        cp.eliminarCliente(id);
    }
    
    public Cliente buscarCliente(long id){
        return cp.buscarCliente(id);
    }
    
    public ArrayList<Cliente> buscarClientes(){
        return cp.buscarClientes();
    }
    
    //---AREA COMERCIAL
 
    public void crearAreaComercial(AreaComercial ac){
        cp.crearAreComercial(ac);
    }
    
    public void editarAreaComercial(AreaComercial ac){
        cp.editarAreaComercial(ac);
    }
    
    public void eliminarAreaComercial(int id){
        cp.eliminarAreaComercial(id);
    }
    
    //---TECNICO
    public void crearTecnico(Tecnico t){
        cp.crearTecnico(t);
    }
    
    public void editarTecnico(Tecnico t){
        cp.editarTecnico(t);
    }
    
    public void eliminarTecnico(long id){
        cp.eliminarTecnico(id);
}
    public Tecnico buscarTecnico(long id){
        return cp.buscarTecnico(id);
    }
    
    public ArrayList<Tecnico> buscarTecnicos(){
        return cp.buscarTecnicos();
    }
    
    //---ESPECIALIDAD
    public void crearEspecialidad(Especialidad e){
        cp.crearEspecialidad(e);
    }
    
    public void editarEspecialidad(Especialidad e){
        cp.editarEspecialidad(e);
    }
    
    public void eliminarEspecialidad(long id){
        cp.eliminarEspecialidad(id);
    }
    
    public Especialidad buscarEspecialidad(long id){
       return cp.buscarEspecialidad(id);
    }
    
    public ArrayList<Especialidad> buscarEspecialidades(){
        return cp.buscarEspecialidades();
    }
    
    //---INCIDENTE
    public void crearIncidente(Incidente i){
        cp.crearIncidente(i);
    }
    
    public void editarIncidente(Incidente i){
        cp.editarIncidente(i);
    }
    
    public void eliminarIncidente(int id){
        cp.eliminarIncidente(id);
    }
    
    public Incidente buscarIncidente(int id){
       return cp.buscarIncidente(id);
    }
    
    public ArrayList<Incidente> buscarIncidentes(){
        return cp.buscarIncidentes();
    }
    
    //---RRHH
    public void crearRH(RRHH rh){
        cp.crearRH(rh);
    }
    
    public void editarRH(RRHH rh){
        cp.editarRH(rh);
    }
    
    public void eliminarRH(int id){
        cp.eliminarRH(id);
    }
    
    public RRHH buscarRH(int id){
        return cp.buscarRH(id);
    }
    
    //---MESA DE AYUDA
    public void crearMesaDeAyuda(MesaDeAyuda ma){
        cp.crearMesaDeAyuda(ma);
    }
    
    public void editarMesaDeAyuda(MesaDeAyuda ma){
        cp.editarMesaDeAyuda(ma);
    }
    
    public void eliminarMesaDeAyuda(int id){
        cp.eliminarMesaDeAyuda(id);
    }
    
    public MesaDeAyuda buscarMesaDeAyuda(int id){
        return cp.buscarMesaDeAyuda(id);
    }
    
    //---SERVICIO
    public void crearServicio(Servicio s){
        cp.crearServicio(s);
    }
    
    public void editarServicio(Servicio s){
        cp.editarServicio(s);
    }
    
    public void eliminarServicio(int id){
        cp.eliminarServicio(id);
    }
    
    public Servicio buscarServicio(int id){
        return cp.buscarServicio(id);
    }
    
    public ArrayList<Servicio> buscarServicios(){
        return cp.buscarServicios();
    }
    
    //---OPERADOR
    public void crearOperador(Operador o){
        cp.crearOperador(o);
    }
    
    public void editarOperador(Operador o){
        cp.editarOperador(o);
    }
    
    public void eliminarOperador(int id){
        cp.eliminarOperador(id);
    }
    
    public Operador buscarOperador(int id){
        return cp.buscarOperador(id);
    }
    
    //---TIPO DE PROBLEMA
    public void crearTipoDeProblema(TipoDeProblema tp){
        cp.crearTipoDeProblema(tp);
    }
    
    public void editarTipoDeProblema(TipoDeProblema tp){
        cp.editarTipoDeProblema(tp);
    }
    
    public void eliminarTipoDeProblema(long id){
        cp.eliminarTipoDeProblema(id);
    }
    
    public TipoDeProblema buscarTipoDeProblema(long id){
        return cp.buscarTipoDeProblema(id);
    }
    
    public ArrayList<TipoDeProblema> buscarTiposDeProblemas(){
        return cp.buscarTiposDeProblemas();
    }
}
