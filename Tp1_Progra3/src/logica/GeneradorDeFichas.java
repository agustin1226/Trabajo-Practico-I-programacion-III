package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorDeFichas {
    private Random random;

    public GeneradorDeFichas() {
        this.random = new Random();
    }

    // Método para arrancar el juego (busca cualquier casilla vacía)
    public void generarFichaAleatoria(Casilla[][] grilla) {
        List<Casilla> vacias = new ArrayList<>();
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 4; c++) {
                if (grilla[f][c].estaVacia()) vacias.add(grilla[f][c]);
            }
        }
        ubicarFicha(vacias);
    }

    // Método para cuando el usuario se mueve (busca solo en el borde opuesto)
    public void generarEnBordeOpuesto(Direccion dir, Casilla[][] grilla) {
        List<Casilla> vaciasEnBorde = new ArrayList<>();
        switch (dir) {
            case ARRIBA: 
                for (int c = 0; c < 4; c++) if (grilla[3][c].estaVacia()) vaciasEnBorde.add(grilla[3][c]);
                break;
            case ABAJO: 
                for (int c = 0; c < 4; c++) if (grilla[0][c].estaVacia()) vaciasEnBorde.add(grilla[0][c]);
                break;
            case IZQUIERDA: 
                for (int f = 0; f < 4; f++) if (grilla[f][3].estaVacia()) vaciasEnBorde.add(grilla[f][3]);
                break;
            case DERECHA: 
                for (int f = 0; f < 4; f++) if (grilla[f][0].estaVacia()) vaciasEnBorde.add(grilla[f][0]);
                break;
        }
        ubicarFicha(vaciasEnBorde);
    }

    private void ubicarFicha(List<Casilla> opciones) {
        if (!opciones.isEmpty()) {
            Casilla elegida = opciones.get(random.nextInt(opciones.size()));
            int valor = random.nextInt(2) + 1;
            Ficha nueva = (valor == 1 || valor == 2) ? new Ficha(valor) : new Ficha(3);
            elegida.setFicha(nueva);
        }
    }
}
