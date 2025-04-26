package aa4_woodshops;

/**
 * Clase ClienteWoodFriend que complementa la superclase cliente
 */
public class ClienteWoodFriend extends Cliente{

    private String id_socio;


    // Constructor
    public ClienteWoodFriend(){super();}


    /**
     * Constructor con todos los parametros.
     * @param dni contiene el dni
     * @param nombre contiene el nombre.
     * @param id_socio contiene la ide de socio.
     */
    public ClienteWoodFriend(String dni, String nombre, String id_socio){
        super(dni, nombre);
        this.id_socio = id_socio;
    }


    //getters y setters
    public String getId_socio() {return id_socio;}
    public void setId_socio(String id_socio) {this.id_socio = id_socio;}


    // toString
    @Override
    public String toString(){
        return "Cliente WoodFriend - Nombre: " + getNombre() +
                ", DNI: " + getDni() + ", ID Socio: " + id_socio + ".";
    }
}
