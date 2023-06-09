package es.upm.tp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lista que contiene objetos de tipo Vuelo. En cuánto a métodos, podremos insertar, buscar
 * seleccionar Vuelo y aplicarles esos métodos. Por último, también tenemos la posibilidad
 * de escribir y leer ficheros aplicándolos a la lista.
 *
 * @author Alberto Chozas Enrique
 * @author Diego García Guerrero
 * @version 1.0
 */
public class ListaVuelos {
    private int capacidad;
    private int ocupacion;
    private Vuelo[] listaVuelos;

    /**
     * Constructor of the class
     *
     * @param capacidad capacidad
     */
    public ListaVuelos(int capacidad){
        this.capacidad = capacidad;
        listaVuelos = new Vuelo[capacidad];
        ocupacion = 0;
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
     * Devuelve el objeto vuelo que está en la posición i del array
     * @param i posición.
     * @return vuelo de la lista, en función de la posición pasada por parámetro.
     */
    public Vuelo getVuelo(int i){
        return listaVuelos[i];
    }

    /**
     * Boolean que comprueba primero si la lista está llena.
     * Si no lo está, insertar un objeto de tipo Vuelo, e incrementa la ocupación y devuelve true.
     * En caso contrario, devuelve false.
     * @param vuelo vuelo que queremos insertar.
     * @return true si se ha podido insertar el objeto de tipo Vuelo; en caso contrario, false.
     */
    public boolean insertarVuelo (Vuelo vuelo){
        if(!estaLlena()) {
            listaVuelos[ocupacion] = vuelo;
            ocupacion++;
            return true;
        }else {
            System.out.println("Lo siento, la lista está llena");
            return false;
        }
    }

    /**
     * Busca un objeto de tipo Vuelo en base a un ID pasado por parámetro.
     * Si lo encuentra, lo devuelve. En caso contrario, devolverá null.
     * @param id String id del Vuelo.
     * @return vuelo con id coincidente.
     */
    public Vuelo buscarVuelo (String id){
        Vuelo vuelo = null;
        for(int i = 0; i < ocupacion; i++){
            if (listaVuelos[i].getID().equals(id)){
                vuelo = listaVuelos[i];
                i = ocupacion;
            }
        }
        return vuelo;
    }

    /**
     * Busca un objeto de tipo ListaVuelos en base a dos Strings: códigoOrigen, códigoDestino y un objeto Fecha.
     * Devolverá un nuevo objeto ListaVuelos conteniendo los vuelos que vayan de un aeropuerto a otro en una determinada fecha.
     * @param codigoOrigen String con el código del Aeropuerto origen.
     * @param codigoDestino String con el código del Aeropuerto destino.
     * @param fecha dia del viaje.
     * @return ListaVuelos con vuelos coincidentes en base a dos códigos de Aeropuerto y una fecha.
     */
    public ListaVuelos buscarVuelos(String codigoOrigen, String codigoDestino, Fecha fecha){
        ListaVuelos lista = new ListaVuelos(capacidad);
        for(int i = 0; i < ocupacion; i++){
            if (listaVuelos[i].getOrigen().getCodigo().equals(codigoOrigen) && listaVuelos[i].getDestino().getCodigo().equals(codigoDestino)
                    && listaVuelos[i].getSalida().coincide(fecha)){
                lista.insertarVuelo(listaVuelos[i]);
            }
        }
        return lista;
    }

    /**
     * Muestra por pantalla los vuelos siguiendo el formato de los ejemplos del enunciado.
     */
    public void listarVuelos(){
        for(int i = 0; i < ocupacion; i++){
            System.out.println(listaVuelos[i].toString());
        }
    }

    /**
     * Permite seleccionar un vuelo existente a partir de su ID, usando el mensaje pasado como
     * argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y
     * usando la cadena cancelar para salir devolviendo null.
     * La función solicita repetidamente hasta que se introduzca un ID correcto.
     * @param teclado objeto Scanner que recoge todo lo introducido por teclado.
     * @param mensaje cadena de texto que será imprimida por pantalla antes de usar el Scanner.
     * @param cancelar cadena de texto que al ponerla, cancelará todo el proceso y ayudará a retornar null.
     * @return Vuelo seleccionado en base a un String id.
     */
    public Vuelo seleccionarVuelo(Scanner teclado, String mensaje, String cancelar){
        Vuelo vuelo;
        String entrada;
        teclado.nextLine();
        do{
            System.out.print(mensaje);
            entrada = teclado.nextLine();
            vuelo = buscarVuelo(entrada);
            if(vuelo == null && !entrada.equals(cancelar)){
                System.out.println("ID de vuelo no encontrado.");
            }
        }while(vuelo == null && !entrada.equals(cancelar));

        return vuelo;
    }

    /**
     * Ha de escribir una lista de vuelos en la ruta y nombre del fichero pasado como parámetro.
     * Si existe el fichero, se sobreescribe. Si no existe, se crea.
     * @param fichero String con el nombre del fichero.
     * @return true si el fichero se ha podido escribir o crear. En caso contrario, false.
     */
    public boolean escribirVuelosCsv(String fichero){
        boolean correcto = true;
        PrintWriter escribir = null;
        try{
            escribir = new PrintWriter(fichero);
            for(int i = 0; i < getOcupacion()-1; i++){
                escribir.print(listaVuelos[i].getID() + ";" + listaVuelos[i].getAvion().getMatricula() + ";" + listaVuelos[i].getOrigen().getCodigo() + ";" + listaVuelos[i].getOrigen().getTerminales() + ";" + listaVuelos[i].getSalida() + ";" + listaVuelos[i].getDestino().getCodigo() + ";" + listaVuelos[i].getDestino().getTerminales() + ";" + ";" + listaVuelos[i].getLlegada() + ";" + listaVuelos[i].getPrecio());
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            correcto = false;
        } finally {
            if(escribir != null) escribir.close();
        }
        return correcto;
    }

    /**
     * Genera una lista de vuelos a partir del fichero CSV, usando los límites especificados como
     * argumentos para la capacidad de la lista.
     * @param fichero String nombre del fichero.
     * @param capacidad int capacidad de la listaVuelos
     * @param aeropuertos Lista de Aeropuertos.
     * @param aviones Lista de Aviones.
     * @return Lista de Vuelos no nula en caso de no haber lanzado ningun Error. En caso contrario, null.
     */
    public static ListaVuelos leerVuelosCsv(String fichero, int capacidad, ListaAeropuertos aeropuertos, ListaAviones aviones){
        ListaVuelos lista = new ListaVuelos(capacidad);
        Scanner entrada = null;
        try{
            entrada = new Scanner(new FileReader(fichero));
            while(entrada.hasNext()){
                String[] linea = entrada.nextLine().split(";");
                String id = linea[0];
                Avion avion = aviones.buscarAvion(linea[1]);
                Aeropuerto origen = aeropuertos.buscarAeropuerto(linea[2]);
                int terminalOrigen = Integer.parseInt(linea[3]);
                Fecha fechaSalida = Fecha.fromString(linea[4]);
                Aeropuerto destino = aeropuertos.buscarAeropuerto(linea[5]);
                int terminalDestino = Integer.parseInt(linea[6]);
                Fecha fechaLlegada = Fecha.fromString(linea[7]);
                double precio = Double.parseDouble(linea[8]);

                lista.insertarVuelo(new Vuelo(id,avion,origen,terminalOrigen,fechaSalida,destino,terminalDestino,fechaLlegada,precio));
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
