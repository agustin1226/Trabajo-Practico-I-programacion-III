package logica;

public class Ficha {
    
    private int valor;

    public Ficha(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    // Verifica si esta ficha puede chocar y fusionarse con otra
    public boolean puedeFusionarseCon(Ficha otra) {
        if (otra == null) {
            return false;
        }

        int valorOtra = otra.getValor();

        // Regla 1: El 1 se fusiona con el 2 (y viceversa)
        if ((this.valor == 1 && valorOtra == 2) || (this.valor == 2 && valorOtra == 1)) {
            return true;
        }

        // Regla 2: De 3 en adelante, solo se fusionan si son idénticas
        if (this.valor >= 3 && this.valor == valorOtra) {
            return true;
        }

        return false; // Cualquier otro caso, no se fusiona
    }

    // Ejecuta la fusión y devuelve una nueva ficha con el valor sumado
    public Ficha fusionar(Ficha otra) {
        if (puedeFusionarseCon(otra)) {
            return new Ficha(this.valor + otra.getValor());
        }
        return this; // Si por algún error de lógica intentan fusionar algo inválido, devolvemos la misma ficha
    }
}