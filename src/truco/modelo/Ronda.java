package truco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private Partida partida;
    private List<Mano> manos;
    private int manoActual;
    private List<Jugador> ordenTurnos;
    private int turnoActual;

    public Ronda(Partida partida) {
        this.partida = partida;
        this.manos = new ArrayList<>();
        this.manoActual = 0;
        inicializarRonda();
    }

    private void inicializarRonda() {
        // Crear 3 manos para la ronda
        for (int i = 0; i < 3; i++) {
            manos.add(new Mano(partida));
        }

        // Establecer orden de turnos
        this.ordenTurnos = partida.getJugadoresEnOrden();
        this.turnoActual = 0;
    }


    private void asignarPuntosMano(Jugador ganador) {
        // Lógica para asignar puntos por mano ganada
    }

    private void asignarPuntosRonda() {
        // Lógica para asignar puntos por ronda ganada
    }

    public Jugador getGanador() {
        // Determinar ganador de la ronda basado en manos ganadas
        return null; // Implementar lógica real
    }
    private boolean rondaTerminada() {
        // La ronda termina cuando se completan 3 manos o hay un ganador de partida
        return manoActual >= 3 || partida.hayGanador();
    }
    public void jugar() {
        while (!rondaTerminada()) {
            Mano mano = manos.get(manoActual);
            mano.jugar();

            if (mano.terminada()) {
                manoActual++;
                asignarPuntosMano(mano.getGanador());

                // Mostrar resultado de la mano
                mostrarResultadoMano(mano);
            }
        }

        if (!partida.hayGanador()) {
            asignarPuntosRonda();
        }
    }

    private void mostrarResultadoMano(Mano mano) {
        System.out.println("Mano ganada por: " + mano.getGanador().getNombre());
    }
}