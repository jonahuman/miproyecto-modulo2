import java.io.FileWriter;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;

import java.util.List;

public class HistorialEstados {

    private List<String> estados;

    private String rutaArchivo;

    public HistorialEstados(String rutaArchivo) {

        this.estados = new ArrayList<>();

        this.rutaArchivo = rutaArchivo;

    }

    public void agregarEstado(int tiempo, int numAnimales, int numPlantas, String nacimientos, String muertes, String eventos) {

        String estado = tiempo + ";" + numAnimales + ";" + numPlantas + ";" + nacimientos + ";" + muertes + ";" + eventos;

        estados.add(estado);

    }

    public void guardarHistorial() {

        try (FileWriter writer = new FileWriter(rutaArchivo, StandardCharsets.UTF_8)) {

            writer.write("Tiempo;Animales;Plantas;Nacimientos;Muertes;Eventos\n");

            for (String estado : estados) {

                writer.write(estado + "\n");

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}