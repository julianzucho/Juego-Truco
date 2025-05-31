package truco.modelo;
import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {
    protected String nombre;
    protected List<Carta> cartas = new ArrayList<>();
    protected int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public abstract Carta jugarCarta();

    public abstract String elegirAccion();

    public abstract int cantarTantos();
    public List<Carta> getCartas() { return cartas; }

    public void recibirCartas(List<Carta> nuevas) {
        cartas.clear();
        cartas.addAll(nuevas);
    }

    public void sumarPuntos(int puntos) { this.puntos += puntos; }
    public int getPuntos() { return puntos; }
    public String getNombre() { return nombre; }
}