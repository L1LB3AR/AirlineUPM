package es.upm.tp;
import java.util.Objects;
import java.util.Scanner;

/**
 * Pasajero encapsula determinadas variables que permiten el funcionamiento de los métodos. En esta
 * clase podremos sobre todo, dar de alta a un pasajero.
 * Por otro lado, también tenemos métodos de tipo Billete , para buscar, seleccionar, añanir o listar
 * Billetes.
 *
 * @author Diego García Guerrero
 * @author Alberto Chozas Enrique
 * @version 1.0
 */
public class Pasajero {
    private String nombre, apellidos, email;
    private long numeroDNI;
    private char letraDNI;
    private int maxBilletes;
    private ListaBilletes listaBilletes;

    /**
     * Constructor of the class
     *
     * @param nombre nombre
     * @param apellidos apellidos
     * @param numeroDNI numeroDNI
     * @param letraDNI letraDNI
     * @param email email
     * @param maxBilletes numero maximo de billetes
     */
    public Pasajero(String nombre, String apellidos, long numeroDNI, char letraDNI, String email, int maxBilletes){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroDNI = numeroDNI;
        this.letraDNI = letraDNI;
        this.email = email;
        this.maxBilletes = maxBilletes;
        listaBilletes = new ListaBilletes(maxBilletes);
    }

    /**
     * Getter del atributo Nombre.
     * @return nombre del Pasajero.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Getter del atributo Apellidos.
     * @return apellidos del Pasajero.
     */
    public String getApellidos(){
        return  apellidos;
    }

    /**
     * Getter del atributo NumeroDNI.
     * @return numero del DNI del Pasajero.
     */
    public long getNumeroDNI(){
        return numeroDNI;
    }

    /**
     * Getter del atributo LetraDNI
     * @return letra del DNI del Pasajero.
     */
    public char getLetraDNI(){
        return letraDNI;
    }

    /**
     * Devuelve el DNI completo del Pasajero en función de un formato.
     * @return DNI del pasajero.
     */
    public String getDNI(){
        return String.format(String.format("%08d%s", numeroDNI, letraDNI));
    }

    /**
     * Getter del atributo Email.
     * @return email del Pasajero.
     */
    public String getEmail(){
        return email;
    }

    /**
     * Devuelve el número máximo de billetes permitidos.
     * @return máximo de billetes.
     */
    public int getMaxBilletes(){
        return maxBilletes;
    }

    /**
     * Devuelve un String en función del siguiente formato:
     * Víctor Rodríguez Fernández, 00123456S, victor.rfernandez@upm.es
     * @return cadena de texto en un formato determinado.
     */
    public String toString(){
        return nombre + " " + apellidos + ", " + numeroDNI + letraDNI + ", " + email;
    }

    /**
     * Devuelve el número de billetes adquiridos por parte del pasajero.
     * @return número de billetes comprados.
     */
    public int numBilletesComprado(){
        return listaBilletes.getOcupacion();
    }

    /**
     * Boolean que devuelve true si la condición de superación del máximo permitido se ha sobrepasado.
     * En caso contrario, devolverá false.
     * @return  true si se ha alcanzado el máximo de billetes comprado; false en caso contrario.
     */
    public boolean maxBilletesAlcanzado(){
        return numBilletesComprado() >= maxBilletes;
    }

    /**
     * Devuelve un objeto de tipo Billete en base a un int pasado por parámetro.
     * @param i posición
     * @return Billete de la lista de billetes en la posición i.
     */
    public Billete getBillete(int i){
        return listaBilletes.getBillete(i);
    }

    /**
     * Devuelve true si se ha podido añadir el Billete pasado por parámetro. False en caso contrario.
     * @param billete objeto Billete.
     * @return true si billete ha sido añadido; false en caso contrario.
     */
    public boolean aniadirBillete(Billete billete){
        return listaBilletes.insertarBillete(billete);
    }

    /**
     * Buscamos un Billete en la lista de Billetes, en base a un Localizador que se para por parámetro.
     * Si lo encontramos, lo devolvemos. En caso contrario, devolverá null.
     * @param localizador String con el id localizador del billete.
     * @return Billete que coincide con el Localizador.
     */
    public Billete buscarBillete(String localizador){
        return listaBilletes.buscarBillete(localizador);
    }

    /**
     * Elimina el billete de la lista de billetes del pasajero.
     * @param localizador String con el id localizador del billete.
     * @return true si se ha podido eliminar con éxito el billete; false en caso contrario.
     */
    public boolean cancelarBillete(String localizador){
        return listaBilletes.eliminarBillete(localizador);
    }

    /**
     * Imprime por pantalla los billetes comprado por el pasajero.
     */
    public void listarBilletes(){
        for(int i = 1; i <= numBilletesComprado(); i++){
            System.out.println(i + ". " + listaBilletes.getBillete(i-1).toString());
        }
    }

    /**
     * Encapsula la funcionalidad seleccionarBillete de ListaBilletes.
     * @param teclado objeto Scanner que recoge todo lo introducido por teclado.
     * @param mensaje String que se mostrará antes de empezar a introducir datos por teclado.
     * @return Billete con el localizador coincidente.
     */
    public Billete seleccionarBillete(Scanner teclado, String mensaje){
        String localizador;
        System.out.println(mensaje);
        localizador = teclado.nextLine();
        return buscarBillete(localizador);
    }

    //Métodos estáticos

    /**
     * Crea un nuevo pasajero no repetido, pidiendo por teclado los datos necesarios al usuario
     * en el orden y con los textos indicados en los ejemplos del enunciado.
     * La función solicita repetidamente los parámetros hasta que sean correctos.
     * @param teclado objeto Scanner que recoge todo lo introducido por teclado.
     * @param pasajeros Lista de Pasajeros.
     * @param maxBilletes número máximo de pasajeros permitido.
     * @return pasajero creado.
     */
    public static Pasajero altaPasajero(Scanner teclado, ListaPasajeros pasajeros, int maxBilletes){
        String nombre, apellidos, email;
        char letraDNI;
        long numeroDNI;
        teclado.nextLine();
        System.out.print("Ingrese nombre: ");
        nombre = teclado.nextLine();
        System.out.print("Ingrese apellidos: ");
        apellidos = teclado.nextLine();
        do {
            System.out.print("Ingrese número de DNI: ");
            numeroDNI = teclado.nextLong();
            System.out.print("Ingrese letra de DNI: ");
            letraDNI = teclado.next().charAt(0);
            if(correctoDNI(numeroDNI, letraDNI)){
                System.out.println("DNI incorrecto.");
            }
        }while (correctoDNI(numeroDNI, letraDNI));
        teclado.nextLine();
        do {
            System.out.print("Introduzca el email: ");
            email = teclado.nextLine();
            if(correctoEmail(email)){
                System.out.println("Email incorrecto.");
            }else if(pasajeros.buscarPasajeroEmail(email) != null){
                System.out.println("Email ya existe.");
            }
        }while (correctoEmail(email) || pasajeros.buscarPasajeroEmail(email) != null);

        Pasajero pasajero = new Pasajero(nombre, apellidos, numeroDNI, letraDNI, email, maxBilletes);
        pasajeros.insertarPasajero(pasajero);
        return pasajero;
    }

    /**
     * Devuelve true si el DNI es correcto. False en caso contrario.
     * @param numero numero de DNI sin letra.
     * @param letra letra del DNI.
     * @return true si es correcto el DNI del pasajero.
     */
    public static boolean correctoDNI(long numero, char letra){
        boolean correcto = true;
        char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        if(letra != asignacionLetra[Math.toIntExact(numero)%23]){
            correcto = false;
        }
        return !correcto;
    }

    /**
     * Devuelve true si el email del pasajero tiene terminación @upm.es ó @alumnos.upm.es
     * False en caso contrario.
     * @param email email del pasajero.
     * @return true si el email es correcto o false en caso contrario.
     */
    public static boolean correctoEmail(String email){
        boolean correcto;
        if(!email.contains("@")){
            return true;
        }else {
            String[] usuario = email.split("@");
            if (!Objects.equals(usuario[1], "upm.es") && !Objects.equals(usuario[1], "alumnos.upm.es")) {
                correcto = false;
            } else if (usuario[0].startsWith(".") || usuario[0].endsWith(".")) {
                correcto = false;
            } else
                correcto = esAlfabetico(usuario[0]);
        }
        return !correcto;
    }

    /**
     * @param caracteres Cadena de texto de caracteres.
     * @return true si la cadena de texto no contiene ninguno de los caracteres expuestos en el bucle
     * en caso contrario, devolverá false.
     */
    public static boolean esAlfabetico(String caracteres){
        boolean alfabetico = true;
        int i = 0;
        if(caracteres != null){
            while (i < caracteres.length() && alfabetico){
                char c = caracteres.charAt(i);
                if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !(c >= '0' && c <= '9') && c != '.') {
                    alfabetico = false;
                }
                i++;
            }
        }
        return alfabetico;
    }
}
