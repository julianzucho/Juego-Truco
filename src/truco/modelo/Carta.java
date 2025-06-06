package truco.modelo;

public abstract class Carta implements Comparable<Carta> {
    private int valor;
    private String palo;
    private int peso;

    public Carta(int valor, String palo, int peso) {
        this.valor = valor;
        this.palo = palo;
        this.peso = peso;
    }

    public abstract int getValorEnvido();
    public abstract int getValorFlor();

    @Override
    public int compareTo(Carta otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    // Getters
    public int getValor() { return valor; }
    public String getPalo() { return palo; }
    public int getPeso() { return peso; }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}