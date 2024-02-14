package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.AreaComercial;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Servicio;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Date> fechaBaja;
    public static volatile SingularAttribute<Cliente, String> razonSocial;
    public static volatile SingularAttribute<Cliente, AreaComercial> areaComercial;
    public static volatile SingularAttribute<Cliente, Long> cuit;
    public static volatile SingularAttribute<Cliente, Date> fechaAlta;
    public static volatile ListAttribute<Cliente, Servicio> listaServicios;
    public static volatile SingularAttribute<Cliente, Long> id;
    public static volatile SingularAttribute<Cliente, Long> telefono;
    public static volatile ListAttribute<Cliente, Incidente> listaIncidentes;

}