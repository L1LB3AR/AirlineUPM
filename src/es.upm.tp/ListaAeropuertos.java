package es.upm.tp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lista que contiene Aeropuertos. Tenemos metodos para insertar, buscar, seleccionar la lista.
 * Por último, destacar que tenemos los métodos de lectura y escritura en ficheros.
 *
 * @author Alberto Chozas Enrique
 * @author Diego García Guerrero
 * @version 1.0
 */
public class ListaAeropuertos{
    private int capacidad;
    private int ocupacion;
    private Aeropuerto[] listaAeropuertos;

    /**
     * Constructor de la clase
     *
     * @param capacidad capacidad
     */
    public ListaAeropuertos(int capacidad){
        this.capacidad = capacidad;
        listaAeropuertos = new Aeropuerto[capacidad];
        ocupacion = 0;
    }

    /**
     * Getter del atributo Ocupacion
     *
     * @return ocupacion en la lista de Aeropuertos
     */
    public int getOcupacion(){
        return ocupacion;
    }

    /**
     * Boolean que será true si la condición es correcta o false en caso contrario.
     *
     * @return boolean true si está llena; false en caso contrario.
     */
    public boolean estaLlena(){
        return ocupacion >= capacidad;
    }

    /**
     * Retorna un objeto Aeropuerto en función a un entero pasado por parámetro y lo busca en la lista.
     *
     * @param i posición pasada por parámetro
     * @return Aeropuerto en función de un parámetro de tipo entero.
     */
    public Aeropuerto getAeropuerto(int i){
        return listaAeropuertos[i];
    }

    /**
     * Boolean que comprueba primero si la lista de Aeropuertos está llena.
     * Si no lo está, inserta un objeto de tipo aeropuerto e incrementa la posición, devuelve true.
     * En caso contrario, false.
     * @param aeropuerto objeto de tipo Aeropuerto.
     * @return true si ha podido añadir un objeto Aeropuerto nuevo a la lista; en caso contrario false.
     */
    public boolean insertarAeropuerto(Aeropuerto aeropuerto){
        if(!estaLlena()) {
            listaAeropuertos[ocupacion] = aeropuerto;
            ocupacion++;
            return true;
        }else
            return false;
    }

    /**
     * Busca en la lista de Aeropuertos, un Aeropuerto concreto en base a un código pasado por
     * parámetro. El resultado se guarda en un objeto auxiliar y se devuelve.
     * @param codigo código del Aeropuerto buscado.
     * @return objeto Aeropuerto.
     */
    public Aeropuerto buscarAeropuerto(String codigo){
        Aeropuerto aeropuerto = null;
        for(int i = 0; i < ocupacion; i++){
            if (listaAeropuertos[i].getCodigo().equals(codigo)){
                aeropuerto = listaAeropuertos[i];
                i = ocupacion;
            }
        }
        return aeropuerto;
    }

    /**
     * Permite seleccionar un aeropuerto existente a partir de su código, usando el mensae pasado como
     * argumento y siguiendo el orden y los textos mostrados en el enunciado, la función, solicita
     * repetidamente el código hasta que se introduzca uno correcto.
     * @param teclado objeto Scanner que recogerá todo aquello introducido por teclado.
     * @param mensaje String que imprimiremos por pantalla.
     * @return objeto Aeropuerto buscado y seleccionado.
     */
    public Aeropuerto seleccionarAeropuerto(Scanner teclado, String mensaje){
        String codigo;
        do{
            System.out.print(mensaje);
            codigo = teclado.next();
            if(this.buscarAeropuerto(codigo) == null){
                System.out.println("No se encuentra ningún aeropuerto con el codigo introducido");
            }
        }while(this.buscarAeropuerto(codigo) == null);
        return this.buscarAeropuerto(codigo);
    }

    /**
     * Boolean que genera un fichero CSV con la lista de Aeropuertos, sobreescribiéndolos.
     *
     * @param nombre String del nombre del fichero en cuestión .
     * @return true si se ha podido sobreescribir; false en caso contrario.
     */
    public boolean escribirAeropuertosCsv(String nombre){
        boolean correcto = true;
        PrintWriter escribir = null;
        try{
            escribir = new PrintWriter(nombre);
            for(int i = 0; i < getOcupacion()-1; i++){
                escribir.print(listaAeropuertos[i].getNombre() + ";" + listaAeropuertos[i].getCodigo() + ";" + listaAeropuertos[i].getLatitud() + ";" + listaAeropuertos[i].getLongitud() + ";" + listaAeropuertos[i].getTerminales());
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            correcto = false;
        } finally{
            if(escribir != null) escribir.close();
        }
        return correcto;
    }

    /**
     * Genera una lista de aeropuertos a partir del fichero CSV, usando el argumento como
     * capacidad maxima de la lista
     * @param fichero String del nombre del fichero en cuestion
     * @param capacidad capacidad
     * @return lista de aeropuertos leida
     */
    public static ListaAeropuertos leerAeropuertosCsv(String fichero, int capacidad){
        ListaAeropuertos lista = new ListaAeropuertos(capacidad);
        Scanner entrada = null;
        try{
            entrada = new Scanner(new FileReader(fichero));
            while(entrada.hasNext()){
                String[] linea = entrada.nextLine().split(";");
                String nombre = linea[0];
                String codigo = linea[1];
                double latitud = Double.parseDouble(linea[2]);
                double longitud = Double.parseDouble(linea[3]);
                int terminal = Integer.parseInt(linea[4]);

                lista.insertarAeropuerto(new Aeropuerto(nombre, codigo, latitud, longitud, terminal));
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            if(entrada != null){
                try{
                    entrada.close();
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return lista;
    }
}
