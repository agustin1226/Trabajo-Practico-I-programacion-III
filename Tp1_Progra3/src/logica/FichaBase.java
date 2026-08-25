package logica;

public class FichaBase extends Ficha {

    public FichaBase(int valor) {
        super(valor);
        if (valor != 1 && valor != 2) {
            throw new IllegalArgumentException("La FichaBase solo puede valer 1 o 2");
        }
    }

    @Override
    public boolean puedeFusionarseCon(Ficha otra) {
        if (otra instanceof FichaBase) {
            // Un 1 solo se fusiona con un 2, y viceversa
            return this.valor + otra.getValor() == 3;
        }
        return false;
    }

    @Override
    public Ficha fusionarCon(Ficha otra) {
        if (puedeFusionarseCon(otra)) {
            // Al fusionarse, generan el primer múltiplo de 3
            return new FichaMultiplo(3);
        }
        return null; 
    }

    @Override
    public int calcularPuntaje() {
        return 0; // no tienen valor
    }
}
