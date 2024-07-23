import java.io.File;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int filas = 10;

        int columnas = 10;

        Tablero tablero = new Tablero(filas, columnas);

        HistorialEstados historial = new HistorialEstados("Data/estadisticas.csv"); /* se crea manualmente la carpeta Data afuera de src, afuera de out y afuera de .idea, si no existe esto es para almacenar el archivo csv, no se debe borrar esa carpeta, ya que csv depende mucho de ese directorio. */

        /* inicialización aleatoria de animales y plantas */

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {

            int fila = rand.nextInt(filas);

            int columna = rand.nextInt(columnas);

            Animal animal = new Animal(fila, columna);

            tablero.agregarAnimal(animal);

        }

        for (int i = 0; i < 20; i++) {

            int fila = rand.nextInt(filas);

            int columna = rand.nextInt(columnas);

            Planta planta = new Planta(fila, columna);

            tablero.agregarPlanta(planta);

        }

        /* en mi autómata celular se hace una ejecución de ciclos de vida   */

        for (int ciclo = 0; ciclo < 20; ciclo++) {

            Evento evento = Evento.values()[rand.nextInt(Evento.values().length)];

            tablero.ejecutarCiclo(evento, historial, ciclo);

            /* se guarda el estado del tablero y las estadísticas*/

            String estado = "Tiempo: " + (ciclo + 1) + "\n" + tablero.toString();

            System.out.println("Evento: " + evento);

            System.out.println(estado);

        }

       /* se guarda el historial en el CSV */

        historial.guardarHistorial();

    }

}
