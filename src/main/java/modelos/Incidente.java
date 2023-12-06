/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="incidente")
public class Incidente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private String tipoProblema;
    @Basic
    private String descProblema;
    @Basic
    private boolean resuelto;
    @Basic
    private Date fechaRes;
   @ManyToOne
   private Tecnico tecnico;
   private Servicio servicio;
   
    
    public Incidente() {
    }

    public Incidente(int id, String tipoProblema, String descProblema, boolean resuelto, Date fechaRes, Tecnico tecnico, Servicio servicio) {
        this.id = id;
        this.tipoProblema = tipoProblema;
        this.descProblema = descProblema;
        this.resuelto = resuelto;
        this.fechaRes = fechaRes;
        this.tecnico = tecnico;
        this.servicio = servicio;
    }

    public Date getFechaRes() {
        return fechaRes;
    }

    public void setFechaRes(Date fechaRes) {
        this.fechaRes = fechaRes;
    }
    
    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoProblema() {
        return tipoProblema;
    }

    public void setTipoProblema(String tipoProblema) {
        this.tipoProblema = tipoProblema;
    }

    public String getDescProblema() {
        return descProblema;
    }

    public void setDescProblema(String descProblema) {
        this.descProblema = descProblema;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "Incidente{" + "id=" + id + ", tipoProblema=" + tipoProblema + ", descProblema=" + descProblema + ", resuelto=" + resuelto + ", fechaRes=" + fechaRes + ", tecnico=" + tecnico + ", servicio=" + servicio + '}';
    }
   
}
