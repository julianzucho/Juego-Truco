package truco.modelo;

import java.util.ArrayList;

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
        return mano.remove(indice);
    }

    public void limpiarMano() {
        mano.clear();
    }

    // Getters y setters
    public ArrayList<Carta> getMano() { return mano; }
    public String getNombre() { return nombre; }
    public Equipo getEquipo() { return equipo; }
    public int getPuntosEnvido() { return puntosEnvido; }
    public void setPuntosEnvido(int puntos) { this.puntosEnvido = puntos; }
    public int getPuntosFlor() { return puntosFlor; }
    public void setPuntosFlor(int puntos) { this.puntosFlor = puntos; }
}