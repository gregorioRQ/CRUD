/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Basic
    private String razonSocial;
    @Basic
    private long cuit;
    @Basic
    private long telefono;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @OneToMany(mappedBy="cliente")
    private ArrayList<Servicio> listaServicios;
    @OneToMany(mappedBy="cliente")
    private ArrayList<Incidente> listaIncidentes;
    @ManyToOne
    private AreaComercial areaComercial;
    

    public Cliente() {
    }

    public Cliente(long id, String razonSocial, long cuit, long telefono, Date fechaAlta, Date fechaBaja, ArrayList<Servicio> listaServicios, ArrayList<Incidente> listaIncidentes, AreaComercial areaComercial) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.listaServicios = listaServicios;
        this.listaIncidentes = listaIncidentes;
        this.areaComercial = areaComercial;
    }

    public AreaComercial getAreaComercial() {
        return areaComercial;
    }

    public void setAreaComercial(AreaComercial areaComercial) {
        this.areaComercial = areaComercial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public ArrayList<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(ArrayList<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public ArrayList<Incidente> getListaIncidentes() {
        return listaIncidentes;
    }

    public void setListaIncidentes(ArrayList<Incidente> listaIncidentes) {
        this.listaIncidentes = listaIncidentes;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", razonSocial=" + razonSocial + ", cuit=" + cuit + ", telefono=" + telefono + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", listaServicios=" + listaServicios + ", listaIncidentes=" + listaIncidentes + ", areaComercial=" + areaComercial + '}';
    }

}
