package logica;

public abstract class Ficha {
    protected int valor;

    public Ficha(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    // El tablero va a llamar a estos métodos sin importarle qué tipo de ficha es
    public abstract boolean puedeFusionarseCon(Ficha otra);
    public abstract Ficha fusionarCon(Ficha otra);
    public abstract int calcularPuntaje();
}