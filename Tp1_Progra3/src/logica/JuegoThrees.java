package logica;

import java.util.ArrayList;

public class JuegoThrees {
    private static final int MOVIMIENTO_INVALIDO = -1;
    private static final int[] VALORES_BASE = {1, 2, 3};
    private Tablero tablero;
    private int puntajeActual;
    private ArrayList<ObservadorJuego> observadores;
    private int valorProximaFicha; 

    public JuegoThrees() {
        this.tablero = new Tablero();
        this.puntajeActual = 0;
        this.observadores = new ArrayList<>();        
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

    public void procesarMovimiento(Direccion dir) {      
        int puntosObtenidos = tablero.mover(dir, this.valorProximaFicha);
        if (puntosObtenidos != MOVIMIENTO_INVALIDO) {
            puntajeActual += puntosObtenidos;             
            this.valorProximaFicha = generarValorAleatorio();          
            notificarObservadores(); 
            
            if (estaTerminado()) {
                System.out.println("Fin del juego. Puntaje final: " + puntajeActual);
            }
        }
    }
    public boolean estaTerminado() {
        return !tablero.hayMovimientosPosibles();
    }
    public Tablero getTablero() {
        return tablero;
    }
    public int getPuntajeActual() {
        return puntajeActual;
    }
    private int generarValorAleatorio() {
        int indice = (int) (Math.random() * VALORES_BASE.length);
        return VALORES_BASE[indice];
    }
}