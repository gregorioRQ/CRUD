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
@Table(name="recursos_humanos")
public class RRHH implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombreDelResponsble;
    private Date fechaDeUltimaActualizacion;
    @OneToMany(mappedBy="rh")
    private ArrayList<Tecnico>tecnicos;

    public RRHH() {
    }

    public RRHH(int id, String nombreDelResponsble, Date fechaDeUltimaActualizacion, ArrayList<Tecnico> tecnicos) {
        this.id = id;
        this.nombreDelResponsble = nombreDelResponsble;
        this.fechaDeUltimaActualizacion = fechaDeUltimaActualizacion;
        this.tecnicos = tecnicos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDelResponsble() {
        return nombreDelResponsble;
    }

    public void setNombreDelResponsble(String nombreDelResponsble) {
        this.nombreDelResponsble = nombreDelResponsble;
    }

    public Date getFechaDeUltimaActualizacion() {
        return fechaDeUltimaActualizacion;
    }

    public void setFechaDeUltimaActualizacion(Date fechaDeUltimaActualizacion) {
        this.fechaDeUltimaActualizacion = fechaDeUltimaActualizacion;
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    @Override
    public String toString() {
        return "RRHH{" + "id=" + id + ", nombreDelResponsble=" + nombreDelResponsble + ", fechaDeUltimaActualizacion=" + fechaDeUltimaActualizacion + ", tecnicos=" + tecnicos + '}';
    }
    
    
    
}


