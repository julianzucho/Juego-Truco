package truco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private ArrayList<Carta> mano;
    private Equipo equipo;
    private int puntosEnvido;
    private int puntosFlor;

    public Jugador(String nombre, Equipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.mano = new ArrayList<>();
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public Carta jugarCarta(int indice) {
        if (indice < 0 || indice >= mano.size()) {
            throw new IllegalArgumentException("Índice de carta inválido");
        }
        return mano.remove(indice);
    }

    public void limpiarMano() {
        mano.clear();
    }

    // Getters
    public List<Carta> getMano() { return mano; }
    public String getNombre() { return nombre; }
    public Equipo getEquipo() { return equipo; }
    public int getPuntosEnvido() { return puntosEnvido; }
    public int getPuntosFlor() { return puntosFlor; }

    // Setters
    public void setPuntosEnvido(int puntos) { this.puntosEnvido = puntos; }
    public void setPuntosFlor(int puntos) { this.puntosFlor = puntos; }
}