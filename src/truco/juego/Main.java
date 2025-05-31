package truco.juego;

import javax.swing.SwingUtilities;
import truco.ui.VentanaTruco;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaTruco ventana = new VentanaTruco();
            ventana.setVisible(true);
        });
    }
}