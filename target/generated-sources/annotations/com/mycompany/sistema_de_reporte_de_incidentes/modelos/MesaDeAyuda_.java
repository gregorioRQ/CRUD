package com.mycompany.sistema_de_reporte_de_incidentes.modelos;

import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Cliente;
import com.mycompany.sistema_de_reporte_de_incidentes.modelos.Incidente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-02-14T01:19:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(MesaDeAyuda.class)
public class MesaDeAyuda_ { 

    public static volatile ListAttribute<MesaDeAyuda, Incidente> incidentes;
    public static volatile SingularAttribute<MesaDeAyuda, Integer> incidentesAtendidos;
    public static volatile SingularAttribute<MesaDeAyuda, String> nombreDelResponsable;
    public static volatile SingularAttribute<MesaDeAyuda, Integer> id;
    public static volatile ListAttribute<MesaDeAyuda, Cliente> cilentesAtendidos;

}