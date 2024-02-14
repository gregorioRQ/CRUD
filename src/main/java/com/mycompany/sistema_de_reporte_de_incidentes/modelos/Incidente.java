/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private String descripcion;
    @Basic
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic 
    private String hora;
    @Basic
    private int tiempoEstimadoResolucion;
    @Basic
    private int colchonDeHs;
    private boolean notificadoAlTecnico;
   @ManyToOne
   private Tecnico tecnico;
   @ManyToOne
   private Operador operador;
   @ManyToOne
   private Cliente cliente;
   @ManyToOne
   private MesaDeAyuda mesaDeAyuda;

    public Incidente() {
    }

    public Incidente(int id, String tipoProblema, String descripcion, String estado, Date fecha, String hora, int tiempoEstimadoResolucion, int colchonDeHs, boolean notificadoAlTecnico, Tecnico tecnico, Operador operador, Cliente cliente, MesaDeAyuda mesaDeAyuda) {
        this.id = id;
        this.tipoProblema = tipoProblema;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
        this.tiempoEstimadoResolucion = tiempoEstimadoResolucion;
        this.colchonDeHs = colchonDeHs;
        this.notificadoAlTecnico = notificadoAlTecnico;
        this.tecnico = tecnico;
        this.operador = operador;
        this.cliente = cliente;
        this.mesaDeAyuda = mesaDeAyuda;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public int getTiempoEstimadoResolucion() {
        return tiempoEstimadoResolucion;
    }

    public void setTiempoEstimadoResolucion(int tiempoEstimadoResolucion) {
        this.tiempoEstimadoResolucion = tiempoEstimadoResolucion;
    }

    public int getColchonDeHs() {
        return colchonDeHs;
    }

    public void setColchonDeHs(int colchonDeHs) {
        this.colchonDeHs = colchonDeHs;
    }

    public boolean isNotificadoAlTecnico() {
        return notificadoAlTecnico;
    }

    public void setNotificadoAlTecnico(boolean notificadoAlTecnico) {
        this.notificadoAlTecnico = notificadoAlTecnico;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

 

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MesaDeAyuda getMesaDeAyuda() {
        return mesaDeAyuda;
    }

    public void setMesaDeAyuda(MesaDeAyuda mesaDeAyuda) {
        this.mesaDeAyuda = mesaDeAyuda;
    }

    @Override
    public String toString() {
        return "Incidente{" + "id=" + id + ", tipoProblema=" + tipoProblema + ", descripcion=" + descripcion + ", estado=" + estado + ", fecha=" + fecha + ", hora=" + hora + ", tiempoEstimadoResolucion=" + tiempoEstimadoResolucion +  ", colchonDeHs=" + colchonDeHs + ", notificadoAlTecnico=" + notificadoAlTecnico + ", tecnico=" + tecnico + ", operador=" + operador + ", cliente=" + cliente + ", mesaDeAyuda=" + mesaDeAyuda + '}';
    }


}
