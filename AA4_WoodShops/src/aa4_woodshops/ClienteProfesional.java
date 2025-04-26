package aa4_woodshops;

/**
 * Clase para construir los clientes profesionales.
 * Subclase de Cliente, agrega el atributo descuento.
 */
public class ClienteProfesional extends Cliente {

    private double descuento;


    // Constructor
    public ClienteProfesional() {super();}


    /**
     * Constructor con todos los parámetros
     * @param dni contiene el dni en string
     * @param nombre contiene el nombre
     * @param descuento contiene el descuento
     */
    public ClienteProfesional(String dni, String nombre, double descuento) {
        super(dni, nombre);  // Llama al constructor con parámetros de Cliente
        this.descuento = descuento;
    }


    // getters y setters
    public double getDescuento() {return descuento;}
    public void setDescuento(double descuento) {this.descuento = descuento;}


    // toString
    @Override
    public String toString() {
        return "Cliente profesional - Nombre: " + getNombre() +
                ", DNI: " + getDni() + ", Descuento: " + descuento + "%";
    }
}