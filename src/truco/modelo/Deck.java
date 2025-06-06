package truco.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Carta> cartas;

    public Deck() {
        cartas = new ArrayList<>();
        inicializarMazo();
    }

    private void inicializarMazo() {
        String[] palos = {"Espada", "Basto", "Oro", "Copa"};
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};
        int[] pesos = {14, 9, 10, 1, 2, 3, 11, 5, 6, 7};

        for (String palo : palos) {
            for (int i = 0; i < valores.length; i++) {
                cartas.add(new CartaEspaniola(valores[i], palo, pesos[i]));
            }
        }
    }

    public void mezclar() {
        Collections.shuffle(cartas);
    }

    public Carta repartir() {
        if (cartas.isEmpty()) return null;
        return cartas.remove(0);
    }
}