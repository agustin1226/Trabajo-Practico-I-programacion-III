package logica;

public class Tablero {
    private Casilla[][] grilla;
    private GeneradorDeFichas generador;
    private GestorMovimiento gestorMovimiento;

    public Tablero() {
        this.grilla = new Casilla[4][4];
        this.generador = new GeneradorDeFichas();
        this.gestorMovimiento = new GestorMovimiento();
        
        // Inicializamos las casillas
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 4; c++) {
                grilla[f][c] = new Casilla(f, c);
            }
        }
        
        // Fichas iniciales
        for (int i = 0; i < 2; i++) {
            generador.generarFichaAleatoria(grilla);
        }
    }

    // Devuelve los puntos que ganó el jugador
    public int mover(Direccion direccion) {
        int puntosGanados = gestorMovimiento.ejecutarMovimiento(direccion, grilla);
        
        if (puntosGanados != -1) { // Si hubo movimiento real
            generador.generarEnBordeOpuesto(direccion, grilla);
        }
        
        return puntosGanados;
    }

    public boolean hayMovimientosPosibles() {
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 4; c++) {
                Casilla actual = grilla[f][c];
                if (actual.estaVacia()) return true;
                if (f < 3 && actual.getFicha().puedeFusionarseCon(grilla[f + 1][c].getFicha())) return true;
                if (c < 3 && actual.getFicha().puedeFusionarseCon(grilla[f][c + 1].getFicha())) return true;
            }
        }
        return false;
    }

    public Casilla getCasilla(int fila, int columna) {
        return grilla[fila][columna];
    }
}