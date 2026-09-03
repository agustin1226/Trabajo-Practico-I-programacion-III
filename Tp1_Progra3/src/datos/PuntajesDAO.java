package datos;

import logica.Usuario;

public interface PuntajesDAO {
    void guardarPuntaje(Usuario jugador, int puntaje);
    String obtenerRankingFormateado();
    int obtenerMejorPuntaje();
}
