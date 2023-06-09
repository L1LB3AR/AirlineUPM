package es.upm.tp;

/**
 * Aeropuerto es una clase que encapsula para variables variables. Dos de tipo String
 * para el nombre y el código de éste. Dos de tipo Double para los ejes de cooordenadas y una
 * entera que es el número de terminales.
 *
 * @author Alberto Chozas Enrique
 * @author Diego García Guerrero
 * @version 1.0
 */
public class Aeropuerto {
    private String nombre, codigo;
    private double latitud, longitud;
    private int terminales;

    /**
     * Constructor de la clase Aeropuerto usado para definir
     * nombre, codigo, la situacion geografica y los terminales
     * de un Aeropuerto.
     *
     * @param nombre el nombre del aeropuerto
     * @param codigo el codigo del aeropuerto
     * @param latitud la posicion del aeropuerto
     * @param longitud la posicion del aeropuerto
     * @param terminales los terminales del aeropuerto
     */
    public Aeropuerto(String nombre, String codigo, double latitud, double longitud, int terminales){
        setNombre(nombre);
        setCodigo(codigo);
        setLatitud(latitud);
        setLongitud(longitud);
        setTerminales(terminales);
    }

    /**
     * Getter del atributo Nombre.
     *
     * @return Nombre del Aeropuerto
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Setter del atributo Nombre.
     *
     * @param nombre el nombre del aeropuerto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo Codigo
     *
     * @return Codigo del Aeropuerto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Setter del atributo Codigo
     *
     * @param codigo el codigo del aeropuerto
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Getter del atributo Latitud
     *
     * @return Latitud del Aeropuerto
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Setter del atributo Latitud
     *
     * @param latitud la latitud a la que se encuentra el aeropuerto
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Getter del atributo Longitud
     *
     * @return Longitud del Aeropuerto
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Setter del atributo Longitud
     *
     * @param longitud la longitud a la que se encuentra el aeropuerto
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Getter del atributo Terminales
     *
     * @return Terminal del Aeropuerto
     */
    public int getTerminales() {
        return terminales;
    }

    /**
     * Setter del atributo Terminales
     *
     * @param terminales terminales que se encuentran en el aeropuerto
     */
    public void setTerminales(int terminales) {
        this.terminales = terminales;
    }

    /**
     * Calcula la distancia entre el aeropuerto que recibe el mensaje
     * y el aeropuerto "destino" siguiendo la fórmula del enunciado
     *
     * @param destino Aeropuerto que se quiere comprobar a que distancia se encuentra del origen.
     * @return distancia a la que se encuentra el destino respecto el de origen.
     */
    public double distancia(Aeropuerto destino){
        double latitud1 = Math.toRadians(this.latitud);
        double latitud2 = Math.toRadians(destino.latitud);
        double longitud1 = Math.toRadians(this.longitud);
        double longitud2 = Math.toRadians(destino.longitud);
        double radioTierra = 6378;
        return Math.acos(Math.sin(latitud1)*Math.sin(latitud2)+Math.cos(latitud1)*Math.cos(latitud2)*Math.cos(longitud1-longitud2))*radioTierra;
    }


    /**
     * Crea y retorna un String con los datos de un aeropuerto con el siguiente formato:
     * Adolfo Suarez Madrid - Barajas(MAD), en (40.4927751 -3.5778), con 4 terminales
     *
     * @return Cadena de texto con un aeropuerto.
     */
    public String toString(){
        return getNombre() + " - " + getCodigo() + ", en (" + getLatitud() + getLongitud() + "), con " + getTerminales() + " terminales";
    }

    /**
     * Crea y retorna un String con los datos de un aeropuerto
     * con el siguiente formato: Adolfo Suarez Madrid - Barajas(MAD)
     *
     * @return Cadena de texto con el nombre y codigo de un aeropuerto.
     */
    public String toStringSimple(){
        return getNombre() + " - " + getCodigo();
    }
}
