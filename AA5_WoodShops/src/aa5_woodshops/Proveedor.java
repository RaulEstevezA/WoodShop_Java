package aa5_woodshops;

/**
 * Clase que representa un proveedor de productos.
 * Contiene el NIF y el nombre del proveedor
 */
public class Proveedor {

    private String nif;
    private String nombre;


    //Constructores
    public Proveedor(){}


    /**
     * Constructor con todos los parámetros.
     * @param nif NIF del proveedor.
     * @param nombre Nombre del proveedor.
     */
    public Proveedor(String nif, String nombre){
        this.nif = nif;
        this.nombre = nombre;
    }


    // getters y setters
    public String getNif() {return nif;}
    public void setNif(String nif) {this.nif = nif;}


    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}


    //toString
    @Override
    public String toString(){
        return nombre + " (" + nif + ")";
    }
}
