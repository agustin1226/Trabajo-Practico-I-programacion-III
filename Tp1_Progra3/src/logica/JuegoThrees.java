package logica;

import java.util.ArrayList;

public class JuegoThrees {
    private Tablero tablero;
    private int puntajeActual;
    private ArrayList<ObservadorJuego> observadores;

    public JuegoThrees() {
        this.tablero = new Tablero();
        this.puntajeActual = 0;
        this.observadores = new ArrayList<>();
    }

    public void registrarObservador(ObservadorJuego obs) {
        observadores.add(obs);
    }

    private void notificarObservadores() {
        for (ObservadorJuego obs : observadores) {
            obs.notificar(this);
        }
    }

 // Este es el método que va a llamar el ControladorTeclado
    public void procesarMovimiento(Direccion dir) {
        // Ahora mover() nos devuelve un int con los puntos de las fusiones
        int puntosObtenidos = tablero.mover(dir);
        
        // Si devuelve distinto de -1, significa que el movimiento fue válido
        if (puntosObtenidos != -1) {
            puntajeActual += puntosObtenidos; // puntaje al total
            
            notificarObservadores(); // Le avisamos a la interfaz que redibuje
            
            if (estaTerminado()) {
                // Cuando el juego termina, la aplicación debe mostrar el puntaje
                System.out.println("Fin del juego. Puntaje final: " + puntajeActual);
            }
        }
    }
    public boolean estaTerminado() {
        // Le preguntamos al tablero si el jugador se quedó sin opciones
        return !tablero.hayMovimientosPosibles();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

}