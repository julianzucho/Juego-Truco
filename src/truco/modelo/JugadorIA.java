package truco.modelo;

import java.util.ArrayList;
import java.util.Random;

public class JugadorIA extends Jugador {
    private Random random = new Random();

    public JugadorIA(String nombre, Equipo equipo) {
        super(nombre, equipo);
    }

    public Carta jugarCartaAleatoria() {
        ArrayList<Carta> mano = getMano();
        int indice = random.nextInt(mano.size());
        return jugarCarta(indice);
    }

    public boolean decidirSiAceptaTruco() {
        return random.nextBoolean();
    }

    public int cantarEnvido() {
        // Lógica simple para decidir si cantar envido
        int puntosPosibles = 0;
        for (Carta carta : getMano()) {
            puntosPosibles += carta.getValorEnvido();
        }
        return (puntosPosibles > 20) ? puntosPosibles : 0;
    }
}