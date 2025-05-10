package aa5_woodshops;


import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase con verificadores con diferentes utilidades.
 */
public class Verificadores {

    private static final Scanner teclado = new Scanner(System.in);

    /**
     * Función para pedir un texto evitando introducir nada, así evitamos entradas vacías.
     * @param mensaje Mensaje que se debe imprimir al solicitar el texto. Esto permite reúso.
     * @return Devuelve el mensaje introducido por el usuario sin errores.
     */
    public static String pedirTexto(String mensaje){
        String entrada;
        while (true) {
            System.out.println(mensaje);
            entrada = teclado.nextLine().trim();

            if (!entrada.isEmpty()){
                return entrada;
            }
        }
    }


    /**
     * Función para verificar que se introduce el dato solicitado, en este caso un int.
     * @param mensaje Mensaje que se imprime al llamar la función, puede ser un enunciado o un menú.
     * @param min Número mínimo esperado.
     * @param max Número máximo esperado.
     * @return Devuelve el número introducido dentro de los márgenes esperados.
     */
    public static int pedirNumero(String mensaje, int min, int max) {
        while (true) {
            String entrada = pedirTexto(mensaje);

            try {
                int numero = Integer.parseInt(entrada);
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println("Número fuera de rango. Introduce un número entre " + min + " y " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Solo se permiten números enteros.");
            }
        }
    }


    /**
     * Función para verificar que se introduce un número deseado, en este caso, la posibilidad
     * que sea un número con decimales.
     * @param mensaje Mensaje que se imprime al solicitar el dato.
     * @param min Número mínimo esperado.
     * @param max Número máximo esperado.
     * @return Número devuelto dentro de los rangos deseados.
     */
    public static double pedirDecimal(String mensaje, double min, double max) {
        while (true) {
            String entrada = pedirTexto(mensaje);

            try {
                double numero = Double.parseDouble(entrada);
                boolean dentroDelRango = numero >= min && (max == -1 || numero <= max);

                if (dentroDelRango) {
                    return numero;
                } else {
                    String msgRango = (max == -1)
                            ? "El número debe ser mayor o igual a " + min
                            : "Introduce un valor entre " + min + " y " + max;
                    System.out.println("Número fuera de rango. " + msgRango);
                }
            } catch (NumberFormatException e) {
                System.out.println("Solo se permiten números (usa punto como separador decimal).");
            }
        }
    }


    /**
     * Verifica que el NIF/NIE/CIF tenga exactamente 9 caracteres alfanuméricos.
     * No realizamos verificación real, solo de carácteres.
     * @param mensaje Mensaje que se mostrará al usuario.
     * @return NIF válido como texto.
     */
    public static String pedirDocumento(String mensaje) {
        String entrada;
        while (true) {
            System.out.println(mensaje);
            entrada = teclado.nextLine().trim();

            if (entrada.matches("[A-Za-z0-9]{9}")) {
                return entrada.toUpperCase();
            }

            System.out.println("Formato inválido. Debe tener exactamente 9 caracteres alfanuméricos.");
        }
    }


    /**
     * Método para verificar que el valor que se introduce coincide con el de los disponibles.
     * Verifica que la coincidencia sea exacta para no generar errores.
     * Se repite hasta que introduzca un dato válido.
     * @param mensaje Mensaje que se imprime al acceder al verificador
     * @param enumClase Diferentes enum disponibles
     * @param <T> Tipo genérico que representa un enumerado (debe extender de Enum).
     * @return Devuelve el dato verificado
     */
    public static <T extends Enum<T>> T pedirEnum(String mensaje, Class<T> enumClase) {
        while (true) {
            String entrada = pedirTexto(mensaje);
            try {
                return Enum.valueOf(enumClase, entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Valor inválido. Escribe uno de los valores mostrados exactamente como aparece.");
            }
        }
    }


    /**
     * Verificador para que se pida la fecha correctamente
     * @param mensaje mensaje recibido al llamar la función
     * @return devuelve una fecha válida
     */
    public static Date pedirFecha(String mensaje) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false); // No permite fechas inválidas como 32/01/2024

        Date fecha = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.println(mensaje);
            String entrada = teclado.nextLine();
            try {
                fecha = formato.parse(entrada);
                fechaValida = true;
            } catch (ParseException e) {
                System.out.println("Formato incorrecto. Usa el formato dd/MM/yyyy. Ejemplo: 25/04/2025");
            }
        }

        return fecha;
    }


    /**
     * Verificador para pedir tienda
     * @param sede Parametro que representa toda la empresa
     * @return devuelve los datos de una tienda existente en la empresa
     */
    public static Tiendas pedirTiendaPorID(SedeCentral sede) {
        while (true) {
            sede.mostrarTiendas();
            int idNumero = pedirNumero("Introduce el ID de la tienda: ", 0, 9999);
            String idTienda = String.format("%04d", idNumero);

            for (Tiendas t : sede.getTiendas()) {
                if (t.getId().equalsIgnoreCase(idTienda)) {
                    return t;
                }
            }

            System.out.println("No se ha encontrado ninguna tienda con ese ID. Inténtalo de nuevo.");
        }
    }


    /**
     * Función para solicitar dos fechas y verificar que tengan orden cronológico. Así mismo, se
     * llama a la función pedirFecha(String mensaje) para verificar que se introducen las fechas
     * de forma correcta.
     * @return un array con dos fechas correlativas.
     */
    public static Date[] pedirDosFechas() {
        Date fechaInicio = pedirFecha("Introduce la fecha de inicio (dd/MM/yyyy): ");
        Date fechaFin;
        do {
            fechaFin = pedirFecha("Introduce la fecha de fin (dd/MM/yyyy): ");
            if (fechaFin.before(fechaInicio)) {
                System.out.println("La fecha de fin no puede ser anterior a la de inicio. Inténtalo de nuevo.");
            }
        } while (fechaFin.before(fechaInicio));

        return new Date[] { fechaInicio, fechaFin };
    }
}




