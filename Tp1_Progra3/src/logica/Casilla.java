package logica;

public class Casilla {
    private int fila;
    private int columna;
    private Ficha ficha; // Si es null, significa que la casilla está vacía

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.ficha = null; // Toda casilla arranca vacía por defecto
    }

    public boolean estaVacia() {
        return this.ficha == null;
    }

    public Ficha getFicha() {
        return this.ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public void vaciar() {
        this.ficha = null;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}