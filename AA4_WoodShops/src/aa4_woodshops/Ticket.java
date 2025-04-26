package aa4_woodshops;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un ticket o factura de venta en una tienda.
 * Contiene el número de ticket, la fecha, el cliente y las líneas de detalle.
 */
public class Ticket {

    private int id_ticket;
    private Date fecha;
    private Cliente cliente;
    private List<LineaDetalle> lineas;

    // Constructor vacío
    public Ticket() {
        this.lineas = new ArrayList<>();
    }


    /**
     * Constuctor con todos los parametros
     * @param id_ticket contiene numero de ticket
     * @param fecha contiene la fecha
     * @param cliente contiene nombre del cliente
     */
    public Ticket(int id_ticket, Date fecha, Cliente cliente) {
        this.id_ticket = id_ticket;
        this.fecha = fecha;
        this.cliente = cliente;
        this.lineas = new ArrayList<>();
    }


    // Getters y setters
    public int getId_ticket() { return id_ticket; }
    public void setId_ticket(int id_ticket) { this.id_ticket = id_ticket; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<LineaDetalle> getLineas() { return lineas; }
    public void setLineas(List<LineaDetalle> lineas) { this.lineas = lineas; }

    // Agregar línea al ticket
    public void agregarLinea(LineaDetalle linea) {
        this.lineas.add(linea);
    }


    // toString
    @Override
    public String toString() {
        StringBuilder ticketPapel = new StringBuilder();
        ticketPapel.append("Número de ticket: ").append(id_ticket).append("\n");

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        ticketPapel.append("Fecha: ").append(formatoFecha.format(fecha)).append("\n");

        if (cliente instanceof ClienteAnonimo) {
            ticketPapel.append("Cliente: Anónimo\n");
        } else if (cliente instanceof ClienteProfesional profesional) {
            ticketPapel.append("Cliente Profesional: ").append(profesional.getNombre())
                    .append(", DNI: ").append(profesional.getDni()).append("\n");
        } else if (cliente instanceof ClienteWoodFriend woodFriend) {
            ticketPapel.append("Cliente WoodFriend: ").append(woodFriend.getNombre())
                    .append(", DNI: ").append(woodFriend.getDni())
                    .append(", ID Socio: ").append(woodFriend.getId_socio()).append("\n");
        }

        ticketPapel.append("Detalle:\n");
        for (LineaDetalle linea : lineas) {
            ticketPapel.append(linea).append("\n");
        }

        // Calcular el total
        double total = 0;
        for (LineaDetalle linea : lineas) {
            total += linea.getProducto().getPrecioVenta() * linea.getCantidad();
        }

        double descuentoAplicado = 0;
        double porcentajeDescuento = 0;

        if (cliente instanceof ClienteProfesional profesional) {
            porcentajeDescuento = profesional.getDescuento();
            descuentoAplicado = total * (porcentajeDescuento / 100);
            total -= descuentoAplicado;
        }

        // Mostrar siempre el descuento (aunque sea 0)
        ticketPapel.append("Descuento aplicado: ").append(porcentajeDescuento).append("%\n");
        ticketPapel.append("Total del ticket: ").append(String.format("%.2f", total)).append("€");

        return ticketPapel.toString();
    }
}
