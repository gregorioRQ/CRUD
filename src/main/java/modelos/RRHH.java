/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import controladores.Controladores;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class RRHH {
    Controladores control = new Controladores();
    Scanner sc = new Scanner(System.in);
    
    
    public void gestionRH(){
        System.out.println("1-añadir tecnico, 2-editar, 3-emitir informe, 4 tecnico con mas incidentes resueltos");
        int op = sc.nextInt();
        
        switch(op){
            case 1: crearTec();
            break;
            case 2: editarTec();
            break;
            case 3: emitirInforme();
            break;
            case 4: System.out.println("dias");
            int d = sc.nextInt();
                String tec = tecnicoConMasIncidentes(d);
                System.out.println(tec);
        }
    }
    
    public void crearTec(){
                System.out.println("id");
                int id = sc.nextInt();
                
                System.out.println("nombre");
               String nombre = sc.next();
               
               System.out.println("Especialidad 1");
               String esp1 = sc.next();
               
               System.out.println("Tiempo estimado de resolucion");
                int tiempRes = sc.nextInt();
               
               System.out.println("Especialidad 2");
               String esp2 = sc.next();
               
               System.out.println("Tiempo estimado de resolucion");
               int tiempRes2 = sc.nextInt();
               
               ArrayList<Incidente> listaIncidentesProv = new ArrayList();
                Tecnico tec = new Tecnico (id, nombre, esp1,
                        tiempRes, 
                        esp2, tiempRes2, 
                        0, "0", listaIncidentesProv);
                control.crearTecnico(tec);
           
    };
    
    public void editarTec(){
        System.out.println("Escriba su id de tecnico");
        int id = sc.nextInt();
        Tecnico tecEdit  = control.buscarTecnico(id);
        System.out.println(tecEdit.toString());
    }
    
    public void emitirInforme(){
        ArrayList<Tecnico> listtec = control.listaTecnico();
        
        for(Tecnico tec: listtec){
            System.out.println("Tecnico "+ tec.getNombre());
            
            if(tec.getListaIncidentes().size() > 0){
                 tec.getListaIncidentes().forEach((i)->{
                    if(i.isResuelto()){
                        System.out.println("Actividades: ");
                        System.out.println(i.getTipoProblema()+" -- estado resuelto");
                    }else{
                        System.out.println("Actividades: ");
                        System.out.println(i.getTipoProblema() + " -- estado sin resolver");
                }
            });
        }else{
                System.out.println("no tiene actividades aun");
            }
        
        }
    }
    
     public static String tecnicoConMasIncidentes(int dias) {
        Map<String, Integer> incidentesPorTecnico = new HashMap<>();
        ArrayList<Incidente> incidentes = new ArrayList();
                
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Calcular la fecha hace N días
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, -dias);
        Date fechaN = calendar.getTime();

        // Contar incidentes resueltos por cada técnico en los últimos N días
        for (Incidente incidente : incidentes) {
            if (incidente.getFechaRes().after(fechaN) && incidente.getFechaRes().before(fechaActual)) {
                Tecnico tecnico = incidente.getTecnico();
                incidentesPorTecnico.put(tecnico.getNombre(), incidentesPorTecnico.getOrDefault(tecnico, 0) + 1);
            }
        }

        // Encontrar al técnico con más incidentes resueltos
        int maxIncidentes = 0;
        String tecnicoConMasIncidentes = null;
        for (Map.Entry<String, Integer> entry : incidentesPorTecnico.entrySet()) {
            if (entry.getValue() > maxIncidentes) {
                maxIncidentes = entry.getValue();
                tecnicoConMasIncidentes = entry.getKey();
            }
        }

        return tecnicoConMasIncidentes;
    }
    
}


