/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import controladores.Controladores;
import static java.awt.SystemColor.control;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import static modelos.Incidente_.tipoProblema;

/**
 *
 * @author PC
 */
public class Operador {
     Scanner sc =  new Scanner(System.in);
      Controladores control = new Controladores();
     
    public void llamar(){
    
    int dato;
   
    System.out.println("Ingrese su id");
    dato = sc.nextInt();
    
     Cliente clie  = control.buscarCliente(dato);
     System.out.println("Nombre cliente: " +  clie.getNombre().toString());
    
    
    System.out.println("Servicios asociados: ");
    
    for(Servicio ser : clie.getListaServicios()){
        System.out.println("id "+ser.getId() +" - "+ser.getNombre());
        
    };
        
    
        System.out.println("Por cual de los servicios desea reportar un incidente? escriba el nombre del servicio");
        String tipoProblema = sc.next(); 
        System.out.println("Añada un id");
        int idp = sc.nextInt();
        System.out.println("Añada una descripcion del problema");
        String descProblema =sc.next();
       
       
  
        
        //listado de tecnicos disponibles para resolver el problema
        ArrayList<Tecnico> listaTecnicos = control.listaTecnico();
      
         List<Tecnico> tecsdisp = listaTecnicos.stream()
                 .filter(t -> t.getEspecialidad1().equalsIgnoreCase(tipoProblema) || t.getEspecialidad2().equalsIgnoreCase(tipoProblema))
                 .collect(Collectors.toList());
         
         if(tecsdisp.size() > 0){
               System.out.println("Estos son los tecnicos disponibles: ");
              for(Tecnico tec:tecsdisp)
             System.out.println(tec.getNombre() +" -- Id: "+tec.getId());
         }else{
             System.out.println("no hay tecnicos disponibles para este incidente");
         }
         

        System.out.println("Escriba el id de el tecnico que quiera seleccionar");
        int idTec = sc.nextInt();
        
        for(Tecnico tec1: tecsdisp){
            if(idTec == tec1.getId()){
                System.out.println("Tiempo para resolver "+tec1.getEspecialidad1()+" -- "+tec1.getTiempoEstimado1()+"hs");
                System.out.println("Tiempo para resolver "+tec1.getEspecialidad2()+" -- "+tec1.getTiempoEstimado2()+"hs");
                System.out.println("Incidente ingresado");   
                          
                  Incidente incidente = new Incidente(idp,tipoProblema, descProblema,false, null , null,null);
                    control.crearIncidente(incidente); 
                      tec1.getListaIncidentes().add(incidente);
                 tec1.setIncAresolver(1); 
                 control.editarTecnico(tec1);
            }
        }
    }
        
      
    }

  
