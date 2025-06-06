package truco.controlador;
import truco.modelo.Equipo;
import truco.modelo.*;
import truco.vista.InterfazUsuario;

public class JuegoController {
    private Partida partida;
    private InterfazUsuario interfaz;

    public JuegoController(InterfazUsuario interfaz) {
        this.interfaz = interfaz;
    }

    public void iniciarJuego() {
        ConfiguracionJuego config = obtenerConfiguracion();
        partida = new Partida(config);

        while (!partida.terminada()) {
            partida.iniciarNuevaRonda();
            partida.jugarRondaActual();
            mostrarResultadoRonda();
        }

        mostrarGanadorPartida();
    }

    private ConfiguracionJuego obtenerConfiguracion() {
        // Mostrar menú para seleccionar modo de juego
        String[] opcionesModo = {"Individual", "Equipos"};
        int opcionModo = interfaz.pedirOpcion(opcionesModo);

        ModoJuego modo = (opcionModo == 0) ? ModoJuego.INDIVIDUAL : ModoJuego.EQUIPOS;

        // Pedir nombres de jugadores según el modo
        String[] nombres = pedirNombresJugadores(modo);

        return new ConfiguracionJuego(modo, nombres.length, nombres, 30);
    }

    private String[] pedirNombresJugadores(ModoJuego modo) {
        if (modo == ModoJuego.INDIVIDUAL) {
            return new String[]{"Jugador 1", "Jugador 2"};
        } else {
            return new String[]{"Jugador 1 (Equipo A)", "Jugador 2 (Equipo B)",
                    "Jugador 3 (Equipo A)", "Jugador 4 (Equipo B)"};
        }
    }

    private void mostrarResultadoRonda() {
        Ronda ronda = partida.getRondaActual();
        interfaz.mostrarMensaje("Resultado de la ronda:");
        interfaz.mostrarMensaje("Ganador: " + ronda.getGanador().getNombre());
        interfaz.mostrarPuntos(partida.getPuntosEquipo1(), partida.getPuntosEquipo2());
    }

    private void mostrarGanadorPartida() {
        String ganador = partida.getGanador().getNombre();
        interfaz.mostrarMensaje("¡¡ " + ganador + " gana la partida !!");
    }
}