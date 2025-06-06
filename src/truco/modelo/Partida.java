package truco.modelo;
import truco.modelo.Equipo;
import java.util.ArrayList;
import java.util.List;

public class Partida {
    private ConfiguracionJuego config;
    private List<Jugador> jugadores;
    private List<Equipo> equipos;
    private Deck deck;
    private Ronda rondaActual;
    private int puntosEquipo1;
    private int puntosEquipo2;

    public Partida(ConfiguracionJuego config) {
        this.config = config;
        this.deck = new Deck();
        inicializarJugadores();
    }

    private void inicializarJugadores() {
        jugadores = new ArrayList<>();

        if (config.getModo() == ModoJuego.INDIVIDUAL) {
            for (String nombre : config.getNombresJugadores()) {
                jugadores.add(new JugadorHumano(nombre, null));
            }
        } else {
            equipos = new ArrayList<>();
            equipos.add(new Equipo("Equipo A"));
            equipos.add(new Equipo("Equipo B"));

            for (int i = 0; i < config.getNombresJugadores().length; i++) {
                Equipo equipo = equipos.get(i % 2);
                Jugador jugador;

                if (i < 2) { // Primeros dos jugadores son humanos
                    jugador = new JugadorHumano(config.getNombresJugadores()[i], equipo);
                } else { // Los siguientes son IA
                    jugador = new JugadorIA(config.getNombresJugadores()[i], equipo);
                }

                jugadores.add(jugador);
                equipo.agregarJugador(jugador);  // Añadir jugador al equipo
            }
        }
    }

    public void iniciarNuevaRonda() {
        deck.mezclar();
        repartirCartas();
        this.rondaActual = new Ronda(this);
    }

    public void jugarRondaActual() {
        rondaActual.jugar();
    }

    private void repartirCartas() {
        for (Jugador jugador : jugadores) {
            jugador.limpiarMano();
            for (int i = 0; i < 3; i++) {
                jugador.recibirCarta(deck.repartir());
            }
        }
    }

    public boolean terminada() {
        return puntosEquipo1 >= config.getPuntosParaGanar() ||
                puntosEquipo2 >= config.getPuntosParaGanar();
    }


    // Getters
    public List<Jugador> getJugadoresEnOrden() {
        return jugadores;
    }
    public Ronda getRondaActual() {
        return rondaActual;
    }
    public int getPuntosEquipo1() {
        return puntosEquipo1;
    }
    public int getPuntosEquipo2() {
        return puntosEquipo2;
    }
    public boolean hayGanador() {
        return puntosEquipo1 >= config.getPuntosParaGanar() ||
                puntosEquipo2 >= config.getPuntosParaGanar();
    }

    public Equipo getEquipoGanador() {
        if (!terminada()) return null;

        return puntosEquipo1 > puntosEquipo2 ? equipos.get(0) : equipos.get(1);
    }
    public Jugador getGanador() {
        if (!terminada()) return null;

        if (config.getModo() == ModoJuego.INDIVIDUAL) {
            return puntosEquipo1 > puntosEquipo2 ? jugadores.get(0) : jugadores.get(1);
        } else {
            // Obtener el equipo ganador y devolver su primer jugador
            Equipo equipoGanador = puntosEquipo1 > puntosEquipo2 ? equipos.get(0) : equipos.get(1);
            if (!equipoGanador.getJugadores().isEmpty()) {
                return equipoGanador.getJugadores().get(0);
            }
            return null;
        }
    }


}