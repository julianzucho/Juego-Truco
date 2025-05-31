package truco.modelo;
import java.util.*;

public class Mazo {
    private List<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<>();
        reiniciar();
    }

    public void reiniciar() {
        cartas.clear();
        String[] palos = {"Espada", "Basto", "Oro", "Copa"};
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};
        for (String palo : palos) {
            for (int valor : valores) {
                cartas.add(new Carta(palo, valor, calcularPoder(valor, palo)));
            }
        }
        Collections.shuffle(cartas);
    }

    public List<Carta> repartir(int cantidad) {
        List<Carta> mano = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            mano.add(cartas.remove(0));
        }
        return mano;
    }

    private int calcularPoder(int valor, String palo) {
        // Simplificación: Espada 1 > Basto 1 > 7 Espada > 7 Oro > resto...
        if (valor == 1 && palo.equals("Espada")) return 14;
        if (valor == 1 && palo.equals("Basto")) return 13;
        if (valor == 7 && palo.equals("Espada")) return 12;
        if (valor == 7 && palo.equals("Oro")) return 11;
        if (valor == 3 ) return 10;
        if (valor == 2 ) return 9;
        if (valor == 1 && palo.equals("Copa") || palo.equals("Oro")) return 8;
        if (valor== 12) return 7;
        if (valor == 11) return 6;
        if (valor == 10) return 5;
        if (valor == 7) return 4;
        if (valor == 6) return 3;
        if (valor == 5) return 2;
        if (valor == 4) return 1;
        return valor; // valores más bajos valen más
    }
}