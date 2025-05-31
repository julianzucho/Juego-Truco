package truco.ui;

import truco.juego.MotorTruco;
import truco.modelo.Carta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PanelJuego extends JPanel {
    private MotorTruco motor;
    private JLabel estado;
    private JLabel puntajes;
    private JPanel cartasPanel;
    private JPanel historialPanel;
    private JPanel accionesPanel;
    private Carta cartaJugadorAnimada;
    private int xCartaJugador, yCartaJugador;
    private int xDestinoJugador, yDestinoJugador;
    private Timer timerJugador;

    private Carta cartaIAAnimada;
    private int xCartaIA, yCartaIA;
    private int xDestinoIA, yDestinoIA;
    private Timer timerIA;

    private static final int ANCHO_CARTA = 60;
    private static final int ALTO_CARTA = 90;

    private List<Carta> cartasJugador;
    private Carta ultimaCartaIA;

    public PanelJuego() {
        setLayout(new BorderLayout());

        estado = new JLabel("Estado del juego", SwingConstants.CENTER);
        puntajes = new JLabel("Puntajes", SwingConstants.CENTER);
        cartasPanel = new JPanel();
        historialPanel = new JPanel();
        historialPanel.setLayout(new BoxLayout(historialPanel, BoxLayout.Y_AXIS));
        accionesPanel = new JPanel();
        accionesPanel.setLayout(new FlowLayout());

        JButton btnEnvido = new JButton("Cantar Envido");
        btnEnvido.addActionListener(e -> motor.cantarEnvido());
        accionesPanel.add(btnEnvido);

        JButton btnTruco = new JButton("Cantar Truco");
        btnTruco.addActionListener(e -> motor.cantarTruco());
        accionesPanel.add(btnTruco);

        JButton btnNuevaRonda = new JButton("Nueva Ronda");
        btnNuevaRonda.addActionListener(e -> motor.iniciarNuevaRonda());
        accionesPanel.add(btnNuevaRonda);

        add(estado, BorderLayout.NORTH);
        add(puntajes, BorderLayout.SOUTH);
        add(cartasPanel, BorderLayout.CENTER);
        add(new JScrollPane(historialPanel), BorderLayout.EAST);
        add(accionesPanel, BorderLayout.WEST);
    }

    public void setMotor(MotorTruco motor) {
        this.motor = motor;
    }

    public void mostrarCartasJugador(List<Carta> cartas) {
        cartasJugador = new ArrayList<>(cartas);
        cartasPanel.removeAll();
        for (Carta carta : cartasJugador) {
            JButton boton = new JButton(carta.toString());
            boton.addActionListener((ActionEvent e) -> motor.jugarCarta(carta));
            cartasPanel.add(boton);
        }
        revalidate();
        repaint();
    }

    public void mostrarJugadas(Carta cartaJugador, Carta cartaIA) {
        if (cartaJugador != null && cartaIA != null) {
            historialPanel.add(new JLabel("Jugador: " + cartaJugador + " | IA: " + cartaIA));
            ultimaCartaIA = null;
        } else if (cartaIA != null) {
            historialPanel.add(new JLabel("IA: " + cartaIA));
            ultimaCartaIA = cartaIA;
        }
        revalidate();
        repaint();
    }

    public void actualizarEstado(String texto) {
        estado.setText(texto);
    }

    public void actualizarPuntajes(int puntosJugador, int puntosIA) {
        puntajes.setText("Jugador: " + puntosJugador + " | IA: " + puntosIA);
    }

    public void limpiarHistorial() {
        historialPanel.removeAll();
        revalidate();
        repaint();
    }

    public Carta getUltimaCartaIA() {
        return ultimaCartaIA;
    }
    public void animarCartaJugador(Carta carta) {
        if (timerJugador != null && timerJugador.isRunning()) timerJugador.stop();
        this.cartaJugadorAnimada = carta;

        xCartaJugador = getWidth() / 2 - ANCHO_CARTA / 2;
        yCartaJugador = getHeight() - ALTO_CARTA - 10;

        xDestinoJugador = getWidth() / 2 - ANCHO_CARTA - 20;
        yDestinoJugador = getHeight() / 2 - ALTO_CARTA / 2;

        timerJugador = new Timer(15, e -> {
            if (xCartaJugador > xDestinoJugador) xCartaJugador -= 10;
            if (yCartaJugador > yDestinoJugador) yCartaJugador -= 10;

            if (xCartaJugador < xDestinoJugador) xCartaJugador = xDestinoJugador;
            if (yCartaJugador < yDestinoJugador) yCartaJugador = yDestinoJugador;

            repaint();

            if (xCartaJugador == xDestinoJugador && yCartaJugador == yDestinoJugador) {
                timerJugador.stop();
            }
        });
        timerJugador.start();
    }
    public void animarCartaIA(Carta carta) {
        if (timerIA != null && timerIA.isRunning()) timerIA.stop();
        this.cartaIAAnimada = carta;

        xCartaIA = getWidth() / 2 - ANCHO_CARTA / 2;
        yCartaIA = 10;

        xDestinoIA = getWidth() / 2 + 20;
        yDestinoIA = getHeight() / 2 - ALTO_CARTA / 2;

        timerIA = new Timer(15, e -> {
            if (xCartaIA < xDestinoIA) xCartaIA += 10;
            if (yCartaIA < yDestinoIA) yCartaIA += 10;

            if (xCartaIA > xDestinoIA) xCartaIA = xDestinoIA;
            if (yCartaIA > yDestinoIA) yCartaIA = yDestinoIA;

            repaint();

            if (xCartaIA == xDestinoIA && yCartaIA == yDestinoIA) {
                timerIA.stop();
            }
        });
        timerIA.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cartaJugadorAnimada != null) {
            Image img = cartaJugadorAnimada.getImagen();
            g.drawImage(img, xCartaJugador, yCartaJugador, ANCHO_CARTA, ALTO_CARTA, this);
        }
        if (cartaIAAnimada != null) {
            Image img = cartaIAAnimada.getImagen();
            g.drawImage(img, xCartaIA, yCartaIA, ANCHO_CARTA, ALTO_CARTA, this);
        }
    }

}