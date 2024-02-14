package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Especialidad;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.RRHH;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tecnico.class)
public class Tecnico_ { 

    public static volatile SingularAttribute<Tecnico, Date> fechaIngreso;
    public static volatile SingularAttribute<Tecnico, Long> cantIncResueltos;
    public static volatile ListAttribute<Tecnico, Incidente> incidentes;
    public static volatile SingularAttribute<Tecnico, String> estado;
    public static volatile SingularAttribute<Tecnico, String> tiempoEstimadoDeResolucion;
    public static volatile ListAttribute<Tecnico, Especialidad> especialidades;
    public static volatile SingularAttribute<Tecnico, RRHH> rh;
    public static volatile SingularAttribute<Tecnico, Integer> incAresolver;
    public static volatile SingularAttribute<Tecnico, Long> id;
    public static volatile SingularAttribute<Tecnico, String> medioDeNotificacion;
    public static volatile SingularAttribute<Tecnico, Long> telefono;
    public static volatile SingularAttribute<Tecnico, String> nombre;

}