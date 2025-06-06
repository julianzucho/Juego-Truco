package truco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private Partida partida;
    private List<Carta> cartasJugadas;
    private boolean terminada;

    public Mano(Partida partida) {
        this.partida = partida;
        this.cartasJugadas = new ArrayList<>();
        this.terminada = false;
    }

    public void jugar() {
        while (!terminada) {
            Jugador jugadorActual = partida.getJugadorEnTurno();
            Carta cartaJugada = jugadorActual.jugarCarta(obtenerDecision(jugadorActual));
            cartasJugadas.add(cartaJugada);

            // Verificar si la mano ha terminado
            verificarFinMano();
        }
    }

    private int obtenerDecision(Jugador jugador) {
        if (jugador instanceof JugadorHumano) {
            return ((JugadorHumano) jugador).pedirCartaAJugar();
        } else {
            return ((JugadorIA) jugador).decidirCartaAJugar();
        }
    }

    private void verificarFinMano() {
        // Lógica para determinar si la mano ha terminado
    }

    public boolean terminada() {
        return terminada;
    }

    public Jugador getGanador() {
        // Lógica para determinar ganador de la mano
        return null; // Implementar lógica real
    }
}