package logica;

public class GestorMovimiento {

    // Devuelve los puntos ganados en el turno (o -1 si no se movió nada)
    public int ejecutarMovimiento(Direccion dir, Casilla[][] grilla) {
        boolean movioAlgo = false;
        int puntosGanados = 0;

        for (int i = 0; i < 4; i++) {
            Casilla[] linea = extraerLinea(i, dir, grilla);
            int puntosLinea = desplazarLinea(linea);
            
            if (puntosLinea >= 0) { // Si devolvió al menos 0, hubo movimiento
                movioAlgo = true;
                puntosGanados += puntosLinea;
            }
        }
        return movioAlgo ? puntosGanados : -1;
    }

    private int desplazarLinea(Casilla[] linea) {
        boolean cambio = false;
        int puntos = 0;

        for (int i = 0; i < 3; i++) {
            Casilla actual = linea[i];
            Casilla siguiente = linea[i + 1];

            if (!siguiente.estaVacia()) {
                if (actual.estaVacia()) {
                    actual.setFicha(siguiente.getFicha());
                    siguiente.vaciar();
                    arrastrarResto(linea, i + 1);
                    cambio = true;
                } else if (actual.getFicha().puedeFusionarseCon(siguiente.getFicha())) {
                    // CORRECCIÓN 1: Llamamos a fusionar() en lugar de fusionarCon()
                    Ficha nuevaFicha = actual.getFicha().fusionar(siguiente.getFicha());
                    actual.setFicha(nuevaFicha);
                    siguiente.vaciar();
                    arrastrarResto(linea, i + 1);
                    
                    // CORRECCIÓN 2: Usamos getValor() porque calcularPuntaje() ya no existe
                    puntos += nuevaFicha.getValor(); 
                    cambio = true;
                }
            }
        }
        return cambio ? puntos : -1;
    }

    private void arrastrarResto(Casilla[] linea, int desde) {
        for (int i = desde; i < 3; i++) {
            if (!linea[i + 1].estaVacia()) {
                linea[i].setFicha(linea[i + 1].getFicha());
                linea[i + 1].vaciar();
            }
        }
    }

    private Casilla[] extraerLinea(int indice, Direccion dir, Casilla[][] grilla) {
        Casilla[] linea = new Casilla[4];
        for (int j = 0; j < 4; j++) {
            switch (dir) {
                case ARRIBA:    linea[j] = grilla[j][indice]; break;
                case ABAJO:     linea[j] = grilla[3 - j][indice]; break;
                case IZQUIERDA: linea[j] = grilla[indice][j]; break;
                case DERECHA:   linea[j] = grilla[indice][3 - j]; break;
            }
        }
        return linea;
    }
}