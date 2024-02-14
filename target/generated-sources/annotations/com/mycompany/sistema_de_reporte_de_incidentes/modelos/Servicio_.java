package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.TipoDeProblema;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, Cliente> cliente;
    public static volatile SingularAttribute<Servicio, Integer> id;
    public static volatile SingularAttribute<Servicio, String> nombre;
    public static volatile ListAttribute<Servicio, TipoDeProblema> tiposDeProblemas;

}