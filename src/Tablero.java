import java.util.ArrayList;

import java.util.List;

import java.util.Random;

public class Tablero {

    private Celda[][] celdas;

    private List<Animal> animales;

    private List<Planta> plantas;

    public Tablero(int filas, int columnas) {

        celdas = new Celda[filas][columnas];

        animales = new ArrayList<>();

        plantas = new ArrayList<>();

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                celdas[i][j] = new Celda();

            }

        }

    }

    public boolean esPosicionValida(int fila, int columna) {

        return fila >= 0 && fila < celdas.length && columna >= 0 && columna < celdas[0].length;

    }

    public Celda getCelda(int fila, int columna) {

        return celdas[fila][columna];

    }

    public void agregarAnimal(Animal animal) {

        animales.add(animal);

        celdas[animal.getFila()][animal.getColumna()].setSerVivo(animal);

    }

    public void agregarPlanta(Planta planta) {

        plantas.add(planta);

        celdas[planta.getFila()][planta.getColumna()].setSerVivo(planta);

    }

    public void moverSerVivo(SerVivo serVivo, int nuevaFila, int nuevaColumna) {

        celdas[serVivo.getFila()][serVivo.getColumna()].setSerVivo(null);

        serVivo.setFila(nuevaFila);

        serVivo.setColumna(nuevaColumna);

        celdas[nuevaFila][nuevaColumna].setSerVivo(serVivo);

    }

    public int contarAnimales() {

        return animales.size();

    }

    public int contarPlantas() {

        return plantas.size();

    }

    public void ejecutarCiclo(Evento evento, HistorialEstados historial, int ciclo) {

        List<String> nacimientos = new ArrayList<>();

        List<String> muertes = new ArrayList<>();

        StringBuilder eventos = new StringBuilder();

        eventos.append(evento).append(" ");

       /* en mi autómata celular hay simulación de nacimientos y muertes */

        Random rand = new Random();

        if (rand.nextBoolean()) {

            int fila = rand.nextInt(celdas.length);

            int columna = rand.nextInt(celdas[0].length);

            Animal nuevoAnimal = new Animal(fila, columna);

            agregarAnimal(nuevoAnimal);

            nacimientos.add("Nacimiento de animal en celda [" + fila + "," + columna + "]");

        }

        if (rand.nextBoolean()) {

            int fila = rand.nextInt(celdas.length);

            int columna = rand.nextInt(celdas[0].length);

            Planta nuevaPlanta = new Planta(fila, columna);

            agregarPlanta(nuevaPlanta);

            nacimientos.add("Nacimiento de planta en celda [" + fila + "," + columna + "]");

        }

        for (Animal animal : animales) {

            if (animal.isEstaVivo()) {

                animal.pasarTiempo(this, evento);

            }

        }

        for (Planta planta : plantas) {

            if (planta.isEstaVivo()) {

                planta.pasarTiempo(this, evento);

            }

        }

        animales.removeIf(animal -> {

            if (!animal.isEstaVivo()) {

                muertes.add("Muere animal en celda [" + animal.getFila() + "," + animal.getColumna() + "]");

                return true;

            }

            return false;

        });

        plantas.removeIf(planta -> {

            if (!planta.isEstaVivo()) {

                muertes.add("Muere planta en celda [" + planta.getFila() + "," + planta.getColumna() + "]");

                return true;

            }

            return false;

        });

        historial.agregarEstado(ciclo + 1, contarAnimales(), contarPlantas(), String.join(" / ", nacimientos), String.join(" / ", muertes), eventos.toString());

    }

    @Override

    public String toString() {

        StringBuilder estadoActual = new StringBuilder();

        for (int i = 0; i < celdas.length; i++) {

            for (int j = 0; j < celdas[i].length; j++) {

                estadoActual.append(celdas[i][j].toString()).append(" ");

            }

            estadoActual.append("\n");

        }

        return estadoActual.toString();

    }

}