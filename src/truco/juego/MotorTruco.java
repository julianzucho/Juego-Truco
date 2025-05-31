package truco.juego;

import truco.modelo.*;
import truco.ui.PanelJuego;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class MotorTruco {
    private JugadorHumano jugador;
    private JugadorIA ia;
    private Mazo mazo;
    private PanelJuego panel;
    private List<Carta> jugadasJugador;
    private List<Carta> jugadasIA;
    private int manosGanadasJugador;
    private int manosGanadasIA;
    private boolean turnoJugador;
    private boolean jugadorEsMano = true;
    private boolean envidoCantado = false;
    private boolean trucoCantado = false;
    private int nivelTruco = 1; // 1: Truco, 2: Re Truco, 3: Vale Cuatro
    private boolean esperandoRespuestaTruco = false;

    public MotorTruco(PanelJuego panel) {
        this.panel = panel;
        this.panel.setMotor(this);
        this.jugador = new JugadorHumano("Jugador");
        this.ia = new JugadorIA("IA");
        this.mazo = new Mazo();
        iniciarNuevaRonda();
    }

    public void iniciarNuevaRonda() {
        mazo.reiniciar();
        jugador.recibirCartas(mazo.repartir(3));
        ia.recibirCartas(mazo.repartir(3));

        jugadasJugador = new ArrayList<>();
        jugadasIA = new ArrayList<>();
        manosGanadasJugador = 0;
        manosGanadasIA = 0;
        envidoCantado = false;
        trucoCantado = false;
        nivelTruco = 1;
        esperandoRespuestaTruco = false;

        panel.limpiarHistorial();
        panel.mostrarCartasJugador(jugador.getCartas());
        panel.actualizarPuntajes(jugador.getPuntos(), ia.getPuntos());

        turnoJugador = jugadorEsMano;
        jugadorEsMano = !jugadorEsMano;
        panel.actualizarEstado((turnoJugador ? "Tu turno." : "Turno de IA..."));

        if (!turnoJugador) {
            jugarTurnoIA();
        }
    }

    public void jugarCarta(Carta carta) {
        if (!turnoJugador || esperandoRespuestaTruco) return;

        //jugador.removerCarta(carta);
        jugadasJugador.add(carta);

        panel.animarCartaJugador(carta);

        Timer timer = new Timer(1000, e -> {
            Carta cartaIA = ia.jugarCarta();
            jugadasIA.add(cartaIA);
            panel.animarCartaIA(cartaIA);

            int resultado = carta.comparar(cartaIA);
            if (resultado > 0) manosGanadasJugador++;
            else if (resultado < 0) manosGanadasIA++;

            if (manosGanadasJugador == 2 || manosGanadasIA == 2 || jugadasJugador.size() == 3) {
                terminarRonda();
            } else {
                turnoJugador = resultado >= 0; // Gana o empata = mantiene turno
                panel.actualizarEstado((turnoJugador ? "Tu turno." : "Turno de IA..."));
                if (!turnoJugador) jugarTurnoIA();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void jugarTurnoIA() {
        Timer timer = new Timer(1000, e -> {
            Carta cartaIA = ia.jugarCarta();
            jugadasIA.add(cartaIA);
            panel.animarCartaIA(cartaIA);
            turnoJugador = true;
            panel.actualizarEstado("Tu turno.");
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void cantarEnvido() {
        if (envidoCantado) return;
        envidoCantado = true;
        int puntosJugador = jugador.cantarTantos();
        int puntosIA = ia.cantarTantos();

        panel.actualizarEstado("Cantaste Envido. IA responde...");

        Timer timer = new Timer(1000, e -> {
            boolean acepta = new Random().nextBoolean();
            if (acepta) {
                panel.actualizarEstado("IA aceptó. Envido: Jugador: " + puntosJugador + " | IA: " + puntosIA);
                if (puntosJugador > puntosIA) jugador.sumarPuntos(2);
                else ia.sumarPuntos(2);
            } else {
                panel.actualizarEstado("IA no quiso. Ganás 1 punto.");
                jugador.sumarPuntos(1);
            }
            panel.actualizarPuntajes(jugador.getPuntos(), ia.getPuntos());
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void cantarTruco() {
        if (esperandoRespuestaTruco) return;

        if (!trucoCantado) {
            trucoCantado = true;
            esperandoRespuestaTruco = true;
            nivelTruco = 1;
            panel.actualizarEstado("Cantaste Truco. IA responde...");
        } else if (nivelTruco < 3) {
            esperandoRespuestaTruco = true;
            nivelTruco++;
            panel.actualizarEstado((nivelTruco == 2 ? "Re Truco" : "Vale Cuatro") + ". IA responde...");
        }

        Timer timer = new Timer(1000, e -> {
            boolean acepta = new Random().nextBoolean();
            if (acepta) {
                panel.actualizarEstado("IA aceptó el " + getNombreTruco() + ". Seguimos...");
                esperandoRespuestaTruco = false;
            } else {
                panel.actualizarEstado("IA no aceptó el " + getNombreTruco() + ". Ganás " + (nivelTruco - 1) + " puntos.");
                jugador.sumarPuntos(nivelTruco - 1);
                panel.actualizarPuntajes(jugador.getPuntos(), ia.getPuntos());
                esperarYNuevaRonda();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void terminarRonda() {
        int puntosRonda = trucoCantado ? nivelTruco : 1;
        if (manosGanadasJugador > manosGanadasIA) {
            jugador.sumarPuntos(puntosRonda);
            panel.actualizarEstado("Ganaste la ronda. +" + puntosRonda + " puntos");
        } else {
            ia.sumarPuntos(puntosRonda);
            panel.actualizarEstado("Ganó la IA la ronda. +" + puntosRonda + " puntos");
        }
        panel.actualizarPuntajes(jugador.getPuntos(), ia.getPuntos());
        esperarYNuevaRonda();
    }

    private void esperarYNuevaRonda() {
        Timer timer = new Timer(3000, e -> iniciarNuevaRonda());
        timer.setRepeats(false);
        timer.start();
    }

    private String getNombreTruco() {
        return switch (nivelTruco) {
            case 1 -> "Truco";
            case 2 -> "Re Truco";
            case 3 -> "Vale Cuatro";
            default -> "";
        };
    }
}