package truco.vista;

import truco.modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class InterfazGrafica extends JFrame implements InterfazUsuario {
    private JTextArea areaTexto;
    private JPanel panelCartas;
    private JPanel panelOpciones;
    private int opcionSeleccionada = -1;
    private int cartaSeleccionada = -1;

    public InterfazGrafica() {
        setTitle("Truco Argentino");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Área de texto para mensajes
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 16));
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        // Panel para cartas
        panelCartas = new JPanel(new FlowLayout());
        add(panelCartas, BorderLayout.NORTH);

        // Panel para opciones
        panelOpciones = new JPanel(new GridLayout(0, 1));
        add(panelOpciones, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        areaTexto.append(mensaje + "\n");
    }

    @Override
    public int pedirOpcion(String[] opciones) {
        panelOpciones.removeAll();
        opcionSeleccionada = -1;

        for (int i = 0; i < opciones.length; i++) {
            final int index = i;
            JButton btn = new JButton(opciones[i]);
            btn.addActionListener(e -> {
                opcionSeleccionada = index;
                synchronized (this) {
                    notify();
                }
            });
            panelOpciones.add(btn);
        }

        panelOpciones.revalidate();
        panelOpciones.repaint();

        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return opcionSeleccionada;
    }

    @Override
    public void mostrarCartas(Jugador jugador) {
        panelCartas.removeAll();
        cartaSeleccionada = -1;

        List<Carta> mano = jugador.getMano();
        for (int i = 0; i < mano.size(); i++) {
            final int index = i;
            JButton btnCarta = new JButton(mano.get(i).toString());
            btnCarta.setPreferredSize(new Dimension(120, 180));
            btnCarta.addActionListener(e -> {
                cartaSeleccionada = index;
                synchronized (this) {
                    notify();
                }
            });
            panelCartas.add(btnCarta);
        }

        panelCartas.revalidate();
        panelCartas.repaint();
    }

    public int pedirCartaAJugar() {
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return cartaSeleccionada;
    }

    @Override
    public void actualizarEstadoJuego() {
        // Implementar actualización visual del estado
    }

    @Override
    public void mostrarGanadorRonda(String ganador) {
        JOptionPane.showMessageDialog(this, "¡" + ganador + " gana la ronda!");
    }

    @Override
    public void mostrarPuntos(int equipo1, int equipo2) {
        areaTexto.append("\nPuntuación:\nEquipo 1: " + equipo1 + "\nEquipo 2: " + equipo2 + "\n");
    }

    @Override
    public void limpiarPantalla() {
        areaTexto.setText("");
        panelCartas.removeAll();
        panelOpciones.removeAll();
        panelCartas.revalidate();
        panelOpciones.revalidate();
    }
}