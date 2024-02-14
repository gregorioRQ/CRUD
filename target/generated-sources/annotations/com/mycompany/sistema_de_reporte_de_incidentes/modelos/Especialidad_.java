package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Tecnico;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.TipoDeProblema;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Especialidad.class)
public class Especialidad_ { 

    public static volatile SingularAttribute<Especialidad, String> descripcion;
    public static volatile SingularAttribute<Especialidad, String> areaDeConocimiento;
    public static volatile ListAttribute<Especialidad, Tecnico> tecnicos;
    public static volatile SingularAttribute<Especialidad, Long> id;
    public static volatile SingularAttribute<Especialidad, String> nombre;
    public static volatile ListAttribute<Especialidad, TipoDeProblema> tiposDeProblemas;

}