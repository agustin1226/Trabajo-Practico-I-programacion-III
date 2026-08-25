package logica;

public class FichaMultiplo extends Ficha {

    public FichaMultiplo(int valor) {
        super(valor);
        if (valor < 3 || valor % 3 != 0) {
            throw new IllegalArgumentException("Debe ser 3 o múltiplo");
        }
    }

    @Override
    public boolean puedeFusionarseCon(Ficha otra) {
        if (otra instanceof FichaMultiplo) {
            // Solo se combinan si son exactamente iguales
            return this.valor == otra.getValor();
        }
        return false;
    }

    @Override
    public Ficha fusionarCon(Ficha otra) {
        if (puedeFusionarseCon(otra)) {
            return new FichaMultiplo(this.valor + otra.getValor());
        }
        return null; 
    }

    @Override
    public int calcularPuntaje() {
        // Fórmula exponencial
        int exponente = (int) (Math.log(this.valor / 3) / Math.log(2));
        return (int) Math.pow(3, exponente + 1);
    }
}
