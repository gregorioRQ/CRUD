package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(AreaComercial.class)
public class AreaComercial_ { 

    public static volatile SingularAttribute<AreaComercial, Date> ultimaActualizacion;
    public static volatile SingularAttribute<AreaComercial, String> nombreDelResponsable;
    public static volatile SingularAttribute<AreaComercial, Integer> id;
    public static volatile ListAttribute<AreaComercial, Cliente> clientes;

}