/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistema_de_reporte_de_incidentes;



import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.AreaComercial;
import modelos.Operador;
import modelos.PaneldelTecnico;
import modelos.RRHH;



/**
 *
 * @author PC
 */
public class Sistema_de_reporte_de_incidentes {

    public static void main(String[] args) {
        
  
        try {
            Operador op = new Operador();
            PaneldelTecnico pt = new PaneldelTecnico();
            
            RRHH rh = new RRHH();
            AreaComercial ac = new AreaComercial();
            
            Scanner sc = new Scanner(System.in);
            System.out.println("--- 1 agregar tec etc, 2 agregar cli etc, 3 atender agragar problema etc,  5 añadir servicio");
            System.out.println("4 si sos un tecnico, para ver tus notificaciones");
            int opUs = sc.nextInt();
            
            switch(opUs){
                case 1: rh.gestionRH();
                break;
                case 2: ac.gestionarClientes();
                break;
                case 3: op.llamar();
                break;
                case 4: pt.verificar();
                break;
                case 5: op.crearS();
                break;
            };
        } catch (ParseException ex) {
            Logger.getLogger(Sistema_de_reporte_de_incidentes.class.getName()).log(Level.SEVERE, null, ex);
        }


    }  
    
}
