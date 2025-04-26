package aa4_woodshops;

/**
 * Clase que representa un cliente anónimo que no almacena
 * datos personales.
 */
public class ClienteAnonimo extends Cliente {


    // Constructor
    public ClienteAnonimo() {
        super(null, null);
    }


    // toString
    @Override
    public String toString() {
        return "Cliente Anonimo";
    }
}
