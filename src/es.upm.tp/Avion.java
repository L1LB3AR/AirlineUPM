package es.upm.tp;
/**
 * Avion es una clase que encapsula distintos tipos de variables. De tipo String, para la marca,
 * el modelo y la matricula del avión. Por otro lado, variables de tipo int, que almacenarán el
 * número de filas y columnas que hay. Por último, una double con el alcance máximo del avion.
 *
 * @author Diego García Guerrero
 * @author Alberto Chozas Enrique
 * @version 1.0
 */
public class Avion {
    private String marca, modelo, matricula;
    private int filas, columnas;
    private double alcance;
    /**
     * Constructor of the class
     *
     * @param marca la marca del avion
     * @param modelo el modelo del avion
     * @param matricula la matricula del avion
     * @param columnas numero de columnas en el avion
     * @param filas numero de filas del avion
     * @param alcance el alcance en km del avion
     */
    public Avion(String marca, String modelo, String matricula, int filas, int columnas, double alcance){
        setMarca(marca);
        setModelo(modelo);
        setMatricula(matricula);
        setFilas(filas);
        setColumnas(columnas);
        setAlcance(alcance);
    }

    /**
     * Getter del atributo Marca
     *
     * @return Marca del Avion.
     */
    public String getMarca(){return marca;}

    /**
     * Setter del atributo Marca
     *
     * @param marca la marca del Avion.
     */
    public void setMarca(String marca){this.marca = marca;}

    /**
     * Getter del atributo Modelo
     *
     * @return Modelo del Avión.
     */
    public String getModelo(){return modelo;}

    /**
     * Setter del atributo Modelo
     *
     * @param modelo el modelo del Avión.
     */
    public void setModelo(String modelo){this.modelo = modelo;}

    /**
     * Getter del atributo Matricula
     *
     * @return Matricula del Avión.
     */
    public String getMatricula(){return matricula;}

    /**
     * Setter del atributo Matricula
     *
     * @param matricula la matricula del Avión.
     */
    public void setMatricula(String matricula){this.matricula = matricula;}

    /**
     * Getter del atributo Columnas
     *
     * @return Número de columnas del Avión.
     */
    public int getColumnas(){return columnas;}

    /**
     * Setter del atributo Columnas
     *
     * @param columnas número de columnas del Avión.
     */
    public void setColumnas(int columnas){this.columnas = columnas;}

    /**
     * Getter del atributo Filas
     *
     * @return Número de filas del Avión.
     */
    public int getFilas(){return filas;}

    /**
     * Setter del atributo Filas
     *
     * @param filas número de filas del Avión.
     */
    public void setFilas(int filas){this.filas = filas;}

    /**
     * Getter del atributo Alcance
     *
     * @return Alcance máximo del Avión.
     */
    public double getAlcance(){return alcance;}

    /**
     * Setter del atributo Alcance
     *
     * @param alcance alcance máximo del Avión.
     */
    public void setAlcance(double alcance){this.alcance = alcance;}

    /**
     * Crea un String con los datos de un avión con el siguiente formato:
     * Boeing 737(EC-LKE): 180 asientos, hasta 5700.0 km
     *
     * @return Cadena de texto con los datos de un Avión con el formato propuesto.
     */
    public String toString(){
        return getMarca() + " " + getModelo() + "(" + getMatricula() + "): " + getColumnas()*getFilas() + " asientos, hasta " + getAlcance() + " km";
    }

    /**
     * Crea un String con los datos de un avión con el siguiente formato:
     * Boeing 737(EC-LKE)
     *
     * @return Cadena de texto con la marca el modelo y la matricula del avión.
     */
    public String toStringSimple(){
        return getMarca() + " " + getModelo() + "(" + getMatricula() + ")";
    }
}
