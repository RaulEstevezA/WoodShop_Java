package aa5_woodshops;

/**
 * Clase que representa un artículo, como una estantería, mesa, etc.
 * Hereda de la clase abstracta Producto.
 */
public class Articulo extends Producto {

    private TipoArticulo tipo;


    // Constructores
    public Articulo() {
        super();
    }


    /**
     * Constructor con todos los atributos.
     * @param codigo Código del producto.
     * @param descripcion Descripción del producto.
     * @param proveedor Proveedor que suministra el producto.
     * @param tipo Tipo de artículo (silla, mesa, etc).
     */
    public Articulo(String codigo, String descripcion, Proveedor proveedor, TipoArticulo tipo) {
        super(codigo, descripcion, proveedor);
        this.tipo = tipo;
    }


    // Getters y setters
    public TipoArticulo getTipo() { return tipo; }
    public void setTipo(TipoArticulo tipo) { this.tipo = tipo; }


    // toString
    @Override
    public String toString() {
        return super.toString() + ", Tipo de artículo: " + tipo;
    }
}