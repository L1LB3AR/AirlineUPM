package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Aeropuerto {

    /**
     * Constructor of the class
     *
     * @param nombre
     * @param codigo
     * @param latitud
     * @param longitud
     * @param terminales
     */
    public Aeropuerto(String nombre, String codigo, double latitud, double longitud, int terminales);
    public String getNombre();
    public String getCodigo();
    public double getLatitud();
    public double getLongitud();
    public int getTerminales();
    // Calcula la distancia entre el aeropuerto que recibe el mensaje y el aeropuerto "destino" siguiendo la fórmula del enunciado
    public double distancia(Aeropuerto destino);
    // Crea un String con los datos de un aeropuerto con el siguiente formato:
    // Adolfo Suarez Madrid - Barajas(MAD), en (40.4927751 -3.5778), con 4 terminales
    public String toString();
    // Crea un String con los datos de un aeropuerto con el siguiente formato:
    // Adolfo Suarez Madrid - Barajas(MAD)
    public String toStringSimple();
}
