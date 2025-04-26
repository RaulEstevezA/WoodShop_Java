package aa4_woodshops;


/**
 * Clase que representa un Barniz.
 * Hereda de la clase abstracta Producto.
 */
public class Barniz extends Producto{

    private int mililitros;
    private ColorBarniz color;


    // Constructores
    public Barniz(){super();}


    /**
     * Constructor de Barniz con todos los atributos.
     * @param codigo Código del producto.
     * @param descripcion Descripción del producto.
     * @param proveedor Proveedor del producto.
     * @param mililitros Cantidad de producto en milímetros.
     * @param color Color del producto.
     */
    public Barniz(String codigo, String descripcion, Proveedor proveedor,
                  int mililitros, ColorBarniz color){
        super(codigo, descripcion, proveedor);
        this.mililitros = mililitros;
        this.color = color;
    }


    // Getters y setters
    public int getMililitros() { return mililitros; }
    public void setMililitros(int mililitros) { this.mililitros = mililitros; }


    public ColorBarniz getColor() { return color; }
    public void setColor(ColorBarniz color) { this.color = color; }


    // toString
    @Override
    public String toString() {
        return super.toString()
                + ", Color: " + color
                + ", Mililitros: " + mililitros + "ml";
    }
}
