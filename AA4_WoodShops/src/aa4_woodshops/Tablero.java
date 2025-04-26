package aa4_woodshops;

/**
 * Clase que representa un tablero de madera.
 * Hereda de la clase abstracta Producto.
 */
public class Tablero extends Producto{

    private float altura;
    private float anchura;
    private TipoMadera tipo;


    // Constructor
    public Tablero(){super();}


    /**
     *  Constructor de Tablero con todos los atributos
     * @param codigo Código del producto.
     * @param descripcion Descripción del producto.
     * @param proveedor Proveedor del producto.
     * @param altura Altura del tablero.
     * @param anchura Anchura del tablero.
     * @param tipo Tipo de madera del tablero.
     */
    public Tablero(String codigo, String descripcion, Proveedor proveedor,
                   float altura, float anchura, TipoMadera tipo) {
        super(codigo, descripcion, proveedor);
        this.altura = altura;
        this.anchura = anchura;
        this.tipo = tipo;
    }


    // Getters y setters
    public float getAltura() { return altura; }
    public void setAltura(float altura) { this.altura = altura; }


    public float getAnchura() { return anchura; }
    public void setAnchura(float anchura) { this.anchura = anchura; }


    public TipoMadera getTipo() { return tipo; }
    public void setTipo(TipoMadera tipo) { this.tipo = tipo; }


    // toString
    @Override
    public String toString() {
        return super.toString()
                + ", Tipo de madera: " + tipo
                + ", Altura: " + altura + "m"
                + ", Anchura: " + anchura + "m";
    }
}
