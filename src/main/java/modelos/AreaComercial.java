/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import controladores.Controladores;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class AreaComercial {
    
    Controladores control = new Controladores();
    Scanner sc = new Scanner(System.in);
    public void gestionarClientes(){
        System.out.println("Cliente 1-añadir, 2-modificar, 3-eliminar, 4-revisar su incidente");
        int opUs = sc.nextInt();
        
        switch(opUs){
            case 1: crearCli();
            break;
            case 2: editarCli();
            break;
            case 3: eliminarCli();
            break;
            case 4: checkearIncidente();
            break;
        }
    }
            
    
    public void crearCli(){
        System.out.println("id");
        int id = sc.nextInt();
        System.out.println("nombre");
        String nombre = sc.next();
        System.out.println("Que servicio desea contratar? ");
        System.out.println("22 -- servicio01; 33 -- servicio02");
        int op = sc.nextInt();
        String nombreS = "";
        
        switch(op){
            case 22: id = 22; nombreS = "servicio01";
            break;
            case 33: id = 33; nombreS = "servicio02";
            break;
        }
        
        ArrayList<Servicio> listemporal = new ArrayList();
        ArrayList<Incidente> ls = new ArrayList();
        
        Cliente cli = new Cliente(id, nombre, listemporal);
        control.crearCliente(cli);
        
        Servicio ser = new Servicio(id, nombreS, cli, ls);
        control.crearServicio(ser);
        
        ArrayList<Servicio> lss = new ArrayList();
        lss.add(ser);
        
        cli.setListaServicios(lss);
        
        control.editarCliente(cli);
        
 
    }
    
    public void eliminarCli(){
        System.out.println("ingrese su id");
        int id = sc.nextInt();
        control.eliminarCliente(id);
        System.out.println("Cliente eliminado");
    }
    
    
    public void editarCli(){
        System.out.println("ingrese su id");
        int id = sc.nextInt();
        Cliente cli1 = control.buscarCliente(id);
        
        System.out.println("editar: 1-nombre");
        int op = sc.nextInt();
        switch(op){
            case 1: System.out.println("ingrese su nuevo nombre");
                      String nom = sc.next();
                       cli1.setNombre(nom);
                        control.editarCliente(cli1);
                        System.out.println("Listo");
                   break;
        };

    }
    
    
    public void checkearIncidente(){
        System.out.println("ingrese su id");
        int id = sc.nextInt();
        Cliente cli = control.buscarCliente(id);
        System.out.println("-- " +cli.getNombre());
        
        System.out.println("ingrese su id incidente");
        int idi = sc.nextInt();
        Incidente inc = control.bucsarIncidente(idi);
        System.out.println("Incidente: "+inc.getTipoProblema());
        
        if(inc.isResuelto()){
            System.out.println("Incidente resuelto por");
        }else{
            System.out.println("Su incidente todavia no ha sido resuelto");
        }
        
    }
}
