/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rafae
 */
@Entity
@Table(name="mesa_ayuda")
public class MesaDeAyuda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombreDelResponsable;
    private int incidentesAtendidos;
    private ArrayList<Cliente>cilentesAtendidos;
     @OneToMany(mappedBy = "mesaDeAyuda")
     private ArrayList<Incidente>incidentes;

    public MesaDeAyuda() {
    }

    public MesaDeAyuda(int id, String nombreDelResponsable, int incidentesAtendidos, ArrayList<Cliente> cilentesAtendidos, ArrayList<Incidente> incidentes) {
        this.id = id;
        this.nombreDelResponsable = nombreDelResponsable;
        this.incidentesAtendidos = incidentesAtendidos;
        this.cilentesAtendidos = cilentesAtendidos;
        this.incidentes = incidentes;
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

    public int getIncidentesAtendidos() {
        return incidentesAtendidos;
    }

    public void setIncidentesAtendidos(int incidentesAtendidos) {
        this.incidentesAtendidos = incidentesAtendidos;
    }

    public ArrayList<Cliente> getCilentesAtendidos() {
        return cilentesAtendidos;
    }

    public void setCilentesAtendidos(ArrayList<Cliente> cilentesAtendidos) {
        this.cilentesAtendidos = cilentesAtendidos;
    }

    public ArrayList<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(ArrayList<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    @Override
    public String toString() {
        return "MesaDeAyuda{" + "id=" + id + ", nombreDelResponsable=" + nombreDelResponsable + ", incidentesAtendidos=" + incidentesAtendidos + ", cilentesAtendidos=" + cilentesAtendidos + ", incidentes=" + incidentes + '}';
    }
     
     
}
