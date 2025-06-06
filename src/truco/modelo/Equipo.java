package truco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private int puntos;
    private List<Jugador> jugadores;  // Lista de jugadores del equipo

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.jugadores = new ArrayList<>();
    }

    // Método para agregar jugadores al equipo
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    // Método getJugadores() que faltaba
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    // Resto de métodos existentes
    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }
}