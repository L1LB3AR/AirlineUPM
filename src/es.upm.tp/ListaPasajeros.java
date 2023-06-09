package es.upm.tp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lista que contiene objetos de tipo Pasajeros. En cuánto a métodos, podremos insertar, buscar,
 * seleccionar y aplicarles esos métodos. Por otro lado, también tenemos la posibilidad de
 * escribir y leer ficheros.
 *
 * @author Alberto Chozas Enrique
 * @author Diego García Guerrero
 * @version 1.0
 */
public class ListaPasajeros {
    private int capacidad;
    private Pasajero[] listaPasajeros;
    private int ocupacion;
    
    /**
     * Constructor of the class
     *
     * @param capacidad capacidad
     */
    public ListaPasajeros(int capacidad){
        this.capacidad = capacidad;
        listaPasajeros = new Pasajero[capacidad];
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
     * Devuelve el objeto de tipo Pasaero, en función de la posición pasada por parámetro.
     * @param i posición.
     * @return pasajero de la lista, en función de la posición pasada por parámetro.
     */
    public Pasajero getPasajero(int i){
        return listaPasajeros[i];
    }

    /**
     * Boolean que comprueba primero si la lista está llena.
     * Si no lo está, inserta un objeto de tipo Pasajero, e incremente la ocupación y devuelve true.
     * En caso contrario, devuelve false.
     * @param pasajero pasajero que queremos insertar.
     * @return objeto de tipo Pasajero buscado.
     */
    public boolean insertarPasajero(Pasajero pasajero){
        if(!estaLlena()) {
            listaPasajeros[ocupacion] = pasajero;
            ocupacion++;
            return true;
        }else {
            System.out.println("Lo sentimos, la lista está llena");
            return false;
        }
    }

    /**
     * Busca un pasajero en función de un String pasado por parámetro como DNI
     * Si lo encuentra, devuelve un objeto de tipo pasajero.
     * @param dni String dni.
     * @return pasajero buscado en función DNI.
     */
    public Pasajero buscarPasajeroDNI(String dni){
        Pasajero pasajero = null;
        for(int i = 0; i < ocupacion; i++){
            if (listaPasajeros[i].getDNI().equals(dni)){
                pasajero = listaPasajeros[i];
                i = ocupacion;
            }
        }
        return pasajero;
    }

    /**
     * Busca un pasajero en función de un String pasado por parámetro como email
     * Si lo encuentra devuelve un objeto de tipo pasajero.
     * @param email String email.
     * @return pasaero buscado a través del email.
     */
    public Pasajero buscarPasajeroEmail(String email){
        Pasajero pasajero = null;
        for(int i = 0; i < ocupacion; i++){
            if (listaPasajeros[i].getEmail().equals(email)){
                pasajero = listaPasajeros[i];
                i = ocupacion;
            }
        }
        return pasajero;
    }

    /**
     * Permite seleccionar un pasaero existente a partir de su DNI, usando el mensaje pasado como
     * argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     * La función solita repetidamente hasta que se introduzca un DNI correcto.
     * @param teclado objeto de tipo Scanner.
     * @param mensaje String que se imprime por pantalla antes de introducir datos por teclado.
     * @return objeto de tipo Pasajero buscado.
     */
    public Pasajero seleccionarPasajero(Scanner teclado, String mensaje){
        String dni;
        do{
            System.out.print(mensaje);
            dni = teclado.next();
            teclado.next();
            if(this.buscarPasajeroDNI(dni) == null){
                System.out.println("No se encuentra ningún pasajero con el DNI introducido.");
            }
        }while(this.buscarPasajeroDNI(dni) == null);
        return this.buscarPasajeroDNI(dni);
    }

    /**
     * Genera un fichero CSV con la lista de pasajeros, sobreescribiéndolo.
     * @param fichero String con el nombre del fichero.
     * @return true si se ha podido escribir el pasajero en el fichero; en caso contrario, false.
     */
    public boolean escribirPasajerosCsv(String fichero){
        boolean correcto = true;
        PrintWriter escribir = null;
        try{
            escribir = new PrintWriter(fichero);
            for(int i = 0; i < getOcupacion()-1; i++){
                escribir.print(listaPasajeros[i].getNombre() + ";" + listaPasajeros[i].getApellidos() + ";" + listaPasajeros[i].getNumeroDNI() + ";" + listaPasajeros[i].getLetraDNI() + ";" + listaPasajeros[i].getEmail());
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            correcto = false;
        } finally{
            try{
                if(escribir != null) escribir.close();
                else correcto = false;
            } catch(Exception exception){
                System.out.println(exception.getMessage());
                correcto = false;
            }
        }
        return correcto;
    }

    /**
     * Genera una lista de pasajeros a partir del fichero CSV, usando las límites especificados
     * como argumentos para la capacidad de la lista y el número de billetes máximo por pasajero.
     * @param fichero String con el nombre del fichero.
     * @param capacidad capacidad.
     * @param maxBilletesPasajero límite de Billetes de Pasajeros.
     * @return Lista de pasajeros leidos previamente.
     */
    public static ListaPasajeros leerPasajerosCsv(String fichero, int capacidad, int maxBilletesPasajero){
        ListaPasajeros lista = new ListaPasajeros(capacidad);
        Scanner entrada = null;
        try{
            entrada = new Scanner(new FileReader(fichero));
            while(entrada.hasNext()){
                String[] linea = entrada.nextLine().split(";");
                String nombre = linea[0];
                String apellidos = linea[1];
                long dni = Long.parseLong(linea[2]);
                char letraDni = linea[3].charAt(0);
                String email = linea[4];

                lista.insertarPasajero(new Pasajero(nombre,apellidos,dni,letraDni,email,maxBilletesPasajero));
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            if(entrada != null){
                try{
                    entrada.close();
                } catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        }
        return lista;
    }
}