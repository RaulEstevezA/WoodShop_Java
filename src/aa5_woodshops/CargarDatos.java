package aa5_woodshops;

import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;


/**
 * Clase encargada de cargar datos iniciales de ejemplo.
 */
public class CargarDatos {

    /**
     * Carga una instancia de SedeCentral con datos de ejemplo y a la lista de CatalogoProductos
     * @return SedeCentral con tiendas, proveedores y productos predefinidos.
     */
    public static SedeCentral cargar() {
        SedeCentral sede = new SedeCentral();

        // Proveedores
        Proveedor p1 = new Proveedor("12345678A", "Maderas Pepe");
        Proveedor p2 = new Proveedor("87654321B", "Barnices Brillan");

        // Tiendas
        Tiendas tienda1 = new Tiendas("0001", "Tienda Centro");
        Tiendas tienda2 = new Tiendas("0002", "Tienda Norte");


        // Agregar productos
        Tablero t1 = new Tablero("T001", "Tablero MDF 2x1", p1, 2.0f, 1.0f, TipoMadera.MDF);
        Tablero t2 = new Tablero("T002", "Tablero Aglomerado 1.5x0.9", p1, 1.5f, 0.9f, TipoMadera.Aglomerado);
        Tablero t3 = new Tablero("T003", "Tablero Contrachapado 2x1.2", p1, 2.0f, 1.2f, TipoMadera.Contrachapado);
        Tablero t4 = new Tablero("T004", "Tablero MDF 2x2", p1, 2.0f, 2.0f, TipoMadera.MDF);

        sede.agregarProductoCatalogo(t1);
        sede.agregarProductoCatalogo(t2);
        sede.agregarProductoCatalogo(t3);
        sede.agregarProductoCatalogo(t4);


        Barniz b1 = new Barniz("B001", "Barniz nogal 500ml", p2, 500, ColorBarniz.Nogal);
        Barniz b2 = new Barniz("B002", "Barniz caoba 250ml", p2, 250, ColorBarniz.Caoba);
        Barniz b3 = new Barniz("B003", "Barniz incoloro 1000ml", p2, 1000, ColorBarniz.Incoloro);
        Barniz b4 = new Barniz("B004", "Barniz caoba 100ml", p2, 100, ColorBarniz.Caoba);

        sede.agregarProductoCatalogo(b1);
        sede.agregarProductoCatalogo(b2);
        sede.agregarProductoCatalogo(b3);
        sede.agregarProductoCatalogo(b4);


        Articulo a1 = new Articulo("A001", "Silla de roble", p1, TipoArticulo.Silla);
        Articulo a2 = new Articulo("A002", "Mesa plegable", p1, TipoArticulo.Mesa);
        Articulo a3 = new Articulo("A003", "Estantería de pino", p1, TipoArticulo.Estanteria);
        Articulo a4 = new Articulo("A004", "Armario blanco", p1, TipoArticulo.Armario);

        sede.agregarProductoCatalogo(a1);
        sede.agregarProductoCatalogo(a2);
        sede.agregarProductoCatalogo(a3);
        sede.agregarProductoCatalogo(a4);


        // Asignar productos a tiendas
        tienda1.agregarProducto(new ProductosEnTiendas(t1, 29.99, 10));
        tienda1.agregarProducto(new ProductosEnTiendas(t2, 22.50, 8));
        tienda1.agregarProducto(new ProductosEnTiendas(t3, 34.75, 4));
        tienda1.agregarProducto(new ProductosEnTiendas(b1, 8.50, 15));
        tienda1.agregarProducto(new ProductosEnTiendas(b3, 14.99, 7));
        tienda1.agregarProducto(new ProductosEnTiendas(b4, 4.20, 12));
        tienda1.agregarProducto(new ProductosEnTiendas(a1, 59.99, 5));
        tienda1.agregarProducto(new ProductosEnTiendas(a3, 39.99, 3));

        tienda2.agregarProducto(new ProductosEnTiendas(t1, 33.95, 6));
        tienda2.agregarProducto(new ProductosEnTiendas(t4, 45.00, 2));
        tienda2.agregarProducto(new ProductosEnTiendas(b2, 6.25, 10));
        tienda2.agregarProducto(new ProductosEnTiendas(a1, 64.99, 2));
        tienda2.agregarProducto(new ProductosEnTiendas(a2, 79.90, 1));


        // Agregar tiendas a la sede
        sede.agregarTienda(tienda1);
        sede.agregarTienda(tienda2);


        // Clientes
        ClienteProfesional clientePro1 = new ClienteProfesional("11223344A", "Empresa Reformas S.L.", 10.0);
        ClienteProfesional clientePro2 = new ClienteProfesional("22334455B", "Maderas Premium S.A.", 5.0);
        ClienteWoodFriend clienteFriend1 = new ClienteWoodFriend("33445566C", "Lucía P.", "WF123");
        ClienteWoodFriend clienteFriend2 = new ClienteWoodFriend("44556677D", "Carlos M.", "WF124");

        // Agregar clientes a la sede
        sede.agregarCliente(clientePro1);
        sede.agregarCliente(clientePro2);
        sede.agregarCliente(clienteFriend1);
        sede.agregarCliente(clienteFriend2);

        // Crear tickets para tienda1
        Calendar calendario = Calendar.getInstance();

        // Ticket 1
        calendario.set(2025, Calendar.APRIL, 20);
        Ticket ticket1 = new Ticket(tienda1.generarNumeroTicket(), calendario.getTime(), clientePro1);
        ticket1.agregarLinea(new LineaDetalle(tienda1.getInventario().get(0), 2));
        ticket1.agregarLinea(new LineaDetalle(tienda1.getInventario().get(3), 5));
        tienda1.agregarTicket(ticket1);

        // Ticket 2
        calendario.set(2025, Calendar.APRIL, 22);
        Ticket ticket2 = new Ticket(tienda1.generarNumeroTicket(), calendario.getTime(), clienteFriend1);
        ticket2.agregarLinea(new LineaDetalle(tienda1.getInventario().get(1), 1));
        ticket2.agregarLinea(new LineaDetalle(tienda1.getInventario().get(6), 1));
        tienda1.agregarTicket(ticket2);

        // Ticket 3
        calendario.set(2025, Calendar.APRIL, 23);
        Ticket ticket3 = new Ticket(tienda2.generarNumeroTicket(), calendario.getTime(), clientePro2);
        ticket3.agregarLinea(new LineaDetalle(tienda2.getInventario().get(0), 3));
        ticket3.agregarLinea(new LineaDetalle(tienda2.getInventario().get(2), 4));
        tienda2.agregarTicket(ticket3);
        return sede;
    }
}
