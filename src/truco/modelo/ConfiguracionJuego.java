package truco.modelo;

public class ConfiguracionJuego {
    private ModoJuego modo;
    private int cantidadJugadores;
    private String[] nombresJugadores;
    private int puntosParaGanar;

    public ConfiguracionJuego(ModoJuego modo, int cantidadJugadores, String[] nombresJugadores, int puntosParaGanar) {
        this.modo = modo;
        this.cantidadJugadores = cantidadJugadores;
        this.nombresJugadores = nombresJugadores;
        this.puntosParaGanar = puntosParaGanar;
    }

    // Getters
    public ModoJuego getModo() { return modo; }
    public int getCantidadJugadores() { return cantidadJugadores; }
    public String[] getNombresJugadores() { return nombresJugadores; }
    public int getPuntosParaGanar() { return puntosParaGanar; }
}