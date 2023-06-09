package es.upm.tp;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

/**
 * Vuelo recoge determinadas variables para el correcto funcionamiento de los métodos.
 *
 * @author  Alberto Chozas Enrique
 * @author  Diego García Guerrero
 * @version 1.0
 */
public class Vuelo {
    private String id;
    private Avion avion;
    private Aeropuerto origen, destino;
    private int terminalOrigen, terminalDestino;
    private Fecha salida, llegada;
    private double precio;
    private boolean[][] asientos;
    private ListaBilletes listaBilletes;

    /**
     * Constructor of the class
     *
     * @param id id
     * @param avion avion
     * @param origen origen
     * @param terminalOrigen terminalOrigen
     * @param salida salida
     * @param destino destino
     * @param terminalDestino terminalDestino
     * @param llegada llegada
     * @param precio precio
     */
    public Vuelo(String id, Avion avion, Aeropuerto origen, int terminalOrigen, Fecha salida, Aeropuerto destino, int terminalDestino, Fecha llegada, double precio){
        this.id = id;
        this.avion = avion;
        this.origen = origen;
        this.terminalOrigen = terminalOrigen;
        this.salida = salida;
        this.destino = destino;
        this.terminalDestino = terminalDestino;
        this.llegada = llegada;
        this.precio = precio;
        asientos = new boolean[avion.getFilas()][avion.getColumnas()];
        listaBilletes = new ListaBilletes(avion.getColumnas()*avion.getFilas());
    }

    /**
     * Getter del atributo ID.
     * @return Id del vuelo.
     */
    public String getID(){
        return id;
    }

    /**
     * Getter del atributo Avion.
     * @return objeto Avion.
     */
    public Avion getAvion(){
        return avion;
    }

    /**
     * Getter del atributo Aeropuerto.
     * @return objeto Aeropuerto.
     */
    public Aeropuerto getOrigen(){
        return origen;
    }

    /**
     * Getter del atributo Terminal Origen.
     * @return entero de la TerminalOrigen.
     */
    public int getTerminalOrigen(){
        return terminalOrigen;
    }

    /**
     * Getter del atributo Fecha de Salida.
     * @return fecha de salida.
     */
    public Fecha getSalida(){
        return salida;
    }

    /**
     * Getter del atributo Destino.
     * @return Aeropuerto de Destino.
     */
    public Aeropuerto getDestino(){
        return destino;
    }

    /**
     * Getter del atributo Terminal Destino.
     * @return entero Terminal Destino.
     */
    public int getTerminalDestino(){
        return terminalDestino;
    }

    /**
     * Getter del atributo Fecha de Llegada.
     * @return Fecha de llegada.
     */
    public Fecha getLlegada(){
        return llegada;
    }

    /**
     * Devuelve el precio del billete.
     * @return Precio Billete.
     */
    public double getPrecio(){
        return precio;
    }

    /**
     * Devuelve el precio de un billete de clase Preferente.
     * @return Precio Billete Preferente.
     */
    public double getPrecioPreferente(){
        return precio;
    }

    /**
     * Devuelve el precio de un billete de Primera clase.
     * @return Precio Billete Primera clase.
     */
    public double getPrecioPrimera(){
        return precio;
    }

    /**
     * Devuelve el número que queda de asientos libres.
     * @return número asientos libres.
     */
    public int getNumAsientosLibres(){return numAsientosLibres();}

    /**
     * Cuenta el número de asientos libres y lo devuelve.
     * @return número de asientos libres.
     */
    public int numAsientosLibres(){
        int asientosLibres = 0;
        for(int i = 0; i < avion.getFilas();i++){
            for(int j = 0; j < avion.getColumnas();j++){
                if(!asientos[i][j]){
                    asientosLibres++;
                }
            }
        }
        return asientosLibres;
    }

    /**
     * Devuele true si el número de asientos es equivalente a 0. False en caso contrario.
     * @return true si no hay asintos libres; false en caso contrario.
     */
    public boolean vueloLleno(){
        return numAsientosLibres() == 0;
    }

    /**
     * Devuelve true si el asiento está ocupado o false en caso contrario.
     * @param fila fila donde está el asiento.
     * @param columna columna donde está el asiento.
     * @return true si el asiento estña ocupado o false en caso contrario.
     */
    public boolean asientoOcupado(int fila, int columna){
        return asientos[fila][columna];
    }

    /**
     * Devuelve un objeto de tipo Billete según el localizador pasado por parámetro. En caso
     * de que no exista, devuelve null.
     * @param localizador localizador del billete.
     * @return billete con localizador asociado.
     */
    public Billete buscarBillete(String localizador){
        return listaBilletes.buscarBillete(localizador);
    }

    /**
     * Devuelve el objeto billete que corresponde con una fila o columna.
     * En caso, devolverá null si está libre o se excede en el límite de fila y columna.
     * @param fila fila donde se encuentra el asiento.
     * @param columna columna donde se encuentra el asiento.
     * @return objeto Billete asociado a la fila y columna; en caso contrario, null.
     */
    public Billete buscarBillete(int fila, int columna){
        Billete billete = null;
        boolean billeteEncontrado = false;
        int f = 1;
        int c = 1;
        while(f <= fila && !billeteEncontrado){
            while(c <= columna && !billeteEncontrado){
                if(!asientos[f][c]){
                    billete = listaBilletes.buscarBillete(id, fila, columna);
                    billeteEncontrado = true;
                }
                c++;
            }
            f++;
        }
        return billete;

    }

    /**
     * Si está desocupado el asiento que indica el billete, lo pone ocupado y devuelve true.
     * En caso contrario, devuelve false.
     * @param billete Billete donde indica el asiento que queremos ocupar.
     * @return true si el asiento está desocupado o false en caso contrario.
     */
    public boolean ocuparAsiento(Billete billete){
        if(asientos[billete.getFila()-1][billete.getColumna()-1]){
            System.out.println("Lo sentimos, ese asiento está ocupado.");
            return false;
        }else {
            asientos[billete.getFila() - 1][billete.getColumna() - 1] = true;
            return true;
        }
    }

    /**
     * A través del localizador de billete, se desocupará el asiento.
     * @param localizador localizador del billete.
     * @return true si se ha podido desocupar el asiento o false en caso contrario.
     */
    //A través del localizador de billete, se desocupará el asiento
    public boolean desocuparAsiento(String localizador){
        listaBilletes.eliminarBillete(localizador);
        return true;
    }

    /**
     * Añade los billetes al final de un fichero CSV, sin sobreescribirlo.
     * @param fichero nombre del fichero.
     * @return true si se ha podido añadir el nuevo billete al fichero o false en caso contrario.
     */
    // Añade los billetes al final de un fichero CSV, sin sobreescribirlo
    public boolean aniadirBilletesCsv(String fichero){
        boolean correcto = true;
        PrintWriter aniadirBillete = null;
        try{
            aniadirBillete = new PrintWriter(fichero);
            for(int i = 0; i < avion.getFilas(); i++) {
                aniadirBillete.print("\n");
                for (int j = 0; j < avion.getColumnas(); j++) {
                aniadirBillete.print(this);
                }
            }
        } catch(FileNotFoundException exception){
            System.out.println(exception.getMessage());
            correcto = false;
        } finally{
            if(aniadirBillete != null) aniadirBillete.close();
        }
        return correcto;
    }

    /**
     * Devuelve una cadena con información completa del vuelo con un determinado formato:
     * Vuelo PM0066 de Josep Tarradellas Barcelona-El Prat(BCN) T2 (01/01/2023 08:15:00) a Gran Canaria(LPA) T1 (01/01/2023 11:00:05) en Boeing 747(EC-LKD) por 182,52 €, asientos libres: 409
     * @return string con toda la información del vuelo.
     */
    public String toString(){
        return "Vuelo " + getID() + " de " + origen.getNombre() + "(" + origen.getCodigo() + ") T" + origen.getTerminales()
                + " (" + getSalida() + ") a " + destino.getNombre() + "(" + destino.getCodigo() +") T" + destino.getTerminales()
                + " (" + getLlegada() + ")\nen " + avion.toStringSimple() + " por " + getPrecio() + "€, asientos libres: " + getNumAsientosLibres();
    }

    /**
     * Devuelve una cadena con información completa del vuelo con un formato abreviado:
     * Vuelo PM0066 de Josep Tarradellas Barcelona-El Prat(BCN) T2 (01/01/2023 08:15:00) a Gran Canaria(LPA) T1 (01/01/2023 11:00:05)
     * @return string con la información del vuelo simplificada.
     */
    public String toStringSimple(){
        return "Vuelo " + getID() + "de " + origen.getNombre() + "(" + origen.getCodigo() + ") " + origen.getTerminales()
                + " (" + getSalida() + ") a " + destino.getNombre() + "(" + destino.getCodigo() +") " + destino.getTerminales()
                + " (" + getLlegada() + ")";
    }

    /**
     * Devuelve true si el código origen, destino y fecha son los mismos que el vuelo.
     * @param codigoOrigen código del Aeropuerto origen.
     * @param codigoDestino código del Aeropuerto destino.
     * @param fecha salida.
     * @return true si código origen, destino y fecha son los mismos que el vuelo.
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha){
        boolean coincide;
        coincide = Objects.equals(codigoOrigen, origen.getCodigo()) && Objects.equals(codigoDestino, destino.getCodigo())
                && (fecha.coincide(salida) || fecha.coincide(llegada));
        return coincide;
    }

    /**
     * Imprime la matriz de asientos del vuelo, ejemplo:
     *  A  B  C  D  E  F
     *  1( )(X)( )( )( )( )
     *  2{X}{X}{ }{ }{ }{ }
     *  3{ }{ }{ }{X}{X}{X}
     *  4{ }{ }{ }{ }{ }{ }
     *  5{ }{ }{X}{ }{ }{ }
     *  6[ ][ ][ ][ ][ ][ ]
     *  7[X][X][X][ ][ ][ ]
     *  8[ ][ ][ ][ ][ ][ ]
     *  9[ ][X][ ][ ][ ][X]
     *  10[ ][ ][ ][ ][ ][ ]
     */
    public void imprimirMatrizAsientos(){
        String espacioA, espacioB, medio, alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

        //La primera fila, que indica la letra correspondiente a cada columna
        System.out.print(" ");
        for(int columna = 1; columna <= avion.getColumnas(); columna++){
            System.out.printf("  %c", alfabeto.charAt(columna-1));
        }
        System.out.print("\n");

        //El resto de la matriz
        for(int fila = 1; fila <= avion.getFilas(); fila++){

            //Para imprimir el numero de la fila
            if(fila < 10){
                System.out.print(" " + fila);
            }else
                System.out.print(fila);

            //Para distinguir los distintos tipos de asientos
            switch (fila) {
                case 1:
                    espacioA = "(";
                    espacioB = ")";
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    espacioA = "{";
                    espacioB = "}";
                    break;
                default:
                    espacioA = "[";
                    espacioB = "]";
                    break;
            }

            //Para imprimir los asientos
            for (int asiento = 1; asiento <= avion.getColumnas(); asiento++) {
                if (asientos[fila-1][asiento-1]){
                    medio = "X";
                }else
                    medio = " ";
                System.out.printf("%s%s%s", espacioA, medio, espacioB);
            }
            System.out.print("\n");
        }
        System.out.println("\nTipo de asientos: '[ ]' = TURISTA, '{ }' = PREFERENTE, '( )' = PRIMERA");
    }

    /**
     * Devuelve true si ha podido escribir en un fichero la lista de pasajeros del vuelo, siguiendo las indicaciones del enunciado.
     * @param fichero nombre del fichero.
     * @return true si se ha podido escribir en el fichero o false en caso contrario.
     */
    public boolean generarListaPasajeros(String fichero){
        boolean correcto = true;
        PrintWriter escribir = null;
        try
        {
            escribir = new PrintWriter(fichero);
            escribir.println("--------------------------------------------------");
            escribir.println("------- Lista de pasajeros en vuelo PM1990 -------");
            escribir.println("--------------------------------------------------");
            escribir.println("Asiento   Tipo        Pasajero");

            for(int i = 0; i < (avion.getColumnas() * avion.getFilas()); i++)
            {
                if(listaBilletes.getBillete(i) != null)
                {
                    System.out.printf("%s\t%s\t%s, %s, %s", listaBilletes.getBillete(i).getAsiento(), listaBilletes.getBillete(i).getTipo(), listaBilletes.getBillete(i).getPasajero().getNombre(), listaBilletes.getBillete(i).getPasajero().getDNI(), listaBilletes.getBillete(i).getPasajero().getEmail());

                } else{
                    System.out.println(" ");
                }
            }

        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            correcto = false;
        } catch(Exception exception){
            System.out.println("Error de escritura");
            correcto = false;
        } finally{
            if(escribir != null)
            {
                try
                {
                    escribir.close();
                } catch(Exception ee){
                    System.out.println("Error de cierre");
                    correcto = false;
                }
            }
        }
        return correcto;
    }

    //Métodos estáticos

    /**
     * Genera un ID de vuelo. Este consistirá en una cadena de 6 caracteres, de los cuales los dos
     * primeros serán PM y los 4 siguientes serán números aleatorios. Ejemplo: PM0123
     *
     * @param rand genera el número aleatorio.
     * @return String PM+númeroAleatorio.
     */
    public static String generarID(Random rand){
        return "PM" + rand.nextInt(9999);
    }

    /**
     * Crea y devuelve un objeto Vuelo de los datos que selecciona el usuario de aeropuertos y aviones y la restricción de que
     * no puede estar repetido el identificador, siguiendo las indicaciones del enunciado.
     * La función solicita repetidamente los parámetros hasta que sean correctos.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param rand objeto Random que genera el número aleatorio.
     * @param aeropuertos Lista de Aeropuertos.
     * @param aviones Lista de Aviones.
     * @param vuelos Lista de Vuelos.
     * @return objeto Vuelo creado.
     */
    public static Vuelo altaVuelo(Scanner teclado, Random rand, ListaAeropuertos aeropuertos, ListaAviones aviones, ListaVuelos vuelos){
        Aeropuerto origen, destino;
        String codigoOrigen, codigoDestino, matricula;
        int terminalOrigen, terminalDestino;
        Avion avion;
        Fecha salida, llegada;
        double precio;

        do{
            System.out.print("Ingrese código de Aeropuerto Origen: ");
            codigoOrigen = teclado.next();
            origen = aeropuertos.buscarAeropuerto(codigoOrigen);
            if(origen == null){
                System.out.println("Código de Aeropuerto no encontrado.");
            }
        }while (origen == null);
        do{
            System.out.print("Ingrese Terminal Origen " + "(1 - " + origen.getTerminales() + "): ");
            terminalOrigen = teclado.nextInt();
        }while (terminalOrigen < 1 || terminalOrigen > origen.getTerminales());
        do{
            System.out.print("Ingrese código de Aeropuerto Destino: ");
            codigoDestino = teclado.next();
            destino = aeropuertos.buscarAeropuerto(codigoDestino);
            if(destino == null){
                System.out.println("Código de Aeropuerto no encontrado.");
            }
        }while (destino == null);
        do{
            System.out.print("Ingrese Terminal Destino " + "(1 - " + destino.getTerminales() + "): ");
            terminalDestino = teclado.nextInt();
        }while (terminalDestino < 1 || terminalDestino > destino.getTerminales());
        do {
            System.out.print("Ingrese matrícula de Avión: ");
            matricula = teclado.next();
            avion = aviones.buscarAvion(matricula);
            if (avion != null && avion.getAlcance() < origen.distancia(destino)) {
                System.out.println("Avión seleccionado con alcance insuficiente (menor que " + origen.distancia(destino) + " km).");
                avion = null;
            }else if(avion == null){
                System.out.println("Matrícula de avión no encontrada.");
            }
        }while (avion == null);
        do {
            System.out.println("(La salida debe ser anterior a la llegada)");
            salida = Utilidades.leerFechaHora(teclado, "Fecha de salida: ");
            llegada = Utilidades.leerFechaHora(teclado, "Fecha de llegada: ");
        }while (llegada.anterior(salida));
        do{
            System.out.print("Ingrese precio de pasaje: ");
            precio = teclado.nextDouble();
        }while (precio < 0);

        Vuelo vuelo = new Vuelo(generarID(rand), avion, origen, terminalOrigen, salida, destino, terminalDestino, llegada, precio);
        vuelos.insertarVuelo(vuelo);

        return vuelo;
    }


}