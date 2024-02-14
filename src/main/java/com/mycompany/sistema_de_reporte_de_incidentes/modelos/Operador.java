/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name="operador")
public class Operador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
      private int id;
      private String nombre;
      private long telefono;
      private Date fechaIngreso;
      private String areaDeResponsabilidad;
      private int tiempoEstimadoDeResolucion;
      private String consideraciones;
      
      @OneToMany(mappedBy="operador")
        ArrayList<Incidente> listaIncidentes;

    public Operador() {
    }

    public Operador(int id, String nombre, long telefono, Date fechaIngreso, String areaDeResponsabilidad, int tiempoEstimadoDeResolucion, String consideraciones, ArrayList<Incidente> listaIncidentes) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
        this.areaDeResponsabilidad = areaDeResponsabilidad;
        this.tiempoEstimadoDeResolucion = tiempoEstimadoDeResolucion;
        this.consideraciones = consideraciones;
        this.listaIncidentes = listaIncidentes;
    }

    public int getId() {
        return id;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getAreaDeResponsabilidad() {
        return areaDeResponsabilidad;
    }

    public void setAreaDeResponsabilidad(String areaDeResponsabilidad) {
        this.areaDeResponsabilidad = areaDeResponsabilidad;
    }

    public int getTiempoEstimadoDeResolucion() {
        return tiempoEstimadoDeResolucion;
    }

    public void setTiempoEstimadoDeResolucion(int tiempoEstimadoDeResolucion) {
        this.tiempoEstimadoDeResolucion = tiempoEstimadoDeResolucion;
    }

    public String getConsideraciones() {
        return consideraciones;
    }

    public void setConsideraciones(String consideraciones) {
        this.consideraciones = consideraciones;
    }

    public ArrayList<Incidente> getListaIncidentes() {
        return listaIncidentes;
    }

    public void setListaIncidentes(ArrayList<Incidente> listaIncidentes) {
        this.listaIncidentes = listaIncidentes;
    }

    @Override
    public String toString() {
        return "Operador{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", fechaIngreso=" + fechaIngreso + ", areaDeResponsabilidad=" + areaDeResponsabilidad + ", tiempoEstimadoDeResolucion=" + tiempoEstimadoDeResolucion + ", consideraciones=" + consideraciones + ", listaIncidentes=" + listaIncidentes + '}';
    }
      
      
    }

    
