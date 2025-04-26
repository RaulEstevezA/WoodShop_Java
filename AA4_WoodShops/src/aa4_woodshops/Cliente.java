package aa4_woodshops;

/**
 * Clase abstracta que complementa subclases:
 * ClienteAnonimo, ClienteProfesional y ClienteWoodFriend.
 * Contiene nombre y nif del cliente.
 */
public abstract class Cliente {
    private String dni;
    private String nombre;


    // Constructores
    public Cliente(){}


    /**
     * Constructor con todos los parámetros
     * @param dni string del dni
     * @param nombre string del nombre
     */
    public Cliente(String dni, String nombre){
        this.dni = dni;
        this.nombre = nombre;
    }


    // getters y setters
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
}
