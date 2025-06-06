package truco.modelo;

import java.util.List;

public class JugadorIA extends Jugador {
    public JugadorIA(String nombre, Equipo equipo) {
        super(nombre, equipo);
    }

    public int decidirCartaAJugar() {
        // Lógica simple de IA: juega la carta más alta
        List<Carta> mano = getMano();
        int mejorCartaIndex = 0;
        for (int i = 1; i < mano.size(); i++) {
            if (mano.get(i).compareTo(mano.get(mejorCartaIndex)) > 0) {
                mejorCartaIndex = i;
            }
        }
        return mejorCartaIndex;
    }
}