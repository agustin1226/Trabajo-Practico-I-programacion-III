package datos;

import logica.Usuario;

public interface Puntajes {
    void guardarPuntaje(Usuario jugador, int puntaje);
    String obtenerRankingFormateado();
    int obtenerMejorPuntaje();
}
