package modelos;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Incidente;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-06T23:02:17", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tecnico.class)
public class Tecnico_ { 

    public static volatile SingularAttribute<Tecnico, Integer> tiempoEstimado2;
    public static volatile SingularAttribute<Tecnico, String> cantIncResueltos;
    public static volatile SingularAttribute<Tecnico, Integer> tiempoEstimado1;
    public static volatile SingularAttribute<Tecnico, String> especialidad2;
    public static volatile SingularAttribute<Tecnico, String> especialidad1;
    public static volatile SingularAttribute<Tecnico, Integer> incAresolver;
    public static volatile SingularAttribute<Tecnico, Integer> id;
    public static volatile SingularAttribute<Tecnico, String> nombre;
    public static volatile ListAttribute<Tecnico, Incidente> listaIncidentes;

}