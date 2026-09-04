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

public class PuntajesArchivo implements Puntajes {

    private static final String RUTA_ARCHIVO = "ranking.txt";
    private static final int MAX_TOP_RANKING = 10;

    @Override
    public void guardarPuntaje(Usuario jugador, int puntaje) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            escritor.write(jugador.getNombre() + "," + puntaje);
            escritor.newLine();
        } catch (IOException e) { //excepcion
            System.out.println("Error al guardar el puntaje: " + e.getMessage());
        }
    }

    @Override
    public String obtenerRankingFormateado() {
        String resultado = "Aún no hay puntajes registrados.";
        List<Registro> listaJugadores = leerYOrdenarPuntajes();
        
        if (!listaJugadores.isEmpty()) {
            StringBuilder ranking = new StringBuilder();
            int posicion = 1;
            int indice = 0;
            
            while (indice < listaJugadores.size() && posicion <= MAX_TOP_RANKING) {
                Registro r = listaJugadores.get(indice);
                ranking.append(posicion).append(". ").append(r.nombre).append(" - ").append(r.puntaje).append("\n");
                posicion++;
                indice++;
            }
            resultado = ranking.toString();
        }
        
        return resultado;
    }

    @Override
    public int obtenerMejorPuntaje() {
        int mejor = 0;
        List<Registro> listaJugadores = leerYOrdenarPuntajes();
        
        if (!listaJugadores.isEmpty()) {
            mejor = listaJugadores.get(0).puntaje;
        }
        
        return mejor;
    }

    private List<Registro> leerYOrdenarPuntajes() {
        List<Registro> listaJugadores = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (archivo.exists()) {
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                String linea = lector.readLine();
                while (linea != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        String nombre = partes[0].trim();
                        int puntaje = Integer.parseInt(partes[1].trim());
                        listaJugadores.add(new Registro(nombre, puntaje));
                    }
                    linea = lector.readLine();
                }
            } catch (IOException | NumberFormatException e) { //excepcion
                System.out.println("Error al leer los puntajes: " + e.getMessage());
            }
            
            listaJugadores.sort((r1, r2) -> Integer.compare(r2.puntaje, r1.puntaje));
        }

        return listaJugadores;
    }

    private static class Registro {
        String nombre;
        int puntaje;

        Registro(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }
    }
}