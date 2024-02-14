/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PC
 */
@Entity
@Table(name="tecnico")
public class Tecnico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Basic
    private String nombre;
    @Basic
    private long telefono;
    @Basic
    private String medioDeNotificacion;
    @Basic
    private String tiempoEstimadoDeResolucion;
    @Basic
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic
    private int incAresolver;
    @Basic 
    private long cantIncResueltos;
    @OneToMany(mappedBy="tecnico")
    private ArrayList<Incidente> incidentes;
    @ManyToMany
    private ArrayList<Especialidad>especialidades;
   @ManyToOne
   private RRHH rh;

    public Tecnico() {
    }

    public Tecnico(long id, String nombre, long telefono, String medioDeNotificacion, String tiempoEstimadoDeResolucion, String estado, Date fechaIngreso, int incAresolver, long cantIncResueltos, ArrayList<Incidente> incidentes, ArrayList<Especialidad> especialidades, RRHH rh) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.medioDeNotificacion = medioDeNotificacion;
        this.tiempoEstimadoDeResolucion = tiempoEstimadoDeResolucion;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.incAresolver = incAresolver;
        this.cantIncResueltos = cantIncResueltos;
        this.incidentes = incidentes;
        this.especialidades = especialidades;
        this.rh = rh;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getMedioDeNotificacion() {
        return medioDeNotificacion;
    }

    public void setMedioDeNotificacion(String medioDeNotificacion) {
        this.medioDeNotificacion = medioDeNotificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getIncAresolver() {
        return incAresolver;
    }

    public void setIncAresolver(int incAresolver) {
        this.incAresolver = incAresolver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTiempoEstimadoDeResolucion() {
        return tiempoEstimadoDeResolucion;
    }

    public void setTiempoEstimadoDeResolucion(String tiempoEstimadoDeResolucion) {
        this.tiempoEstimadoDeResolucion = tiempoEstimadoDeResolucion;
    }

    public long getCantIncResueltos() {
        return cantIncResueltos;
    }

    public void setCantIncResueltos(long cantIncResueltos) {
        this.cantIncResueltos = cantIncResueltos;
    }

    public ArrayList<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(ArrayList<Incidente> incidentes) {
        this.incidentes = incidentes;
    }
    
    
    
    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public RRHH getRh() {
        return rh;
    }

    public void setRh(RRHH rh) {
        this.rh = rh;
    }

    @Override
    public String toString() {
        return "Tecnico{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", medioDeNotificacion=" + medioDeNotificacion + ", tiempoEstimadoDeResolucion=" + tiempoEstimadoDeResolucion + ", estado=" + estado + ", fechaIngreso=" + fechaIngreso + ", incAresolver=" + incAresolver + ", cantIncResueltos=" + cantIncResueltos + ", incidentes=" + incidentes + ", especialidades=" + especialidades + ", rh=" + rh + '}';
    }

}
