package aa5_woodshops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa una tienda con su inventario
 * Contiene datos de id y nombre de la tienda, listado de productos
 * y listado de tickets
 */
public class Tiendas {
    private String id;
    private String nombre;
    private List<ProductosEnTiendas> inventario;
    private List<Ticket> tickets;
    private int contadorTickets = 0; // O el número de ticket inicial que prefieras


    // Constructores
    private Tiendas(){ inventario = new ArrayList<>();}

    /**
     * Constructores con todos los atributos.
     * @param id Identificador unico de la tienda.
     * @param nombre Nombre de la tienda.
     */
    public Tiendas(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.inventario = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }


    // getters y setters
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public List<ProductosEnTiendas> getInventario() {return inventario;}
    public void setInventario(List<ProductosEnTiendas> inventario) {this.inventario = inventario;}

    public List<Ticket> getTickets() {return tickets;}
    public void setTickets(List<Ticket> tickets) {this.tickets = tickets;}


    // Agregar productos al inventario.
    public void agregarProducto(ProductosEnTiendas producto) {
        inventario.add(producto);
    }


    // Agregar ticket
    public void agregarTicket(Ticket ticket) {
        tickets.add(ticket);
    }


    // Autogenerador de numero de ticket
    public int generarNumeroTicket() {
        return contadorTickets++;
    }


    // Calcular ventas totales entre fechas de una tienda
    public void calcularVentasEntreFechas(Date inicio, Date fin) {
        double totalVentas = 0;


        // obtiene el total por ticket con el descuento, en caso de que tenga
        for (Ticket ticket : tickets) {
            Date fecha = ticket.getFecha();
            if (!fecha.before(inicio) && !fecha.after(fin)) {
                totalVentas += ticket.calcularTotal();
            }
        }


        // imprime el total de las ventas
        System.out.printf(" - %s (ID: %s): %.2f €\n", getNombre(), getId(), totalVentas);
    }


    // toString
    @Override
    public String toString() {
        return "Tienda " + nombre + "(ID: " + id + ")";
    }
}
