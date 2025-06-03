package truco.modelo;

public class ConfiguracionPartida {
    private int cantidadJugadores;
    private boolean incluirIA;

    public ConfiguracionPartida(int cantidadJugadores, boolean incluirIA) {
        this.cantidadJugadores = cantidadJugadores;
        this.incluirIA = incluirIA;
    }

    // Getters
    public int getCantidadJugadores() { return cantidadJugadores; }
    public boolean isIncluirIA() { return incluirIA; }
}