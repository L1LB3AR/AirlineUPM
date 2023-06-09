package es.upm.tp;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lista que contiene objetos de tipo Billete. En cuánto a métodos, podremos insertar,
 * buscar, seleccionar Billete, eliminar y aplicarles esos métodos. Por último, también tenemos la posibilidad
 * de escribir y leer ficheros aplicándolos a la lista.
 *
 * @author Alberto Chozas Enrique
 * @author Diego García Guerrero
 * @version 1.0
 */
public class ListaBilletes {
    private int capacidad;
    private int ocupacion;
    private Billete[] listaBilletes;

    /**
     * Constructor of the class
     *
     * @param capacidad capacidad
     */
    public ListaBilletes(int capacidad){
        this.capacidad = capacidad;
        ocupacion = 0;
        listaBilletes = new Billete[capacidad];
    }

    /**
     * Retorna la ocupación.
     * @return ocupación.
     */
    public int getOcupacion(){
        return ocupacion;

    }

    /**
     * Boolean que si cumple la condición propuesta, devolverá true. En caso contrario, false.
     *
     * @return true si la lista está llena; false en caso contrario.
     */
    public boolean estaLlena(){
        return ocupacion >= capacidad;
    }

    /**
     * Devuelve el objeto de tipo Billete, en función de la posición pasada por parámetro.
     * @param i  posición.
     * @return billete de la lista, en función de la posición pasada por parámetro.
     */
    public Billete getBillete(int i){
        return listaBilletes[i];
    }

    /**
     * Boolean que comprueba primero si la lista está llena.
     * Si no lo está, inserta un objeto de tipo Billete, e incrementa la ocupación y devuelve true.
     * En caso contrario, devuelve false.
     * @param billete billete que queremos insertar.
     * @return true si se ha podido insertar el objeto de tipo Billete; en caso contrario, false.
     */
    public boolean insertarBillete (Billete billete){
        if(!estaLlena()) {
            listaBilletes[ocupacion] = billete;
            ocupacion++;
            return true;
        }else
            return false;
    }

    /**
     * Busca un objeto de tipo Billete en función de un String localizador pasado por parámetro.
     * @param localizador  localizador del billete.
     * @return objeto de tipo Billete buscado.
     */
    public Billete buscarBillete (String localizador){
        Billete billete = null;
        for(int i = 0; i <= capacidad; i++){
            if (listaBilletes[i].getLocalizador().equals(localizador)){
                billete = listaBilletes[i];
                i = capacidad;
            }
        }
        return billete;
    }

    /**
     * Busca un objeto de tipo Billete en función de un String que contiene el ID del vuelo y
     * la fila y la columna.
     * @param idVuelo String id .
     * @param fila int que contiene la fila.
     * @param columna int que contiene la columna.
     * @return objeto de tipo Billete buscado.
     */
    public Billete buscarBillete (String idVuelo, int fila, int columna){
        Billete billete = null;
        for(int i = 0; i <= capacidad; i++){
            if (listaBilletes[i].getFila() == fila && listaBilletes[i].getColumna() == columna
                    && listaBilletes[i].getLocalizador().startsWith(idVuelo)){
                billete = listaBilletes[i];
                i = capacidad;
            }
        }
        return billete;
    }

    /**
     * Boolean que busca un Billete en función a un String que contiene el localizador de éste.
     * Si lo encuentra, lo iguala a null y eliminado a true.
     * En caso contrario, devuelve false.
     * @param localizador String que contiene una cadena de caracteres siendo ésta localizador del billete.
     * @return true si se ha eliminado el billete; false en caso contrario.
     */
    public boolean eliminarBillete (String localizador){
        boolean eliminado = false;
        for(int i = 0; i <= capacidad; i++){
            if(listaBilletes[i].getLocalizador().equals(localizador)){
                listaBilletes[i] = null;
                eliminado = true;
            }
        }
        return eliminado;
    }

    /**
     * Imprime por pantalla una lista de billetes.
     */
    public void listarBilletes(){
        for(int i = 1; i <= ocupacion; i++){
            System.out.println(i + ". " + listaBilletes[i-1].toString());
        }
    }

    /**
     * Permite seleccionar un billete existente a partir de su localizador, usando el mensaje pasado
     * como argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado
     * La función solicita repetidamente hasta que se introduzca un localizador correcto.
     * @param teclado objeto de tipo Scanner
     * @param mensaje String mensae que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @return Billete seleccionado a partir de parámetros introducidos por teclado.
     */
    public Billete seleccionarBillete(Scanner teclado, String mensaje){
        String localizador;
        do{
            System.out.println(mensaje);
            localizador = teclado.next();
            teclado.next();
            if(this.buscarBillete(localizador) == null){
                System.out.println("No se encuentra ningún billete con el localizador introducido");
            }
        }while(this.buscarBillete(localizador) == null);
        return this.buscarBillete(localizador);
    }

    /**
     * Añade los billetes al final de un fichero CSV, sin sobreescribirlo.
     * @param fichero String que contiene el nombre del fichero.
     * @return true si se ha podido añadir un objeto Billete al fichero; false en caso contrario.
     */
    public boolean aniadirBilletesCsv(String fichero){
        boolean correcto = true;
        Scanner entrada = null;
        PrintWriter salida = null;
        try{
            salida = new PrintWriter(fichero);
            entrada = new Scanner(new FileReader(fichero));
            while(entrada.hasNextLine()){
                entrada.hasNextLine();
            }
            for(int i = 0; i < getOcupacion()-1; i++){
                salida.printf("%s;%s;%s;%s;%d;%2f\n",listaBilletes[i].getLocalizador(), listaBilletes[i].getVuelo(), listaBilletes[i].getPasajero().toString(), listaBilletes[i].getFila(), listaBilletes[i].getColumna(), listaBilletes[i].getPrecio());
            }
        } catch(Exception exception){
            System.out.println(exception.getMessage());
            correcto = false;
        } finally {
            if(salida != null){
                salida.close();
            }
            if(entrada != null){
                entrada.close();
            }
        }
        return correcto;
    }

    // Métodos estáticos

    /**
     * Lee los billetes del fichero CSV y los añade a la lista de sus respectivos Vuelos y Pasajeros.
     * @param ficheroBilletes String con el nombre del fichero de Billetes.
     * @param vuelos objeto ListaVuelos con los vuelos.
     * @param pasajeros objeto ListaPasajeros con los pasajeros.
     */
    // Lee los billetes del fichero CSV y los añade a la lista de sus respectivos Vuelos y Pasajeros
    public static void leerBilletesCsv(String ficheroBilletes, ListaVuelos vuelos, ListaPasajeros pasajeros){
        Scanner entrada = null;
        try{
            entrada = new Scanner(new FileReader(ficheroBilletes));
            while(entrada.hasNext()){
                String[] linea = entrada.nextLine().split(";");
                String localizador = linea[0];
                Vuelo vuelo = vuelos.buscarVuelo(linea[1]);
                Pasajero pasajero = pasajeros.buscarPasajeroDNI(linea[2]);
                Billete.TIPO tipo = Billete.TIPO.valueOf(linea[3]);
                int filas = Integer.parseInt(linea[4]);
                int columnas = Integer.parseInt(linea[5]);
                double precio = Double.parseDouble(linea[6]);

                Billete billete = new Billete(localizador,vuelo,pasajero,tipo,filas,columnas,precio);
                pasajero.aniadirBillete(billete);
                vuelo.ocuparAsiento(billete);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            if(entrada != null){
                try{
                    entrada.close();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
