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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name="servicio")
public class Servicio implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private String nombre;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy="servicio")
    private ArrayList<TipoDeProblema> tiposDeProblemas;
  
   
    public Servicio() {
    }

    public Servicio(int id, String nombre, Cliente cliente, ArrayList<TipoDeProblema> tiposDeProblemas) {
        this.id = id;
        this.nombre = nombre;
        this.cliente = cliente;
        this.tiposDeProblemas = tiposDeProblemas;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

 

    public ArrayList<TipoDeProblema> getTiposDeProblemas() {
        return tiposDeProblemas;
    }

    public void setTiposDeProblemas(ArrayList<TipoDeProblema> tiposDeProblemas) {
        this.tiposDeProblemas = tiposDeProblemas;
    }

    @Override
    public String toString() {
        return "Servicio{" + "id=" + id + ", nombre=" + nombre + ", cliente=" + cliente + ", tiposDeProblemas=" + tiposDeProblemas + '}';
    }
    
}
