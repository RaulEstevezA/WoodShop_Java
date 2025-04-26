package aa4_woodshops;

/**
 * Clase abstracta que representa un producto genérico vendido en la empresa.
 * Contiene atributos comunes a todos los productos como el código, la descripción y el proveedor.
 */
public abstract class Producto {

    protected String codigo;
    protected String descripcion;
    protected Proveedor proveedor;


    // Constructor
    public Producto() {}


    /**
     * Constructor con todos los parámetros.
     * @param codigo Código del producto.
     * @param descripcion Descripción del producto.
     * @param proveedor Proveedor que suministra el producto.
     */
    public Producto(String codigo, String descripcion, Proveedor proveedor) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    // Getters y setters
    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}


    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}


    public Proveedor getProveedor() {return proveedor;}
    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}


    @Override
    public String toString() {
        return "Código: " + codigo +
                ", Descripción: " + descripcion +
                ", Proveedor: " + proveedor;
    }
}
