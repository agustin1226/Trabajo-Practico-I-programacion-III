package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logica.Usuario;

public class PuntajesArchivoDAO implements PuntajesDAO {

    private final String RUTA_ARCHIVO = "ranking.txt";

    @Override
    public void guardarPuntaje(Usuario jugador, int puntaje) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            // Extraemos el nombre directamente del objeto Usuario
            escritor.write(jugador.getNombre() + "," + puntaje);
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el puntaje: " + e.getMessage());
        }
    }

    @Override
    public String obtenerRankingFormateado() {
        List<Registro> listaJugadores = leerYOrdenarPuntajes();
        
        if (listaJugadores.isEmpty()) {
            return "Aún no hay puntajes registrados.";
        }

        StringBuilder ranking = new StringBuilder();
        int posicion = 1;
        for (Registro r : listaJugadores) {
            ranking.append(posicion).append(". ").append(r.nombre).append(" - ").append(r.puntaje).append("\n");
            posicion++;
            if (posicion > 10) break; 
        }
        return ranking.toString();
    }

    @Override
    public int obtenerMejorPuntaje() {
        List<Registro> listaJugadores = leerYOrdenarPuntajes();
        if (listaJugadores.isEmpty()) {
            return 0;
        }
        return listaJugadores.get(0).puntaje; 
    }

    private List<Registro> leerYOrdenarPuntajes() {
        List<Registro> listaJugadores = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return listaJugadores;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int puntaje = Integer.parseInt(partes[1].trim());
                    listaJugadores.add(new Registro(nombre, puntaje));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer los puntajes: " + e.getMessage());
        }

        listaJugadores.sort((r1, r2) -> Integer.compare(r2.puntaje, r1.puntaje));
        return listaJugadores;
    }

    // Estructura interna solo para ordenar los datos leídos
    private static class Registro {
        String nombre;
        int puntaje;

        Registro(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }
    }
}