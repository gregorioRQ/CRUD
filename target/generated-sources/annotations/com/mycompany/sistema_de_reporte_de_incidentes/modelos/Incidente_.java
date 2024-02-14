package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.MesaDeAyuda;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Operador;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Tecnico;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Incidente.class)
public class Incidente_ { 

    public static volatile SingularAttribute<Incidente, String> descripcion;
    public static volatile SingularAttribute<Incidente, String> estado;
    public static volatile SingularAttribute<Incidente, MesaDeAyuda> mesaDeAyuda;
    public static volatile SingularAttribute<Incidente, String> hora;
    public static volatile SingularAttribute<Incidente, String> tipoProblema;
    public static volatile SingularAttribute<Incidente, Tecnico> tecnico;
    public static volatile SingularAttribute<Incidente, Integer> colchonDeHs;
    public static volatile SingularAttribute<Incidente, Date> fecha;
    public static volatile SingularAttribute<Incidente, Cliente> cliente;
    public static volatile SingularAttribute<Incidente, Boolean> notificadoAlTecnico;
    public static volatile SingularAttribute<Incidente, Integer> tiempoEstimadoResolucion;
    public static volatile SingularAttribute<Incidente, Integer> id;
    public static volatile SingularAttribute<Incidente, Operador> operador;

}