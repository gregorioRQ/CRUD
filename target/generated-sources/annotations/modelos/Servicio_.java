package modelos;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Cliente;
import modelos.Incidente;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-06T23:02:17", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, Cliente> cliente;
    public static volatile SingularAttribute<Servicio, Integer> id;
    public static volatile SingularAttribute<Servicio, String> nombre;
    public static volatile ListAttribute<Servicio, Incidente> listaIncidentes;

}