package truco.controlador;

import truco.modelo.*;
import truco.vista.InterfazUsuario;

public class JuegoController {
    private Partida partida;
    private InterfazUsuario interfaz;

    public JuegoController(InterfazUsuario interfaz) {
        this.interfaz = interfaz;
        this.partida = new Partida();
    }

    public void iniciarJuego() {
        interfaz.mostrarMensaje("¡Bienvenido al Truco Argentino!");

        while (!partida.isPartidaTerminada()) {
            partida.nuevaRonda();
            jugarRonda();
        }

        mostrarGanador();
    }

    private void jugarRonda() {
        Ronda ronda = partida.getRondaActual();

        while (!ronda.isTerminada()) {
            Jugador jugadorActual = ronda.getJugadorActual();
            interfaz.mostrarMensaje("\nTurno de: " + jugadorActual.getNombre());

            if (jugadorActual.equals(partida.getJugadores().get(0))) { // Jugador humano
                interfaz.mostrarCartas(jugadorActual);
                int opcion = interfaz.pedirOpcion(new String[]{"Jugar carta", "Cantar truco", "Ir al mazo"});
                // Lógica para manejar la opción seleccionada
            } else {
                // Lógica para IA
            }

            ronda.siguienteTurno();
        }

        // Sumar puntos al equipo ganador
    }

    private void mostrarGanador() {
        String mensaje = partida.getPuntosEquipo1() > partida.getPuntosEquipo2() ?
                "¡Equipo 1 gana la partida!" : "¡Equipo 2 gana la partida!";
        interfaz.mostrarMensaje(mensaje);
    }
}