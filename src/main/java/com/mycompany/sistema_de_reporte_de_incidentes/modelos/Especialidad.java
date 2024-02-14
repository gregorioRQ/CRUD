/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author rafae
 */
@Entity
@Table(name="especialidad")
public class Especialidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Basic
    private String nombre;
    @Basic
    private String descripcion;
    @Basic
    private String areaDeConocimiento;
    @ManyToMany(mappedBy="especialidades")
    private ArrayList<Tecnico> tecnicos;
    @ManyToMany(mappedBy="especialidades")
    private ArrayList<TipoDeProblema>tiposDeProblemas;
    

    public Especialidad() {
    }

    public Especialidad(long id, String nombre, String descripcion, String areaDeConocimiento, ArrayList<Tecnico> tecnicos, ArrayList<TipoDeProblema> tiposDeProblemas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.areaDeConocimiento = areaDeConocimiento;
        this.tecnicos = tecnicos;
        this.tiposDeProblemas = tiposDeProblemas;
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAreaDeConocimiento() {
        return areaDeConocimiento;
    }

    public void setAreaDeConocimiento(String areaDeConocimiento) {
        this.areaDeConocimiento = areaDeConocimiento;
    }

    public ArrayList<TipoDeProblema> getTiposDeProblemas() {
        return tiposDeProblemas;
    }

    public void setTiposDeProblemas(ArrayList<TipoDeProblema> tiposDeProblemas) {
        this.tiposDeProblemas = tiposDeProblemas;
    }

    @Override
    public String toString() {
        return "Especialidad{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", areaDeConocimiento=" + areaDeConocimiento + ", tecnicos=" + tecnicos + ", tiposDeProblemas=" + tiposDeProblemas + '}';
    }

}
