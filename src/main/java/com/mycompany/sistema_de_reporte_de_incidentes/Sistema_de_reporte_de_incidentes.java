/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistema_de_reporte_de_incidentes;

import com.mycompany.sistema_de_reporte_de_incidentes.controladores.Controlador;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.AreaComercial;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Especialidad;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.MesaDeAyuda;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Operador;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.RRHH;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Servicio;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Tecnico;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.TipoDeProblema;
import java.util.ArrayList;
import java.util.Date;


public class Sistema_de_reporte_de_incidentes {

    public static void main(String[] args) {
        Controlador ctrl = new Controlador();
        
        ArrayList<Servicio> servicios = new ArrayList();
        ArrayList<Incidente> incidentes = new ArrayList();
        ArrayList<Cliente> clientes = new ArrayList();
        
        AreaComercial aC = new AreaComercial(7,"Sergio", new Date(), clientes);
        ctrl.crearAreaComercial(aC);
        
        Cliente c = new Cliente(423l, "Lisa", 12312312l, 3764509705l, new Date(), null, servicios, incidentes, aC);
         ctrl.crearCliente(c);
         
         clientes.add(c);
         
         aC.setClientes(clientes);
         
         ctrl.editarAreaComercial(aC);
         
         
    }
    
}
