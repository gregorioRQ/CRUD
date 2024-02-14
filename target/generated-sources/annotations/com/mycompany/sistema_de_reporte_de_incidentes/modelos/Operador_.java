package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Operador.class)
public class Operador_ { 

    public static volatile SingularAttribute<Operador, String> areaDeResponsabilidad;
    public static volatile SingularAttribute<Operador, Date> fechaIngreso;
    public static volatile SingularAttribute<Operador, String> consideraciones;
    public static volatile SingularAttribute<Operador, Integer> tiempoEstimadoDeResolucion;
    public static volatile SingularAttribute<Operador, Integer> id;
    public static volatile SingularAttribute<Operador, Long> telefono;
    public static volatile SingularAttribute<Operador, String> nombre;
    public static volatile ListAttribute<Operador, Incidente> listaIncidentes;

}