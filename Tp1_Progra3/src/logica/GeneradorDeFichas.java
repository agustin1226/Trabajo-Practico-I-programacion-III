package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorDeFichas {
    private Random random;

    public GeneradorDeFichas() {
        this.random = new Random();
    }

    // Método para arrancar el juego (busca cualquier casilla vacía y sortea su propio número)
    public void generarFichaAleatoria(Casilla[][] grilla) {
        List<Casilla> vacias = new ArrayList<>();
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 4; c++) {
                if (grilla[f][c].estaVacia()) vacias.add(grilla[f][c]);
            }
        }
        
        // Sorteamos 1, 2 o 3 para las fichas iniciales
        int valorInicial = random.nextInt(3) + 1; 
        ubicarFicha(vacias, valorInicial);
    }

    // Método para cuando el usuario se mueve (recibe el número exacto desde el Tablero)
    public void generarEnBordeOpuesto(Direccion dir, Casilla[][] grilla, int valorFicha) {
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
        
        // Usamos el valor que venía esperando en el cartel
        ubicarFicha(vaciasEnBorde, valorFicha);
    }

    // Método privado que ahora recibe el valor exacto a instanciar
    private void ubicarFicha(List<Casilla> opciones, int valor) {
        if (!opciones.isEmpty()) {
            Casilla elegida = opciones.get(random.nextInt(opciones.size()));
            elegida.setFicha(new Ficha(valor));
        }
    }
}