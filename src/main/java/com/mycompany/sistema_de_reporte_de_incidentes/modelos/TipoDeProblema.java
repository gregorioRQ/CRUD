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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rafae
 */
@Entity
@Table(name="tipoDeProblema")
public class TipoDeProblema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Basic
    private String nombre;
    @Basic
    private String descripcion;
    @Basic
    private String nivelDePrioridad;
    @Basic
    private String tiempoMaxDeResolucion;
    @Basic
    private ArrayList<String>areasDeConocimientoRequerida;
    @Basic
    private boolean permitirColchonDeHs;
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion;
    @Basic
    private String consideraciones;
    @ManyToOne
    private Servicio servicio;
    @ManyToMany
    private ArrayList<Especialidad>especialidades;

    public TipoDeProblema() {
    }

    public TipoDeProblema(long id, String nombre, String descripcion, String nivelDePrioridad, String tiempoMaxDeResolucion, ArrayList<String> areasDeConocimientoRequerida, boolean permitirColchonDeHs, Date fechaDeCreacion, String consideraciones, Servicio servicio, ArrayList<Especialidad> especialidades) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelDePrioridad = nivelDePrioridad;
        this.tiempoMaxDeResolucion = tiempoMaxDeResolucion;
        this.areasDeConocimientoRequerida = areasDeConocimientoRequerida;
        this.permitirColchonDeHs = permitirColchonDeHs;
        this.fechaDeCreacion = fechaDeCreacion;
        this.consideraciones = consideraciones;
        this.servicio = servicio;
        this.especialidades = especialidades;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTiempoMaxDeResolucion() {
        return tiempoMaxDeResolucion;
    }

    public void setTiempoMaxDeResolucion(String tiempoMaxDeResolucion) {
        this.tiempoMaxDeResolucion = tiempoMaxDeResolucion;
    }

    public ArrayList<String> getAreasDeConocimientoRequerida() {
        return areasDeConocimientoRequerida;
    }

    public void setAreasDeConocimientoRequerida(ArrayList<String> areasDeConocimientoRequerida) {
        this.areasDeConocimientoRequerida = areasDeConocimientoRequerida;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivelDePrioridad() {
        return nivelDePrioridad;
    }

    public void setNivelDePrioridad(String nivelDePrioridad) {
        this.nivelDePrioridad = nivelDePrioridad;
    }


    public boolean isPermitirColchonDeHs() {
        return permitirColchonDeHs;
    }

    public void setPermitirColchonDeHs(boolean permitirColchonDeHs) {
        this.permitirColchonDeHs = permitirColchonDeHs;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getConsideraciones() {
        return consideraciones;
    }

    public void setConsideraciones(String consideraciones) {
        this.consideraciones = consideraciones;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public String toString() {
        return "TipoDeProblema{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivelDePrioridad=" + nivelDePrioridad + ", tiempoMaxDeResolucion=" + tiempoMaxDeResolucion + ", areasDeConocimientoRequerida=" + areasDeConocimientoRequerida + ", permitirColchonDeHs=" + permitirColchonDeHs + ", fechaDeCreacion=" + fechaDeCreacion + ", consideraciones=" + consideraciones + ", servicio=" + servicio + ", especialidades=" + especialidades + '}';
    }

    
   
}
