package aa4_woodshops;

/**
 * Clase para hacer las lineas de producto y unidades de los tickets
 */
public class LineaDetalle {

    private ProductosEnTiendas producto;
    private int cantidad;


    // Constructores
    public LineaDetalle(){}


    /**
     * Contructor con todos los parametris
     * @param producto almacena el nombre del producto
     * @param cantidad Almacena la cantidad de producto comprado
     */
    public LineaDetalle(ProductosEnTiendas producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }


    // getters y setters
    public int getCantidad() {return cantidad;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public ProductosEnTiendas getProducto() {return producto;}
    public void setProducto(ProductosEnTiendas producto) {this.producto = producto;}


    // toString
    @Override
    public String toString() {
        return "- Código: " + producto.getProducto().getCodigo() +
                " | Nombre: " + producto.getProducto().getDescripcion() +
                " | Precio: " + producto.getPrecioVenta() + "€" +
                " | Cantidad: " + cantidad;
    }
}
