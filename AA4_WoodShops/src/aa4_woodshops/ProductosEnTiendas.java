package aa4_woodshops;


/**
 * Clase intermedia típica de relación muchos a muchos
 * que representa un producto concreto en una tienda
 * con su precio de venta y stock específico para esa tienda
 */
public class ProductosEnTiendas {

    private Producto producto;
    private double precioVenta;
    private int stock;


    // Constructores
    public ProductosEnTiendas(){}


    /**
     * Constructor con todos los atributos
     * @param producto Producto almacenado en la tienda.
     * @param precioVenta Precio de venta en la tienda.
     * @param stock Stock en la tienda.
     */
    public ProductosEnTiendas(Producto producto, double precioVenta, int stock){
        this.producto = producto;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }


    // getters y setters
    public Producto getProducto() {return producto;}
    public void setProducto(Producto producto) {this.producto = producto;}

    public double getPrecioVenta() {return precioVenta;}
    public void setPrecioVenta(double precioVenta) {this.precioVenta = precioVenta;}

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}


    // toString
    @Override
    public String toString(){
        return producto.toString()
                + ", Precio: " + precioVenta + "€"
                + ", Stock: " + stock + " unidades";
    }
}
