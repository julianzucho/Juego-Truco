package truco.modelo;

public class CartaEspaniola extends Carta {
    public CartaEspaniola(int valor, String palo, int peso) {
        super(valor, palo, peso);
    }

    @Override
    public int getValorEnvido() {
        return (getValor() < 10) ? getValor() : 0;
    }

    @Override
    public int getValorFlor() {
        return (getValor() < 10) ? getValor() : 0;
    }

    @Override
    public String toString() {
        return getValor() + " de " + getPalo();
    }
}