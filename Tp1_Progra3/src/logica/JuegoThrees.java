package logica;

import java.util.ArrayList;

public class JuegoThrees {
    private Tablero tablero;
    private int puntajeActual;
    private ArrayList<ObservadorJuego> observadores;
    private int valorProximaFicha; // Guardamos el valor que está en espera

    public JuegoThrees() {
        this.tablero = new Tablero();
        this.puntajeActual = 0;
        this.observadores = new ArrayList<>();
        
        // Sorteamos la primera ficha que va a aparecer en el cartel de la ventana
        this.valorProximaFicha = generarValorAleatorio(); 
    }

    public void registrarObservador(ObservadorJuego obs) {
        observadores.add(obs);
    }

    private void notificarObservadores() {
        for (ObservadorJuego obs : observadores) {
            obs.notificar(this);
        }
    }

    public int getValorProximaFicha() {
        return this.valorProximaFicha;
    }

    // Este es el método que llama el ControladorTeclado
    public void procesarMovimiento(Direccion dir) {
        
        // 1. Ejecutamos el movimiento pasándole la ficha que teníamos en el cartel
        int puntosObtenidos = tablero.mover(dir, this.valorProximaFicha);
        
        // Si devuelve distinto de -1, significa que el movimiento fue válido
        if (puntosObtenidos != -1) {
            puntajeActual += puntosObtenidos; 
            
            // 2. Sorteamos la que va a venir recién en el PRÓXIMO turno
            this.valorProximaFicha = generarValorAleatorio();
            
            // Le avisamos a la interfaz que redibuje el tablero y el cartel nuevo
            notificarObservadores(); 
            
            if (estaTerminado()) {
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
    
    // Método auxiliar para obtener 1, 2 o 3.
    // Si tu GeneradorDeFichas ya tiene algo parecido, podés usarlo en lugar de este.
    private int generarValorAleatorio() {
        int[] valoresIniciales = {1, 2, 3};
        int indice = (int) (Math.random() * valoresIniciales.length);
        return valoresIniciales[indice];
    }
}