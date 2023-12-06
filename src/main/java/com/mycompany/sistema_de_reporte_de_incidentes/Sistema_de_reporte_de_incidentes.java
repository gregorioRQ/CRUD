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
            System.out.println("********************************************************************************************************************");
            System.out.println("********************************************************************************************************************");

            System.out.println("-- 1 para  gestion de tecnicos, emitir un informe o saber quien fue el tecnico con mas incidentes resueltos en x dias");
            System.out.println("-- 2 para gestion de clientes o para chequear por un incidente de x cliente");
            System.out.println("-- 3 para reportar un incidente con un servicio");
            System.out.println("-- 4 si sos un tecnico, para ver tus notificaciones");
            System.out.println("-- 5 para añadir un nuevo servicio");
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
