package logica;

public class Tablero {
    private static final int TAMANO_GRILLA = 4;
    private static final int FICHAS_INICIALES = 2;
    private static final int MOVIMIENTO_INVALIDO = -1;
    private Casilla[][] grilla;
    private GeneradorDeFichas generador;
    private GestorMovimiento gestorMovimiento;

    public Tablero() {
        this.grilla = new Casilla[TAMANO_GRILLA][TAMANO_GRILLA];
        this.generador = new GeneradorDeFichas();
        this.gestorMovimiento = new GestorMovimiento();
        for (int f = 0; f < TAMANO_GRILLA; f++) {
            for (int c = 0; c < TAMANO_GRILLA; c++) {
                grilla[f][c] = new Casilla(f, c);
            }
        }
        for (int i = 0; i < FICHAS_INICIALES; i++) {
            generador.generarFichaAleatoria(grilla);
        }
    }
    public int mover(Direccion direccion, int valorProximaFicha) {
        int puntosGanados = gestorMovimiento.ejecutarMovimiento(direccion, grilla);  
        if (puntosGanados != MOVIMIENTO_INVALIDO) { 
            generador.generarEnBordeOpuesto(direccion, grilla, valorProximaFicha);
        }        
        return puntosGanados;
    }

    public boolean hayMovimientosPosibles() {
        boolean posible = false;
        int f = 0;
        int limite = TAMANO_GRILLA - 1;
        while (f < TAMANO_GRILLA && !posible) {
            int c = 0;
            while (c < TAMANO_GRILLA && !posible) {
                Casilla actual = grilla[f][c];             
                if (actual.estaVacia()) {
                    posible = true;
                } else if (f < limite && actual.getFicha().puedeFusionarseCon(grilla[f + 1][c].getFicha())) {
                    posible = true;
                } else if (c < limite && actual.getFicha().puedeFusionarseCon(grilla[f][c + 1].getFicha())) {
                    posible = true;
                }
                c++;
            }
            f++;
        }      
        return posible; 
    }
    public Casilla getCasilla(int fila, int columna) {
        return grilla[fila][columna];
    }
}