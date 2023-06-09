package es.upm.tp;
import java.util.Scanner;

/**
 * Utilidades es una clase que encapsula métodos para leer datos entre un rango de mínimos y máximos.
 *
 * @author Diego García Guerrero
 * @author Alberto Chozas Enrique
 * @version 1.0
 */
public class Utilidades {
    /**
     * Lee un numero introducido por teclado y si está entre el rango lo devuelve.
     * En caso contrario, solicatará repetidamente un número entre el mínimo y máximo.
     * @param teclado objeto Scanner que recoge lo introducido por teclado.
     * @param mensaje cadena de texto que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @param minimo minimo.
     * @param maximo maximo.
     * @return número de tipo int entre un mínimo y máximo.
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo){
        int num;
        do{
            System.out.print(mensaje);
            num = teclado.nextInt();
        }while (num < minimo || num > maximo);
        return num;
    }

    /**
     * Lee un numero introducido por teclado y si está entre el rango lo devuelve.
     * En caso contrario, solicitará repetidamente un número entre el mínimo y máximo.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param mensaje cadena de texto que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @param minimo mínimo.
     * @param maximo máximo.
     * @return número de tipo long entre un mínimo y máximo.
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo){
        long num;
        do{
            System.out.print(mensaje);
            num = teclado.nextLong();
        }while(num < minimo || num > maximo);
        return num;
    }

    /**
     * Lee un número introducido por teclado y si está entre el rango lo devuelve.
     * En caso contrario, solicitará repetidamente un número entre el mínimo y máximo.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param mensaje cadena de texto que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @param minimo mínimo.
     * @param maximo máximo.
     * @return número de tipo double entre un mínimo y máximo.
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo)
    {
        double num;
        do{
            System.out.print(mensaje);
            num = teclado.nextDouble();
        }while(num < minimo || num > maximo);
        return num;
    }

    /**
     * Lee un caracter introducido por teclado y si está entre el rango lo devuelve.
     * En caso contrario, solicitará repetidamente un número entre el mínimo y máximo.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param mensaje cadena de texto que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @param minimo mínimo.
     * @param maximo máximo.
     * @return caracter entre mínimo y máximo.
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo)
    {
        char letra;
        do {
            System.out.print(mensaje);
            letra = teclado.next().charAt(0);
            teclado.nextLine();
        } while (letra > maximo || letra < minimo);
        return letra;
    }

    /**
     * Lee una cadena de texto hasta que se introduzca un "enter".
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param cadena cadena de texto que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @return String leído por teclado.
     */
    public static String leerCadena(Scanner teclado, String cadena){
        System.out.println(cadena);
        return teclado.nextLine();
    }

    /**
     * Solicita una fecha repetidamente hasta que se introduzca una correcta.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param mensaje cadena de texto que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @return objeto Fecha creado.
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje){
        int dia, mes, anio;

        do {
            System.out.println(mensaje);
            dia = leerNumero(teclado, "Ingrese dia: ", 1, 31);
            teclado.nextLine();
            mes = leerNumero(teclado, "Ingrese mes: ", 1, 12);
            teclado.nextLine();
            anio = leerNumero(teclado, "Ingrese año: ", 1900, 2100);
            if(!Fecha.comprobarFecha(dia, mes, anio)){
                System.out.println("Fecha introducida incorrecta.");
            }
        }while (!Fecha.comprobarFecha(dia, mes, anio));

        return new Fecha(dia, mes, anio);
    }

    /**
     * Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas.
     * @param teclado objeto Scanner que recoge los datos introducidos por teclado.
     * @param mensaje cadena de texto que se imprime por pantalla antes de empezar a introducir datos por teclado.
     * @return objeto Fecha+hora creado.
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje){
        int dia,mes,anio,hora,minuto,segundo;

        do {
            System.out.println(mensaje);
            dia = leerNumero(teclado, "Ingrese dia: ", 1, 31);
            teclado.nextLine();
            mes = leerNumero(teclado, "Ingrese mes: ", 1, 12);
            teclado.nextLine();
            anio = leerNumero(teclado, "Ingrese año: ", 1900, 2100);
            teclado.nextLine();
            hora = leerNumero(teclado, "Ingrese hora: ", 0, 23);
            teclado.nextLine();
            minuto = leerNumero(teclado, "Ingrese minuto: ", 0, 59);
            teclado.nextLine();
            segundo = leerNumero(teclado, "Ingrese segundo: ", 0, 59);
            if(!Fecha.comprobarFecha(dia, mes, anio) || !Fecha.comprobarHora(hora, minuto, segundo)) {
                System.out.println("Fecha u hora introducida incorrecta.");
            }
        }while (!Fecha.comprobarFecha(dia, mes, anio) || !Fecha.comprobarHora(hora, minuto, segundo));

        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }
}
