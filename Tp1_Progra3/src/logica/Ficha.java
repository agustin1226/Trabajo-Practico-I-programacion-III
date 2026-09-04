package logica;

public class Ficha {
    
    private int valor;

    public Ficha(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    // Verifica si esta ficha puedefusionarse con otra
    public boolean puedeFusionarseCon(Ficha otra) {
        boolean puede = false;

        if (otra != null) {
            int valorOtra = otra.getValor();

            // Regla 1: El 1 se fusiona con el 2 (y viceversa)
            if ((this.valor == 1 && valorOtra == 2) || (this.valor == 2 && valorOtra == 1)) {
                puede = true;
            }
            // Regla 2: De 3 en adelante, solo se fusionan si son iguales
            else if (this.valor >= 3 && this.valor == valorOtra) {
                puede = true;
            }
        }

        return puede; 
    }

    public Ficha fusionar(Ficha otra) {
        Ficha resultado = this;

        if (puedeFusionarseCon(otra)) {
            resultado = new Ficha(this.valor + otra.getValor());
        }
        
        return resultado;
    }
}