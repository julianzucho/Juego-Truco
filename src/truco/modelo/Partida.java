package truco.modelo;

import java.util.ArrayList;

public class Partida {
    private ArrayList<Jugador> jugadores;
    private Deck deck;
    private Ronda rondaActual;
    private int puntosEquipo1;
    private int puntosEquipo2;
    private final int PUNTOS_GANADOR = 30;

    public Partida() {
        this.jugadores = new ArrayList<>();
        this.deck = new Deck();
        inicializarPartida();
    }

    private void inicializarPartida() {
        Equipo equipo1 = new Equipo("Equipo 1");
        Equipo equipo2 = new Equipo("Equipo 2");

        jugadores.add(new Jugador("Jugador 1", equipo1));
        jugadores.add(new Jugador("Jugador 2", equipo2));
        jugadores.add(new Jugador("Jugador 3", equipo1));
        jugadores.add(new Jugador("Jugador 4", equipo2));
    }

    public void nuevaRonda() {
        deck.mezclar();
        repartirCartas();
        this.rondaActual = new Ronda(this);
    }

    private void repartirCartas() {
        for (Jugador jugador : jugadores) {
            jugador.limpiarMano();
            for (int i = 0; i < 3; i++) {
                jugador.recibirCarta(deck.repartir());
            }
        }
    }

    public boolean isPartidaTerminada() {
        return puntosEquipo1 >= PUNTOS_GANADOR || puntosEquipo2 >= PUNTOS_GANADOR;
    }

    // Getters y setters
    public ArrayList<Jugador> getJugadores() { return jugadores; }
    public Ronda getRondaActual() { return rondaActual; }
    public int getPuntosEquipo1() { return puntosEquipo1; }
    public int getPuntosEquipo2() { return puntosEquipo2; }
    public void sumarPuntosEquipo1(int puntos) { puntosEquipo1 += puntos; }
    public void sumarPuntosEquipo2(int puntos) { puntosEquipo2 += puntos; }
}