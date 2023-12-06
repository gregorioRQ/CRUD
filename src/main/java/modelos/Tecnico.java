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
@Table(name="tecnico")
public class Tecnico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    @Basic
    private String especialidad1;
    @Basic
    private int tiempoEstimado1;
    @Basic
    private String especialidad2;
    @Basic
    private int tiempoEstimado2;
    @Basic
    private int incAresolver;
    @Basic 
    private String cantIncResueltos;
    @OneToMany(mappedBy="tecnico")
    ArrayList<Incidente> listaIncidentes;
   
    

    public Tecnico() {
    }

    public Tecnico(int id, String nombre, String especialidad1, int tiempoEstimado1, String especialidad2, int tiempoEstimado2, int incAresolver, String cantIncResueltos, ArrayList<Incidente> listaIncidentes) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad1 = especialidad1;
        this.tiempoEstimado1 = tiempoEstimado1;
        this.especialidad2 = especialidad2;
        this.tiempoEstimado2 = tiempoEstimado2;
        this.incAresolver = incAresolver;
        this.cantIncResueltos = cantIncResueltos;
        this.listaIncidentes = listaIncidentes;
    }

    public int getIncAresolver() {
        return incAresolver;
    }

    public void setIncAresolver(int incAresolver) {
        this.incAresolver = incAresolver;
    }


    public ArrayList<Incidente> getListaIncidentes() {
        return listaIncidentes;
    }

    public void setListaIncidentes(ArrayList<Incidente> listaIncidentes) {
        this.listaIncidentes = listaIncidentes;
    }

   
    public String getCantIncResueltos() {
        return cantIncResueltos;
    }

    public void setCantIncResueltos(String cantIncResueltos) {
        this.cantIncResueltos = cantIncResueltos;
    }

    
    
    public int getTiempoEstimado1() {
        return tiempoEstimado1;
    }

    public void setTiempoEstimado1(int tiempoEstimado1) {
        this.tiempoEstimado1 = tiempoEstimado1;
    }

    public int getTiempoEstimado2() {
        return tiempoEstimado2;
    }

    public void setTiempoEstimado2(int tiempoEstimado2) {
        this.tiempoEstimado2 = tiempoEstimado2;
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

    public String getEspecialidad1() {
        return especialidad1;
    }

    public void setEspecialidad1(String especialidad1) {
        this.especialidad1 = especialidad1;
    }

    public String getEspecialidad2() {
        return especialidad2;
    }

    public void setEspecialidad2(String especialidad2) {
        this.especialidad2 = especialidad2;
    }

    @Override
    public String toString() {
        return "Tecnico{" + "id=" + id + ", nombre=" + nombre + ", especialidad1=" + especialidad1 + ", tiempoEstimado1=" + tiempoEstimado1 + ", especialidad2=" + especialidad2 + ", tiempoEstimado2=" + tiempoEstimado2 + ", incAresolver=" + incAresolver + ", cantIncResueltos=" + cantIncResueltos + ", listaIncidentes=" + listaIncidentes + '}';
    }
 
}
