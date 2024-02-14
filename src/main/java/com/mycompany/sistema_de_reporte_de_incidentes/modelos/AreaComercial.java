/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PC
 */
@Entity
@Table(name="area_comercial")
public class AreaComercial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
     @Basic
    private String nombreDelResponsable;
    @Temporal(TemporalType.DATE)
    private Date ultimaActualizacion;
    @OneToMany(mappedBy="areaComercial")
    private ArrayList<Cliente>clientes;

    public AreaComercial() {
    }

    public AreaComercial(int id, String nombreDelResponsable, Date ultimaActualizacion, ArrayList<Cliente> clientes) {
        this.id = id;
        this.nombreDelResponsable = nombreDelResponsable;
        this.ultimaActualizacion = ultimaActualizacion;
        this.clientes = clientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDelResponsable() {
        return nombreDelResponsable;
    }

    public void setNombreDelResponsable(String nombreDelResponsable) {
        this.nombreDelResponsable = nombreDelResponsable;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "AreaComercial{" + "id=" + id + ", nombreDelResponsable=" + nombreDelResponsable + ", ultimaActualizacion=" + ultimaActualizacion + ", clientes=" + clientes + '}';
    }
    
   
}
