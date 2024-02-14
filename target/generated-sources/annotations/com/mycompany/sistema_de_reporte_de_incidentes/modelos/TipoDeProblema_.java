package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Especialidad;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Servicio;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(TipoDeProblema.class)
public class TipoDeProblema_ { 

    public static volatile SingularAttribute<TipoDeProblema, String> descripcion;
    public static volatile SingularAttribute<TipoDeProblema, String> consideraciones;
    public static volatile SingularAttribute<TipoDeProblema, Servicio> servicio;
    public static volatile SingularAttribute<TipoDeProblema, ArrayList> areasDeConocimientoRequerida;
    public static volatile ListAttribute<TipoDeProblema, Especialidad> especialidades;
    public static volatile SingularAttribute<TipoDeProblema, String> tiempoMaxDeResolucion;
    public static volatile SingularAttribute<TipoDeProblema, Long> id;
    public static volatile SingularAttribute<TipoDeProblema, Boolean> permitirColchonDeHs;
    public static volatile SingularAttribute<TipoDeProblema, String> nombre;
    public static volatile SingularAttribute<TipoDeProblema, String> nivelDePrioridad;
    public static volatile SingularAttribute<TipoDeProblema, Date> fechaDeCreacion;

}