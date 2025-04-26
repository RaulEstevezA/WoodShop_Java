package aa4_woodshops;
import java.util.Date;

/**
 * Parte principal del programa desde donde se gestiona todo.
 * Desde esta clase se ejecuta el programa y se accede al menú principàl.
 *
 * @author Raúl Estévez para la asignatura Programación Orientada a Objetos.
 */
public class AA4_WoodShops {


    /**
     * Parte principal del programa. Llama al método (inicio) que gestiona
     * toda la logica del programa
     * @param args Argumentos de lina de comandos, no utilizados.
     */
    public static void main(String[] args) {
        inicio();
    }


    /**
     * Inicia la lógica principal del programa. Carga lso datos principales,
     * gestiona las acciones del usuario y deriva a las funciones necesarias.
     */
    public static void inicio(){

        // Cargar la sede con los datos de ejemplo
        SedeCentral sede = CargarDatos.cargar();

        System.out.println("Bienvenido al programa de gestión de tiendas de " + sede.getNombre());

        int numero;
        boolean salir = false;
        do {
            System.out.println();
            // Llamada al menu principal
            numero = Verificadores.pedirNumero("""
                            Que desea hacer:
                            1 Añadir una tienda
                            2 Añadir un producto.
                            3 Añadir un producto a una tienda.
                            4 Listar productos de una tienda.
                            5 Listar productos por ID.
                            6 Añadir clientes.
                            7 Mostrar clientes.
                            8 Añadir ticket de venta
                            9 Listar tickets
                            0 Salir""", 0, 9);
            switch (numero) {
                case 1:
                    anadirTienda(sede);
                    break;
                case 2:
                    anadirProducto(sede);
                    break;
                case 3:
                    productoTienda(sede);
                    break;
                case 4:
                    verProductosTienda(sede);
                    break;
                case 5:
                    productoID(sede);
                    break;
                case 6:
                    anadirCliente(sede);
                    break;
                case 7:
                    mostrarTodosClientes(sede);
                    break;
                case 8:
                    anadirTicket(sede);
                    break;
                case 9:
                    mostrarTicket(sede);
                    break;
                case 0:
                    salir = true;
            }
        } while (!salir);
    }


    // Añadimos tienda nueva
    public static void anadirTienda(SedeCentral sede) {
        int idNumerico = Verificadores.pedirNumero(
                "Introduce el ID numérico de la nueva tienda (entre 1 y 9999):", 1, 9999);
        String id = String.format("%04d", idNumerico);

        String nombre = Verificadores.pedirTexto(
                "Introduce el nombre de la nueva tienda:");

        // Crear y añadir la tienda
        Tiendas nueva = new Tiendas(id, nombre);
        sede.agregarTienda(nueva);

        System.out.println("Tienda añadida correctamente: " + nombre + " (ID: " + id + ")");
    }


    // Añadimos producto nuevo
    public static void anadirProducto(SedeCentral sede) {
        int tipo = Verificadores.pedirNumero("""
                ¿Qué tipo de producto quieres añadir?
                1. Tablero
                2. Barniz
                3. Artículo
                """, 1, 3);

        int codigo = Verificadores.pedirNumero("Código del producto:", 0, 999);
        String descripcion = Verificadores.pedirTexto("Descripción:");
        Proveedor proveedor = new Proveedor(
                Verificadores.pedirDocumento("NIF del proveedor:"),
                Verificadores.pedirTexto("Nombre del proveedor:")
        );

        Producto producto = null;

        switch (tipo) {
            case 1 -> {
                float altura = (float) Verificadores.pedirDecimal("Altura:", 0.01, -1);
                float anchura = (float) Verificadores.pedirDecimal("Anchura:", 0.01, -1);

                for (TipoMadera tm : TipoMadera.values()) System.out.println("- " + tm);
                TipoMadera tipoMadera = Verificadores.pedirEnum("Tipo de madera:", TipoMadera.class);

                String codigoFinal = "T" + String.format("%03d", codigo);

                // Verificar si ya existe en el catálogo
                if (sede.buscarProductoPorCodigo(codigoFinal) != null) {
                    System.out.println("Ya existe un producto con ese código en el catálogo.");
                    return;
                }

                producto = new Tablero(codigoFinal, descripcion, proveedor, altura, anchura, tipoMadera);
            }

            case 2 -> {
                int mililitros = Verificadores.pedirNumero("Mililitros:", 1, Integer.MAX_VALUE);

                for (ColorBarniz cb : ColorBarniz.values()) System.out.println("- " + cb);
                ColorBarniz color = Verificadores.pedirEnum("Color:", ColorBarniz.class);

                String codigoFinal = "B" + String.format("%03d", codigo);

                if (sede.buscarProductoPorCodigo(codigoFinal) != null) {
                    System.out.println("Ya existe un producto con ese código en el catálogo.");
                    return;
                }

                producto = new Barniz(codigoFinal, descripcion, proveedor, mililitros, color);
            }

            case 3 -> {
                for (TipoArticulo ta : TipoArticulo.values()) System.out.println("- " + ta);
                TipoArticulo tipoArticulo = Verificadores.pedirEnum(
                        "Tipo de artículo:", TipoArticulo.class);

                String codigoFinal = "A" + String.format("%03d", codigo);

                if (sede.buscarProductoPorCodigo(codigoFinal) != null) {
                    System.out.println("Ya existe un producto con ese código en el catálogo.");
                    return;
                }

                producto = new Articulo(codigoFinal, descripcion, proveedor, tipoArticulo);
            }
        }

        sede.agregarProductoCatalogo(producto);
        System.out.println("Producto añadido al catálogo correctamente: " + producto.getCodigo() + "\n");
    }


    // Vinculamos un producto a una tienda
    public static void productoTienda(SedeCentral sede) {

        // Verificador de tienda
        Tiendas tiendaSeleccionada = Verificadores.pedirTiendaPorID(sede);


        // Mostrar catálogo completo
        sede.mostrarCatalogoProductos();

        String codigo = Verificadores.pedirTexto("Introduce el código del producto a asignar:");

        Producto producto = sede.buscarProductoPorCodigo(codigo);
        if (producto == null) {
            System.out.println("No existe ningún producto con ese código en el catálogo.");
            return;
        }


        // Verificar que el producto no esté ya en esa tienda
        boolean existe = tiendaSeleccionada.getInventario().stream()
                .anyMatch(p -> p.getProducto().getCodigo().equalsIgnoreCase(codigo));
        if (existe) {
            System.out.println("Ese producto ya está registrado en esta tienda.");
            return;
        }


        double precio = Verificadores.pedirDecimal("Precio de venta:", 0.01, -1);
        int stock = Verificadores.pedirNumero("Stock disponible:", 1, Integer.MAX_VALUE);


        ProductosEnTiendas pet = new ProductosEnTiendas(producto, precio, stock);
        tiendaSeleccionada.agregarProducto(pet);


        System.out.println("Producto " + producto.getCodigo() + " añadido a " + tiendaSeleccionada.getNombre() + " correctamente.");
    }


    // Vemos productos de tienda (parte del menu)
    public static void verProductosTienda(SedeCentral sede){
        // Mostrar tiendas disponibles con el ID
        sede.mostrarTiendas();


        // Verificar que se escoge una tienda listada
        int idNumero = Verificadores.pedirNumero(
                "Introduce el ID de la tienda quieres mostrar los productos:", 0, 9999);
        String idTienda = String.format("%04d", idNumero);

        Tiendas tiendaSeleccionada = null;
        for (Tiendas t : sede.getTiendas()) {
            if (t.getId().equalsIgnoreCase(idTienda)) {
                tiendaSeleccionada = t;
                break;
            }
        }

        if (tiendaSeleccionada == null) {
            System.out.println("No se ha encontrado una tienda con ese ID.");
            return;
        }


        int opcion = Verificadores.pedirNumero("""
                        ¿Cómo quieres listar los productos?:
                        1 Tablero.
                        2 Barniz.
                        3 Artículo.
                        4 Ver todos los productos de la tienda.""", 1, 4);

        switch (opcion) {
            case 1: verTablero(tiendaSeleccionada); break;
            case 2: verBarniz(tiendaSeleccionada); break;
            case 3: verArticulo(tiendaSeleccionada); break;
            case 4: verTodos(tiendaSeleccionada); break;
        }
    };


    // Vemos los tableros
    public static void verTablero(Tiendas tienda) {
        System.out.println("Listado de tableros en la tienda " + tienda.getNombre() + ":");
        for (ProductosEnTiendas p : tienda.getInventario()) {
            if (p.getProducto() instanceof Tablero) {
                System.out.println(p.getProducto() + " | Precio: " + p.getPrecioVenta() + " | Stock: " + p.getStock());
            }
        }
    }


    // Vemos los barnices
    public static void verBarniz(Tiendas tienda) {
        System.out.println("Listado de barnices en la tienda " + tienda.getNombre() + ":");
        for (ProductosEnTiendas p : tienda.getInventario()) {
            if (p.getProducto() instanceof Barniz) {
                System.out.println(p.getProducto() + " | Precio: " + p.getPrecioVenta() + " | Stock: " + p.getStock());
            }
        }
    }


    // Vemos los artículos
    public static void verArticulo(Tiendas tienda) {
        System.out.println("Listado de artículos en la tienda " + tienda.getNombre() + ":");
        for (ProductosEnTiendas p : tienda.getInventario()) {
            if (p.getProducto() instanceof Articulo) {
                System.out.println(p.getProducto() + " | Precio: " + p.getPrecioVenta() + " | Stock: " + p.getStock());
            }
        }
    }


    // Vemos todos los artículos de una tienda
    public static void verTodos(Tiendas tienda) {
        System.out.println("Productos de la tienda " + tienda.getNombre() + ":");
        for (ProductosEnTiendas p : tienda.getInventario()) {
            System.out.println(p.getProducto() + " | Precio: " + p.getPrecioVenta() + " | Stock: " + p.getStock());
        }
    }


    // Vemos el producto por ID en todas las tiendas.
    public static void productoID(SedeCentral sede) {
        String codigo = Verificadores.pedirTexto(
                "Introduce el código del producto a buscar (Ej: T001, B002, A003):");

        Producto productoBuscado = sede.buscarProductoPorCodigo(codigo);

        if (productoBuscado == null) {
            System.out.println("No se ha encontrado un producto con ese código en el catálogo.");
            return;
        }

        // Mostrar la información del producto encontrado
        System.out.println("Información del producto:\n" + productoBuscado);

        // Buscar en qué tiendas está
        boolean encontradoEnTiendas = false;
        System.out.println("\nEl producto se encuentra en las siguientes tiendas:");

        for (Tiendas tienda : sede.getTiendas()) {
            for (ProductosEnTiendas pet : tienda.getInventario()) {
                if (pet.getProducto().getCodigo().equalsIgnoreCase(codigo)) {
                    encontradoEnTiendas = true;
                    System.out.println("- " + tienda.getNombre()
                            + " | Precio: " + pet.getPrecioVenta()
                            + " | Stock: " + pet.getStock());
                }
            }
        }

        if (!encontradoEnTiendas) {
            System.out.println("- El producto existe, pero no está asignado a ninguna tienda.");
        }

        System.out.println("\n");
    }


    // Añadimos un nuevo cliente
    public static void anadirCliente(SedeCentral sede) {
        int tipo = Verificadores.pedirNumero("""
            ¿Qué cliente quieres añadir?
            1 Profesional
            2 Wood Friend
            0 Volver
            """, 0, 2);  // Corrige el máximo a 2


        String nombre = Verificadores.pedirTexto("Nombre del cliente:");
        String documento = Verificadores.pedirDocumento("DNI del cliente:");


        // Verificar si ya existe un cliente con ese DNI
        for (Cliente c : sede.getClientes()) {
            if (c.getDni().equalsIgnoreCase(documento)) {
                System.out.println("Ya existe un cliente con ese DNI.\n");
                return;
            }
        }

        switch (tipo) {
            case 1 -> {
                double descuento = Verificadores.pedirDecimal("Cantidad de descuento sin porcentaje: ", 0, 100);
                Cliente cliente = new ClienteProfesional(documento, nombre, descuento);
                sede.agregarCliente(cliente);
                System.out.println("Cliente profesional añadido correctamente.");
            }
            case 2 -> {
                int id_socioNumero = Verificadores.pedirNumero("Identificador del nuevo socio (3 dígitos): ", 0, 999);
                String id_socio = "WF" + String.format("%03d", id_socioNumero);

                // Verificar si existe el ID
                for (Cliente c : sede.getClientes()) {
                    if (c instanceof ClienteWoodFriend woodFriend && woodFriend.getId_socio().equalsIgnoreCase(id_socio)) {
                        System.out.println("Ya existe un cliente WoodFriend con ese identificador de socio.\n");
                        return;
                    }
                }

                Cliente cliente = new ClienteWoodFriend(documento, nombre, id_socio);
                sede.agregarCliente(cliente);
                System.out.println("Cliente WoodFriend añadido correctamente.");
            }
            case 0 -> {
                System.out.println("Volviendo al menú principal.");
            }
        }
    }


    // Mostrar todos los clientes9
    public static void mostrarTodosClientes(SedeCentral sede) {
        sede.mostrarClientes();
    }


    // Añadir nuevo ticket
    public static void anadirTicket(SedeCentral sede) {
        Tiendas tienda = Verificadores.pedirTiendaPorID(sede);
        int numero = tienda.generarNumeroTicket();
        Date fecha = Verificadores.pedirFecha("Inserte la fecha del ticket (formato dd/MM/yyyy): ");


        // Seleccionar cliente o crear uno
        int crearCliente = Verificadores.pedirNumero("""
            ¿Qué tipo de cliente es?
            1. Anónimo
            2. Cliente registrado
            3. Nuevo cliente
            0. Volver
            """, 0, 3);

        Cliente cliente = null;
        switch (crearCliente) {
            case 0 -> {return;}
            case 1 -> cliente = new ClienteAnonimo();
            case 2 -> {
                String dni = Verificadores.pedirDocumento("Introduce el DNI del cliente registrado: ");
                for (Cliente c : sede.getClientes()) {
                    if (c.getDni().equalsIgnoreCase(dni)) {
                        cliente = c;
                        break;
                    }
                }
                if (cliente == null) {
                    System.out.println("No se ha encontrado un cliente registrado con ese DNI.");
                    return;
                }
            }
            case 3 -> {
                anadirCliente(sede);
                String nuevoDni = Verificadores.pedirDocumento("Introduce de nuevo el DNI del cliente recién creado: ");
                for (Cliente c : sede.getClientes()) {
                    if (c.getDni().equalsIgnoreCase(nuevoDni)) {
                        cliente = c;
                        break;
                    }
                }
                if (cliente == null) {
                    System.out.println("No se ha podido localizar el cliente recién creado.");
                    return;
                }
            }
        }


        // crear ticket
        Ticket ticket = new Ticket(numero, fecha, cliente);


        // añadir líneas de producto al ticket
        int linea = Verificadores.pedirNumero("""
        ¿Quieres añadir productos al ticket?
        1. Sí
        2. No
        """, 1, 2);

        if (linea == 1) {
            while (true) {
                System.out.println("\nProductos disponibles en " + tienda.getNombre() + ":");
                for (ProductosEnTiendas pet : tienda.getInventario()) {
                    System.out.println("- " + pet.getProducto().getCodigo() + " | " + pet.getProducto().getDescripcion() + " | " + pet.getPrecioVenta() + "€");
                }

                String codigoProducto = Verificadores.pedirTexto("Introduce el código del producto que quieres añadir (o 0 para salir):");

                if (codigoProducto.equals("0")) {
                    break;  // Salir del bucle
                }

                ProductosEnTiendas productoEnTienda = null;
                for (ProductosEnTiendas pet : tienda.getInventario()) {
                    if (pet.getProducto().getCodigo().equalsIgnoreCase(codigoProducto)) {
                        productoEnTienda = pet;
                        break;
                    }
                }

                if (productoEnTienda == null) {
                    System.out.println("No se ha encontrado el producto en esta tienda.");
                    continue;
                }

                int cantidad = Verificadores.pedirNumero("Cantidad de unidades:", 1, Integer.MAX_VALUE);

                // Crear línea de detalle usando el objeto existente
                LineaDetalle lineaDetalle = new LineaDetalle(productoEnTienda, cantidad);
                ticket.agregarLinea(lineaDetalle);
                System.out.println("Producto añadido al ticket.");
            }
        }


        // Añadir ticket
        tienda.agregarTicket(ticket);
        System.out.println("Ticket añadido correctamente a la tienda " + tienda.getNombre());


        // Mostrar ticket completo
        System.out.println("\nResumen del ticket:\n");
        System.out.println(ticket);
    }


    // Mostrar tickets filtrados entre dos fechas
    public static void mostrarTicket(SedeCentral sede) {
        Date fechaInicio = Verificadores.pedirFecha("Introduce la fecha de inicio (dd/MM/yyyy): ");
        Date fechaFin = Verificadores.pedirFecha("Introduce la fecha de fin (dd/MM/yyyy): ");

        for (Tiendas tienda : sede.getTiendas()) {
            System.out.println("\nTickets en tienda " + tienda.getNombre() + " | ID: " + tienda.getId() + ":");

            boolean hayTickets = false;
            for (Ticket ticket : tienda.getTickets()) {
                Date fechaTicket = ticket.getFecha();
                if (fechaTicket.compareTo(fechaInicio) >= 0 && fechaTicket.compareTo(fechaFin) <= 0) {
                    hayTickets = true;
                    System.out.println(ticket);
                    System.out.println("------------------------------");
                }
            }

            if (!hayTickets) {
                System.out.println("  No hay tickets en el rango de fechas.");
            }
        }
    }
}
