/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
   private ArrayList<Incidente> listaIncidentes;
  
   
    public Servicio() {
    }

    public Servicio(int id, String nombre, Cliente cliente, ArrayList<Incidente> listaIncidentes) {
        this.id = id;
        this.nombre = nombre;
        this.cliente = cliente;
        this.listaIncidentes = listaIncidentes;
    }

    public ArrayList<Incidente> getListaIncidentes() {
        return listaIncidentes;
    }

    public void setListaIncidentes(ArrayList<Incidente> listaIncidentes) {
        this.listaIncidentes = listaIncidentes;
    }



    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    @Override
    public String toString() {
        return "Servicio{" + "id=" + id + ", nombre=" + nombre + ", cliente=" + cliente + ", listaIncidentes=" + listaIncidentes + '}';
    }

    public void add(Servicio servicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void remove(Servicio servicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    
}
