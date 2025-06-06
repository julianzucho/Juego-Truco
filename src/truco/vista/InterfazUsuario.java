package truco.vista;

import truco.modelo.Jugador;
import java.util.List;

public interface InterfazUsuario {
    void mostrarMensaje(String mensaje);
    int pedirOpcion(String[] opciones);
    void mostrarCartas(Jugador jugador);
    void actualizarEstadoJuego();
    void mostrarGanadorRonda(String ganador);
    void mostrarPuntos(int equipo1, int equipo2);
    void limpiarPantalla();
}