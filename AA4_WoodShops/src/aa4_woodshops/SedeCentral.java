package aa4_woodshops;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la sede central de la empresa que agrupa las tiendas.
 * Contiene el nombre de la empresa, el listado de tiendas, el
 * catálogo de productos y los clientes.
 */
public class SedeCentral {
    private final String nombre = "Wood Shops";
    private List<Tiendas> tiendas;
    private List<Producto> catalogoProductos;
    private List<Cliente> clientes;


    // Constructor
    public SedeCentral() {
        this.tiendas = new ArrayList<>();
        this.catalogoProductos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }


    // Getters y setters
    public String getNombre() { return nombre; }

    public List<Tiendas> getTiendas() { return tiendas; }
    public void setTiendas(List<Tiendas> tiendas) { this.tiendas = tiendas; }

    public List<Producto> getCatalogoProductos() { return catalogoProductos; }
    public void setCatalogoProductos(List<Producto> catalogoProductos) { this.catalogoProductos = catalogoProductos; }

    public List<Cliente> getClientes() {return clientes;}
    public void setClientes(List<Cliente> clientes) {this.clientes = clientes;}


    // Agregar tienda
    public void agregarTienda(Tiendas tienda) {tiendas.add(tienda);}


    // Agregar producto al catálogo
    public void agregarProductoCatalogo(Producto producto) {catalogoProductos.add(producto);}


    //Agregar cliente
    public void agregarCliente(Cliente cliente) {clientes.add(cliente);}


    // Buscar producto por código en el catálogo
    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto p : catalogoProductos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }


    // Mostrar tiendas
    public void mostrarTiendas() {
        System.out.println("\nTiendas disponibles:");
        for (Tiendas tienda : tiendas) {
            System.out.println(" - " + tienda.getNombre() + " (ID: " + tienda.getId() + ")");
        }
    }


    // Mostrar productos
    public void mostrarCatalogoProductos() {
        System.out.println("\nCatálogo de productos:");
        for (Producto p : catalogoProductos) {
            System.out.println("- " + p.getCodigo() + " | " + p.getDescripcion());
        }
    }


    // Mostrar clientes
    public void mostrarClientes() {
        System.out.println("\nClientes:");
        for (Cliente c : clientes) {
            if (!(c instanceof ClienteAnonimo)) {
                System.out.println("- " + c);
            }
        }
    }
}
