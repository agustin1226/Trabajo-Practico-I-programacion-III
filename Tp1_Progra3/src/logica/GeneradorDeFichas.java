package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorDeFichas {
    
    private static final int TAMANO_GRILLA = 4;
    private Random random;

    public GeneradorDeFichas() {
        this.random = new Random();
    }

    public void generarFichaAleatoria(Casilla[][] grilla) {
        List<Casilla> vacias = new ArrayList<>();
        
        for (int f = 0; f < TAMANO_GRILLA; f++) {
            for (int c = 0; c < TAMANO_GRILLA; c++) {
                if (grilla[f][c].estaVacia()) {
                    vacias.add(grilla[f][c]);
                }
            }
        }
        
        int valorInicial = random.nextInt(3) + 1; 
        ubicarFicha(vacias, valorInicial);
    }

    public void generarEnBordeOpuesto(Direccion dir, Casilla[][] grilla, int valorFicha) {
        List<Casilla> vaciasEnBorde = new ArrayList<>();
        int limite = TAMANO_GRILLA - 1;

        switch (dir) {
            case ARRIBA: 
                for (int c = 0; c < TAMANO_GRILLA; c++) {
                    if (grilla[limite][c].estaVacia()) {
                        vaciasEnBorde.add(grilla[limite][c]);
                    }
                }
                break;
            case ABAJO: 
                for (int c = 0; c < TAMANO_GRILLA; c++) {
                    if (grilla[0][c].estaVacia()) {
                        vaciasEnBorde.add(grilla[0][c]);
                    }
                }
                break;
            case IZQUIERDA: 
                for (int f = 0; f < TAMANO_GRILLA; f++) {
                    if (grilla[f][limite].estaVacia()) {
                        vaciasEnBorde.add(grilla[f][limite]);
                    }
                }
                break;
            case DERECHA: 
                for (int f = 0; f < TAMANO_GRILLA; f++) {
                    if (grilla[f][0].estaVacia()) {
                        vaciasEnBorde.add(grilla[f][0]);
                    }
                }
                break;
        }
        
        ubicarFicha(vaciasEnBorde, valorFicha);
    }

    private void ubicarFicha(List<Casilla> opciones, int valor) {
        if (!opciones.isEmpty()) {
            Casilla elegida = opciones.get(random.nextInt(opciones.size()));
            elegida.setFicha(new Ficha(valor));
        }
    }
}