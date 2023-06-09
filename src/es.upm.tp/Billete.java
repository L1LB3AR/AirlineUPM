package es.upm.tp;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Billete es una clase que recoge varias variables con varios métodos. Ésta clase cuenta con sus métodos
 * getters y setters. Podremos comprar billetes, generar facturas y localizadores de los billetes.
 *
 * @author Alberto Chozas Enrique
 * @author Diego García Guerrero
 * @version 1.0
 */
public class Billete {
    enum TIPO { TURISTA, PREFERENTE, PRIMERA }
    private String localizador;
    private Vuelo vuelo;
    private Pasajero pasajero;
    private TIPO tipo;
    private int fila, columna;
    private double precio;
    /**
     * Constructor of the class
     *
     * @param localizador Identidad del billete
     * @param vuelo asociado al billete
     * @param pasajero poseedor del billete
     * @param tipo clase de tarifa (turistra, preferente, primera)
     * @param fila donde esta el asiento
     * @param columna donde esta el asiento
     * @param precio precio del billete, según la clase
     */
    public Billete(String localizador, Vuelo vuelo, Pasajero pasajero, TIPO tipo, int fila, int columna, double precio){
        setLocalizador(localizador);
        setVuelo(vuelo);
        setPasajero(pasajero);
        setTipo(tipo);
        setFila(fila);
        setColumna(columna);
        setPrecio(precio);
    }

    /**
     * Getter del atributo Localizador
     *
     * @return Localizador del Billete.
     */
    public String getLocalizador(){
        return localizador;
    }

    /**
     * Setter del atributo Localizador
     *
     * @param localizador localizador del Billete.
     */
    public void setLocalizador(String localizador){this.localizador = localizador;}

    /**
     * Getter del atributo Vuelo
     *
     * @return Objeto Vuelo del Billete.
     */
    public Vuelo getVuelo(){
        return vuelo;
    }

    /**
     * Setter del atributo Vuelo
     *
     * @param vuelo vuelo del Billete.
     */
    public void setVuelo(Vuelo vuelo){this.vuelo = vuelo;}

    /**
     * Getter del atributo Pasajero
     *
     * @return Objeto Pasajero del Billete.
     */
    public Pasajero getPasajero(){
        return pasajero;
    }

    /**
     * Setter del atributo Pasajero
     *
     * @param pasajero pasajero del Billete.
     */
    public void setPasajero(Pasajero pasajero){this.pasajero = pasajero;}

    /**
     * Getter del atributo Tipo
     *
     * @return Tipo del Billete.
     */
    public TIPO getTipo(){
        return tipo;
    }

    /**
     * Setter del atributo Tipo
     *
     * @param tipo tipo del Billete.
     */
    public void setTipo(TIPO tipo){this.tipo = tipo;}

    /**
     * Getter del atributo Fila
     *
     * @return Número de Fila del asiento en el Billete.
     */
    public int getFila(){
        return fila;
    }

    /**
     * Setter del atributo Fila
     *
     * @param fila número de fila del asiento que aparece en el Billete.
     */
    public void setFila(int fila){this.fila = fila;}

    /**
     * Getter del atributo Columna
     *
     * @return Número de Columna del asiento en el Billete.
     */
    public int getColumna(){
        return columna;
    }

    /**
     * Setter del atributo Columna
     *
     * @param columna número de columna del asiento que aparece en el Billete.
     */
    public void setColumna(int columna){this.columna = columna;}

    /**
     * Getter del atributo Precio
     *
     * @return Precio del Billete.
     */
    public double getPrecio(){
        return precio;
    }

    /**
     * Setter del atributo Precio
     *
     * @param precio valor del precio del Billete.
     */
    public void setPrecio(double precio){
        this.precio = precio;
    }

    /**
     * Retorna el asiento con la fila y columna.
     *
     * @return Cadena de texto con la fila y la columna.
     */
    public String getAsiento(){
        return fila + "" + columna;
    }

    /**
     * Retorna el Billete en una cadena texto con el siguiente formato:
     * Billete PM1111AAAA para Vuelo PM1111 de MAD T4 (24/12/2022 12:35:00) a BCN T1 (24/12/2022 14:05:30) en asiento 6C (TURISTA) por 100.00 €
     *
     * @return Cadena de texto con todo acerca del Billete.
     */
    public String toString(){
        return "Billete " + getLocalizador() + " para Vuelo " + vuelo.toString() + " en asiento " + getAsiento() + "(" + getTipo() + ") por " + getPrecio() +"€";
    }

    /**
     * Retorna valor booleano, true si ha conseguido cancelar el billete, eliminandolo de la lista
     * de billetes del vuelo y del pasajero correspondiente o false en caso contrario.
     *
     * @return valor true si ha cancelado el billete o false si no.
     */
    public boolean cancelar(){
        return vuelo.desocuparAsiento(localizador) && pasajero.cancelarBillete(localizador);
    }

    /**
     * Imprime la información de este billete en un fichero siguiendo el formato de los
     * ejemplos de ejecución del enunciado.
     *
     * @param fichero   cadena de texto con el nombre del fichero.
     * @return un valor booleano, true si ha consguido imprimir la informacion del billete con
     * formato pedido; false en caso contrario.
     */
    public boolean generarFactura(String fichero){
        PrintWriter salida = null;
        boolean escrito = true;
        try
        {
            salida = new PrintWriter(fichero);
            salida.printf("""
                    --------------------------------------------------
                    --------- Factura del billete %s ---------
                    --------------------------------------------------
                    Vuelo: %s
                    Origen: %s (%s) T%d
                    Destino: %s %s T%d
                    Salida: %s
                    Llegada: %s
                    Pasajero: %s %s, %s, %s
                    Tipo de billete: %s
                    Asiento: %s
                    Precio: %.2f€""",
                    this.localizador,vuelo.getID(),vuelo.getOrigen().getNombre(), vuelo.getOrigen().getCodigo(), vuelo.getTerminalOrigen(),
                    vuelo.getDestino().getNombre(),vuelo.getDestino().getCodigo(),vuelo.getTerminalDestino(), vuelo.getSalida(),
                    vuelo.getLlegada(), pasajero.getNombre(), pasajero.getApellidos(), pasajero.getDNI(), pasajero.getEmail(), getTipo().toString(),
                    getAsiento(),getPrecio());

        } catch(Exception e){
            System.out.println(e.getMessage());
            escrito = false;
        } finally {
            if(salida != null)
            {
                try{
                    salida.close();
                    System.out.printf("Facturada de Billete %s generada en %s", vuelo.getID(),fichero);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                    escrito = false;
                }
            }
        }
        return escrito;
    }

    // Métodos estáticos

    /**
     * Genera un localizador de Billete. Este consistirá en una cadena de 10 caracteres, de los cuales
     * los seis primeros será el ID del vuelo asociado y los 4 siguientes serán letras mayúsculas
     * aleatorias.
     *
     * @param rand el generador de letras mayusculas aleatorias.
     * @param idVuelo el identificador de vuelo asociado.
     * @return Cadena de texto con el Localizador del Billete.
     */
    public static String generarLocalizador(Random rand, String idVuelo){
        String localizador = idVuelo, caracteres = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        char car;
        int num;
        for (int i = 1; i <= 4; i++){
            num = rand.nextInt(9);
            localizador += num;
        }
        for (int j = 1; j <= 4; j++) {
            car = caracteres.charAt(rand.nextInt(caracteres.length()));
            localizador += car;
        }
        return localizador;
    }

    /**
     * Crea un nuevo billete para un vuelo y pasajero específico, pidiendo por teclado los datos
     * necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del
     * enunciado.
     * La función solicita repetidamente los parámetros hasta que sean correctos.
     *
     * @param teclado objeto de tipo Scanner que recoge la claseSeleccionada por teclado.
     * @param rand el generador de letras aleatorias.
     * @param vuelo objeto de tipo Vuelo asociado al billete.
     * @param pasajero objeto de tipo Pasajero asociado al billete.
     * @return Billete creado a partir de ésta función.
     */
    public static Billete altaBillete(Scanner teclado, Random rand, Vuelo vuelo, Pasajero pasajero){
        String localizador;
        TIPO claseSeleccionada = null;
        int fila, columna;
        double precio;
        Billete billete;

        localizador = generarLocalizador(rand, vuelo.getID());

        do {
            vuelo.imprimirMatrizAsientos();
            do {
                System.out.println("Hola " + pasajero.getNombre() + ", ¿en que clase desea viajar?");
                String clase = teclado.nextLine();
                try {
                    claseSeleccionada = TIPO.valueOf(clase);
                } catch (IllegalArgumentException claseIncorrecta) {
                    System.out.println("Lo siento, debe elegir una clase entre TURISTA, PREFERENTE o PRIMERA.");
                }
            } while (claseSeleccionada == null);

            fila = seleccionarFila(teclado, vuelo, claseSeleccionada);

            System.out.println("Por favor, seleccione un asiento para la fila" + fila + "(1-" + vuelo.getAvion().getColumnas() + "): ");
            columna = Utilidades.leerNumero(teclado, "", 1, vuelo.getAvion().getColumnas());

            precio = switch (claseSeleccionada) {
                case TURISTA -> vuelo.getPrecio();
                case PREFERENTE -> vuelo.getPrecioPreferente();
                case PRIMERA -> vuelo.getPrecioPrimera();
            };
            billete = new Billete(localizador, vuelo, pasajero, claseSeleccionada, fila, columna, precio);
        }while (!vuelo.ocuparAsiento(billete));

        System.out.println("Su " + billete + " ha sido comprado con éxito, que tenga un buen vuelo.");
        return billete;
    }

    /**
     * Selecciona la fila donde sentarse:
     * Primera, Preferente o Turista.
     * Y la devuelve.
     * @param teclado objeto de tipo Scanner, que recoge la opción.
     * @param vuelo objeto de tipo Vuelo asociado al billete.
     * @param clase rango de calidad del asiento.
     * @return entero asociado al tipo de asiento.
     */
    private static int seleccionarFila(Scanner teclado, Vuelo vuelo, TIPO clase){
        int fila = 0;
        switch (clase){
            case PRIMERA:
                System.out.println("Para primera clase, está reservada la primera fila del avión.");
                fila = 1;
                break;
            case PREFERENTE:
                System.out.println("Por favor, seleccione una fila de clase preferente (2-5): ");
                fila = Utilidades.leerNumero(teclado, "", 2, 5);
                break;
            case TURISTA:
                System.out.println("Por favor, seleccione una fila de clase turista (6-"+ vuelo.getAvion().getFilas() + "): ");
                fila = Utilidades.leerNumero(teclado, "", 6, vuelo.getAvion().getFilas());
        }
        return fila;
    }
}