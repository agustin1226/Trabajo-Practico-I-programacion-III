package logica;

public class GestorMovimiento {

    private static final int TAMANO_GRILLA = 4;

    // Devuelve los puntos ganados
    public int ejecutarMovimiento(Direccion dir, Casilla[][] grilla) {
        boolean movioAlgo = false;
        int puntosGanados = 0;
        int resultado = -1;

        for (int i = 0; i < TAMANO_GRILLA; i++) {
            Casilla[] linea = extraerLinea(i, dir, grilla);
            int puntosLinea = desplazarLinea(linea);            
            if (puntosLinea >= 0) { 
                movioAlgo = true;
                puntosGanados += puntosLinea;
            }
        }       
        if (movioAlgo) {
            resultado = puntosGanados;
        }      
        return resultado;
    }

    private int desplazarLinea(Casilla[] linea) {
        boolean cambio = false;
        int puntos = 0;
        int resultado = -1;
        for (int i = 0; i < TAMANO_GRILLA - 1; i++) {
            Casilla actual = linea[i];
            Casilla siguiente = linea[i + 1];
            if (!siguiente.estaVacia()) {
                if (actual.estaVacia()) {
                    actual.setFicha(siguiente.getFicha());
                    siguiente.vaciar();
                    arrastrarResto(linea, i + 1);
                    cambio = true;
                } else if (actual.getFicha().puedeFusionarseCon(siguiente.getFicha())) {
                    Ficha nuevaFicha = actual.getFicha().fusionar(siguiente.getFicha());
                    actual.setFicha(nuevaFicha);
                    siguiente.vaciar();
                    arrastrarResto(linea, i + 1);                    
                    puntos += nuevaFicha.getValor(); 
                    cambio = true;
                }
            }
        }       
        if (cambio) {
            resultado = puntos;
        }       
        return resultado;
    }
    private void arrastrarResto(Casilla[] linea, int desde) {
        for (int i = desde; i < TAMANO_GRILLA - 1; i++) {
            if (!linea[i + 1].estaVacia()) {
                linea[i].setFicha(linea[i + 1].getFicha());
                linea[i + 1].vaciar();
            }
        }
    }
    private Casilla[] extraerLinea(int indice, Direccion dir, Casilla[][] grilla) {
        Casilla[] linea = new Casilla[TAMANO_GRILLA];
        int limite = TAMANO_GRILLA - 1;
        for (int j = 0; j < TAMANO_GRILLA; j++) {
            switch (dir) {
                case ARRIBA:    
                    linea[j] = grilla[j][indice]; 
                    break;
                case ABAJO:     
                    linea[j] = grilla[limite - j][indice]; 
                    break;
                case IZQUIERDA: 
                    linea[j] = grilla[indice][j]; 
                    break;
                case DERECHA:   
                    linea[j] = grilla[indice][limite - j]; 
                    break;
            }
        }
        return linea;
    }
}