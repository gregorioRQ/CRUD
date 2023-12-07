/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import com.google.protobuf.TextFormat.ParseException;
import controladores.Controladores;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class PaneldelTecnico {
    Scanner sc = new Scanner(System.in);
    Controladores control = new Controladores();
    
    public void verificar() throws java.text.ParseException{
        //mueestra si el tecnico tiene trabajos
        System.out.println("ingrese su id de tecnico");   
        int id = sc.nextInt();
        Tecnico tec  = control.buscarTecnico(id);
        System.out.println("Tecnico "+tec.getNombre());
        
        if(tec.getIncAresolver() > 0){
            System.out.println("Tiene tareas");
           tec.getListaIncidentes()
                   .forEach(i ->  System.out.print("Tarea: "+ i.getTipoProblema() +" -- id Tarea: "+ i.getId() +" -- Descripcion del cliente: "+ i.getDescProblema()));
 
            List<Incidente> listainc = tec.getListaIncidentes().stream().collect(Collectors.toList());
            
            
            System.out.println("Desea resolver alguna? ingrese el id de la tarea");
            int idT = sc.nextInt();
            System.out.println("ingrese la fecha // Ejemplo de fecha en formato dia-mes-año");
            String fech = sc.next();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
  
            Date fecha = formatoFecha.parse(fech);
            
            for(Incidente inc: listainc){
                if(idT == inc.getId()){ 
                    inc.setFechaRes(fecha);
                    inc.setResuelto(true);
                    control.editarIncidente(inc);
                    
                    tec.setCantIncResueltos(tec.getCantIncResueltos() + 1);
                    tec.setIncAresolver(tec.getIncAresolver() - 1);
                    control.editarTecnico(tec);
                }
            }
                System.out.println("resuelto");
            
        }else{
            System.out.println("Estas libre");
        }
        
    };
    
}
