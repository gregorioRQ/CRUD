package modelos;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Servicio;
import modelos.Tecnico;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-06T23:02:17", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Incidente.class)
public class Incidente_ { 

    public static volatile SingularAttribute<Incidente, Date> fechaRes;
    public static volatile SingularAttribute<Incidente, Servicio> servicio;
    public static volatile SingularAttribute<Incidente, String> tipoProblema;
    public static volatile SingularAttribute<Incidente, String> descProblema;
    public static volatile SingularAttribute<Incidente, Integer> id;
    public static volatile SingularAttribute<Incidente, Tecnico> tecnico;
    public static volatile SingularAttribute<Incidente, Boolean> resuelto;

}