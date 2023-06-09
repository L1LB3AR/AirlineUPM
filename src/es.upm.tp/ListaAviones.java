package es.upm.tp;
import java.util.Scanner;
import java.io.*;

/**
 * Lista que contiene array de objetos de tipo Aviones. En cuánto a métodos, podremos insertar,
 * buscar, seleccionar Aviones y aplicarles esos métodos. Por último, también tenemos la posibilidad
 * de escribir y leer ficheros aplicánddolos a ésta lista.
 *
 * @author  Diego García Guerrero
 * @author  Alberto Chozas Enrique
 * @version  1.0
 */
public class ListaAviones {
    private int capacidad;
    private Avion[] listaAviones;

    private int ocupacion;

    /**
     * Constructor of the class
     *
     * @param capacidad capacidad
     */
    public ListaAviones(int capacidad){
        this.capacidad = capacidad;
        this.listaAviones = new Avion[capacidad];
        ocupacion = 0;
    }

    /**
     * Devuelve la ocupación.
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
    public boolean estaLlena(){return ocupacion >= capacidad;}

    /**
     * Devuelve el objeto de tipo Avión, en función de la posición pasada por parámetro.
     * @param posicion posición.
     * @return avión en función de una posición.
     */
    public Avion getAvion(int posicion){
        return listaAviones[posicion];
    }

    /**
     * Boolean que comprueba primero si la lista está llena.
     * Si no lo está, inserta un objeto de tipo avión, e incrementa la ocupación y devuelve true.
     * En caso contrario, devuelve false.
     * @param avion avion que queremos insertar.
     * @return true si se ha podido insertar el objeto de tipo Avión; en caso contrario, false.
     */
    public boolean insertarAvion(Avion avion){
        boolean insertado = false;
        for(int i = 0; i < capacidad && !insertado; i++) {
            if (!estaLlena() && listaAviones[i] == null) {
                listaAviones[i] = avion;
                insertado = true;
                ocupacion++;
            }
        }
        return insertado;
    }

    /**
     * Busca un objeto de tipo Avión en función de un String matricula pasado por parámetro.
     * @param matricula matricula del avión.
     * @return objeto de tipo avión buscado.
     */
    public Avion buscarAvion(String matricula){
        Avion avionBuscado = null;
        for(int i = 0; i < ocupacion; i++){
            if(listaAviones[i].getMatricula().equals(matricula)){
                avionBuscado = getAvion(i);
                i = ocupacion;
            }
        }
        return avionBuscado;
    }

    /**
     * Permite seleccionar un avión existente a partir de su matrícula, y comprueba si dispone de un alcance mayor o igual que el pasado como argumento,
     * usando el mensaje pasado como argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado
     * La función solicita repetidamente la matrícula del avión hasta que se introduzca uno con alcance suficiente.
     *
     * @param teclado objeto de tipo Scanner que recoge todo lo introducido por teclado.
     * @param mensaje String mensaje que se imprime antes de empezar el Scanner.
     * @param alcance alcance máximo del Avión.
     * @return Avión en función de los parámetros introducidos.
     */
    public Avion seleccionarAvion(Scanner teclado, String mensaje, double alcance){
        String matricula;
        do{
            System.out.println(mensaje);
            matricula = teclado.next();
            teclado.next(); //limpiamos el buffer
            if(this.buscarAvion(matricula) == null){
                System.out.println("No se encuentra ningún avion con la matricula seleccionada.");
            } else if(this.buscarAvion(matricula).getAlcance() < alcance){
                System.out.println("Avion con alcance insuficiente.");
            }
        }while(this.buscarAvion(matricula) == null || this.buscarAvion(matricula).getAlcance() < alcance);
     return this.buscarAvion(matricula);
    }

    /**
     * Boolean que devuelve true si ha podido escribir en el fichero y en caso contrario, false.
     *
     * @param nombre String con el nombre del fichero.
     * @return true si ha podido escribir en el fichero; false en caso contrario.
     */
    public boolean escribirAvionesCsv(String nombre){
        boolean correcto = true;
        PrintWriter escribir = null;
        try{
            escribir = new PrintWriter(nombre);
            for(int i = 0; i < getOcupacion()-1; i++){
                escribir.print(listaAviones[i].getMarca() + ";" + listaAviones[i].getModelo() + ";" + listaAviones[i].getMatricula() + ";" + listaAviones[i].getColumnas() + ";" + listaAviones[i].getFilas() + ";" + listaAviones[i].getAlcance());
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
     * Genera una lista de aviones a partir del fichero CSV, usando el argumento como capacidad
     * máximo de la lista.
     * @param fichero String con el nombre del fichero.
     * @param capacidad int con la capacidad de la lista de Aviones.
     * @return Lista de Aviones leida previamente.
     */
    public static ListaAviones leerAvionesCsv(String fichero, int capacidad){
        ListaAviones lista = new ListaAviones(capacidad);
        Scanner entrada = null;
        try{
            entrada = new Scanner(new FileReader(fichero));
            while(entrada.hasNext()){
                String[] linea = entrada.nextLine().split(";");
                String marca = linea[0];
                String modelo = linea[1];
                String matricula = linea[2];
                int fila = Integer.parseInt(linea[3]);
                int columna = Integer.parseInt(linea[4]);
                double alcance = Double.parseDouble(linea[5]);

                lista.insertarAvion(new Avion(marca,modelo,matricula,fila,columna,alcance));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            if(entrada != null) {
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
