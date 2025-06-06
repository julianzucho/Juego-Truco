package truco.modelo;

public class JugadorHumano extends Jugador {
    public JugadorHumano(String nombre, Equipo equipo) {
        super(nombre, equipo);
    }

    public int pedirCartaAJugar() {
        // Esta implementación debería interactuar con la interfaz de usuario
        // Por ahora devolvemos un valor por defecto
        return 0;
    }
}