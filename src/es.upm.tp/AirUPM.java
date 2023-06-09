package es.upm.tp;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * AirUPM en esta clase es donde ejecutaremos todas las clases anteriores y donde construiremos
 * la applicación. Contando con diversas variables, constructor y métodos. Es la clase Main.
 *
 * @author Alberto Chozas Enrique
 * @author Diego García Guerrero
 * @version 1.0
 */
public class AirUPM {
    private int maxAeropuertos, maxAviones, maxVuelos, maxPasajeros, maxBilletesPasajero;
    private ListaAeropuertos listaAeropuertos;
    private ListaAviones listaAviones;
    private ListaPasajeros listaPasajeros;
    private ListaVuelos listaVuelos;
    private ListaBilletes listaBilletes;

    /**
     * Constructor of the class
     * 
     * @param maxAeropuertos
     * @param maxAviones
     * @param maxVuelos
     * @param maxPasajeros
     * @param maxBilletesPasajero
     */
    public AirUPM(int maxAeropuertos, int maxAviones, int maxVuelos, int maxPasajeros, int maxBilletesPasajero){
        this.maxAeropuertos = maxAeropuertos;
        this.maxAviones = maxAviones;
        this.maxVuelos = maxVuelos;
        this.maxPasajeros = maxPasajeros;
        this.maxBilletesPasajero = maxBilletesPasajero;
    }

    /**
     * Lee los datos de los ficheros especificados y las agrega a AirUPM
     * @param ficheroAeropuertos nombre de ficheroAeropuertos
     * @param ficheroAviones nombre de ficheroAviones
     * @param ficheroVuelos nombre de ficheroVuelos
     * @param ficheroPasajeros nombre de ficheroPasajeros
     * @param ficheroBilletes nombre de ficheroBilletes
     */
    public void cargarDatos(String ficheroAeropuertos, String ficheroAviones, String ficheroVuelos, String ficheroPasajeros, String ficheroBilletes){
        this.listaAeropuertos = ListaAeropuertos.leerAeropuertosCsv(ficheroAeropuertos,maxAeropuertos);
        this.listaAviones = ListaAviones.leerAvionesCsv(ficheroAviones,maxAviones);
        this.listaPasajeros = ListaPasajeros.leerPasajerosCsv(ficheroPasajeros,maxPasajeros,maxBilletesPasajero);
        this.listaVuelos = ListaVuelos.leerVuelosCsv(ficheroVuelos,maxVuelos,listaAeropuertos,listaAviones);
        this.listaBilletes = new ListaBilletes(maxPasajeros * maxBilletesPasajero);

        if(this.listaAeropuertos != null && this.listaAviones != null && this.listaPasajeros != null){

            ListaBilletes.leerBilletesCsv(ficheroBilletes, listaVuelos, listaPasajeros);

            Pasajero pasajero;
            for(int i = 0; i < listaPasajeros.getOcupacion(); i++){
                pasajero = listaPasajeros.getPasajero(i);
                for(int j = 0; j < pasajero.numBilletesComprado(); j++){
                    if(pasajero.getBillete(j)!= null){
                        this.listaBilletes.insertarBillete(pasajero.getBillete(j));
                    }
                }
            }

        }

    }

    /**
     * Almacena los datos de AirUPM en los ficheros CSV especificaciones.
     * @param ficheroAeropuertos nombre ficheroAeropuertos
     * @param ficheroAviones nombre ficheroAviones
     * @param ficheroVuelos nombre ficheroVuelos
     * @param ficheroPasajeros nombre ficheroPasajeros
     * @param ficheroBilletes nombre ficheroBilletes
     * @return true si se ha podido guardar los datos en el fichero deseado o false en caso contrario.
     */
    public boolean guardarDatos(String ficheroAeropuertos, String ficheroAviones, String ficheroVuelos, String ficheroPasajeros, String ficheroBilletes){

        boolean aeropuerto, avion, vuelo, pasajero, billete, correcto = false;

        aeropuerto = this.listaAeropuertos.escribirAeropuertosCsv(ficheroAeropuertos);
        avion = this.listaAviones.escribirAvionesCsv(ficheroAviones);
        vuelo = this.listaVuelos.escribirVuelosCsv(ficheroVuelos);
        pasajero = this.listaPasajeros.escribirPasajerosCsv(ficheroPasajeros);
        billete = this.listaBilletes.aniadirBilletesCsv(ficheroBilletes);

        if(aeropuerto && avion && vuelo && pasajero && billete) correcto = true;

        return correcto;

    }

    /**
     * Devuelve true si ha alcanzado el número máximo de vuelos permitidos o false en caso contrario.
     * @return true si máximo de vuelos alcanzado o false en caso contrario.
     */
    public boolean maxVuelosAlcanzado(){
        return listaVuelos.getOcupacion() >= maxVuelos;
    }

    /**
     * Devuelve true si se ha podido insertar el vuelo pasado por parámetro o false en caso contrario.
     * @param vuelo objeto vuelo pasado por parámetro
     * @return true si se ha podido insertar el vuelo o false en caso contrario.
     */
    public boolean insertarVuelo (Vuelo vuelo){
        return listaVuelos.insertarVuelo(vuelo);
    }

    /**
     * Devuelve true si se ha alcanzado el número máximo de pasajeros permitidos o false en caso contrario.
     * @return true si se ha alcanzado el máximo de pasajeros o false en caso contrario.
     */
    public boolean maxPasajerosAlcanzado(){
        return listaPasajeros.getOcupacion() >= maxPasajeros;
    }

    /**
     * Devuelve true si se ha podido insertar el pasajero pasado por parámetro o false en caso contrario.
     * @param pasajero objeto pasajero pasado por parámetro.
     * @return true si se ha podido insertar el pasajero o false en caso contrario.
     */
    public boolean insertarPasajero (Pasajero pasajero){
        return listaPasajeros.insertarPasajero(pasajero);
    }

    /**
     * Devuelve una lista de vuelos entre dos aeropuertos y una fecha de salida solicitados por
     * teclado al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @return una lista de vuelos que coincida con los aeropuertos de salida y llegada y la fecha de salida.
     */
    public ListaVuelos buscarVuelo(Scanner teclado){
        Aeropuerto origen = listaAeropuertos.seleccionarAeropuerto(teclado, "Introduzca el codigo de Aeropuerto Origen: ");
        Aeropuerto destino = listaAeropuertos.seleccionarAeropuerto(teclado, "Introduzca el codigo de Aeropuerto Destino: ");
        Fecha fecha = Utilidades.leerFecha(teclado, "Fecha de Salida: ");

        return listaVuelos.buscarVuelos(origen.getCodigo(), destino.getCodigo(),fecha);
    }

    /**
     * Compra un billete para un vuelo específicado pidiendo por teclado los datos necesario al usuario en el orden
     * y con los textos indicados en los ejemplos de ejecución del enunciado.
     * Si la lista de pasajeros está vacía, creará un nuevo pasajero. Si está llena, seleccionará un pasajero, en
     * cualquier otro caso, deberá preguntar al usuario si crear o seleccionar.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param rand objeto Random.
     * @param vuelo objeto Vuelo.
     */
    public void comprarBillete(Scanner teclado, Random rand, Vuelo vuelo){
        String seleccionarPasajero;
        Pasajero pasajero;
        Billete billete;
        do{
            System.out.print("¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)? ");
            seleccionarPasajero = teclado.next();
            if(!Objects.equals(seleccionarPasajero, "n") && !Objects.equals(seleccionarPasajero, "e")){
                System.out.println("El valor de entrada debe ser 'n' o 'e'");
            }
        }while(!Objects.equals(seleccionarPasajero, "n") && !Objects.equals(seleccionarPasajero, "e"));

        switch (seleccionarPasajero){
            case "e":
                do{
                    System.out.print("Ingrese el DNI del pasajero: ");
                    String dni = teclado.next();
                    pasajero = listaPasajeros.buscarPasajeroDNI(dni);
                    if(pasajero == null){
                        System.out.println("DNI no encontrado.");
                    }
                }while(pasajero == null);
                listaPasajeros.insertarPasajero(pasajero);
                billete = Billete.altaBillete(teclado, rand, vuelo, pasajero);
                pasajero.aniadirBillete(billete);
                listaBilletes.insertarBillete(billete);
                break;
            case "n":
                 pasajero = Pasajero.altaPasajero(teclado, listaPasajeros, maxBilletesPasajero);
                 listaPasajeros.insertarPasajero(pasajero);
                 billete = Billete.altaBillete(teclado, rand, vuelo, pasajero);
                 pasajero.aniadirBillete(billete);
                 listaBilletes.insertarBillete(billete);
                break;
        }
    }
    /**
     * Muestra el menú y solicita una opción por teclado.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @return entero introducido como opción por teclado.
     */
    public static int menu(Scanner teclado){
        int opcion;
        do {
            System.out.println("1. Alta Vuelo\n2. Alta Pasajero\n3. Buscar Vuelo\n4. Mostrar billetes de Pasajero\n5. " +
                    "Generar lista de Pasajeros\n0. Salir\n");
            opcion = Utilidades.leerNumero(teclado,"Seleccione opción: ", 0,5);
        }while (opcion < 0 || opcion > 5);
        return opcion;
    }

    /**
     * Carga los datos de los ficheros CSV pasados por argumento (consola) en AirUPM, llama iterativamente al menú y realiza la
     * opción especificada hasta que se indique la opción Salir, y finalmente guarda los datos de AirUPM en los mismos ficheros CSV.
     * @param args argumentos pasados por parámetros.
     */
    public static void main(String[] args) {
        if (args.length == 10) {
            AirUPM ejecucion = new AirUPM(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            ejecucion.cargarDatos(args[5], args[6], args[7], args[8], args[9]);

            int seleccion;
            Scanner teclado = new Scanner(System.in);
            Random random = new Random();

            do {
                seleccion = menu(teclado);
                switch (seleccion) {
                    case 1:
                        Vuelo vuelo;
                        if (!ejecucion.maxVuelosAlcanzado()) {
                            vuelo = Vuelo.altaVuelo(teclado, random, ejecucion.listaAeropuertos, ejecucion.listaAviones, ejecucion.listaVuelos);
                            if(ejecucion.insertarVuelo(vuelo)){
                                System.out.printf("Vuelo %s creado con éxito. \n", vuelo.getID());
                            }
                        }else
                            System.out.println("No se pueden dar de alta mas vuelos. Limite de altas de vuelo alcanzado.");
                        break;
                    case 2:
                        Pasajero pasajero;
                        if (!ejecucion.maxPasajerosAlcanzado()){
                            pasajero = Pasajero.altaPasajero(teclado, ejecucion.listaPasajeros, ejecucion.maxBilletesPasajero);
                            if(ejecucion.insertarPasajero(pasajero)){
                                System.out.printf("Pasajero con DNI %s dado de alta con éxito. \n", pasajero.getDNI());
                            }
                        }else
                            System.out.println("No se pueden dar de alta mas pasajeros. Limite de altas de pasajeros alcanzado.");
                        break;
                    case 3:
                        ListaVuelos lista = ejecucion.buscarVuelo(teclado);
                        if (lista.getOcupacion() != 0) {
                            lista.listarVuelos();
                            Vuelo vueloBuscado = ejecucion.listaVuelos.seleccionarVuelo(teclado, "Ingrese ID de vuelo para comprar billete o escriba CANCELAR: ", "CANCELAR");
                            if (vueloBuscado != null && vueloBuscado.numAsientosLibres() > 0) {
                                ejecucion.comprarBillete(teclado, random, vueloBuscado);
                            }else if(vueloBuscado != null && vueloBuscado.numAsientosLibres() <= 0){
                                System.out.println("No quedan asientos libres en el vuelo.");
                            }
                        } else
                            System.out.println("No se ha encontrado ningún vuelo.");
                        break;
                    case 4:
                        if (ejecucion.listaPasajeros.getOcupacion() != 0) {
                            Pasajero pasajeroS = ejecucion.listaPasajeros.seleccionarPasajero(teclado, "Ingrese DNI: ");
                            if (pasajeroS.numBilletesComprado() > 0) {
                                pasajeroS.listarBilletes();
                                Billete billeteS = pasajeroS.seleccionarBillete(teclado, "Ingrese localizador del billete: ");
                                char r;

                                do {
                                    System.out.print("¿Generar factura del billete (f), cancelarlo (c) o volver al menu (m)? ");
                                    r = teclado.next().charAt(0);
                                    if (r != 'm' && r != 'c' && r != 'f')
                                        System.out.println("El valor de entrada debe ser 'f', 'c' o 'm'");

                                } while (r != 'm' && r != 'c' && r != 'f');

                                if (r == 'f') {
                                    String ruta = Utilidades.leerCadena(teclado, "Introduzca la ruta donde generar la factura: ");
                                    billeteS.generarFactura(ruta);
                                    System.out.println("Factura de Billete PM1111IDIM generada en " + ruta);
                                } else if (r == 'c') {
                                    pasajeroS.cancelarBillete(billeteS.getLocalizador());
                                    billeteS.getVuelo().desocuparAsiento(billeteS.getLocalizador());
                                    ejecucion.listaBilletes.eliminarBillete(billeteS.getLocalizador());
                                    System.out.println("Billete " + billeteS.getLocalizador() + " cancelado.");
                                }

                            } else {
                                System.out.println("El pasajero seleccionado no ha adquirido ningún billete.");
                            }
                        }
                        break;
                    case 5:
                        String identificador;
                        do {
                            identificador = Utilidades.leerCadena(teclado, "Ingrese ID del vuelo: ");
                        } while (ejecucion.listaVuelos.buscarVuelo(identificador) == null);
                        String rutaP = Utilidades.leerCadena(teclado, "Introduzca la ruta donde generar la lista de pasajeros: ");
                        if (ejecucion.listaVuelos.buscarVuelo(identificador).generarListaPasajeros(rutaP))
                            System.out.printf("Lista de pasajeros del vuelo %s generada en %s \n", identificador, rutaP);
                        break;
                }
            } while (seleccion != 0);
            boolean terminado = ejecucion.guardarDatos("aeropuertos.csv", "aviones.csv", "vuelos.csv", "pasajeros.csv", "billetes.csv");
            if (terminado) {
                System.out.println("Ficheros guardados con éxito");
            }
        }
        else{
            System.out.println("Numero de argumentos incorrecto");
        }
    }
}
