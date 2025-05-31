package truco.ui;

import truco.juego.MotorTruco;

import javax.swing.*;

public class VentanaTruco extends JFrame {
    public VentanaTruco() {
        setTitle("Truco Argentino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        PanelJuego panel = new PanelJuego();
        setContentPane(panel);

        // Crear e iniciar el motor, y vincularlo con el panel
        MotorTruco motor = new MotorTruco(panel);
        panel.setMotor(motor);
        motor.iniciarNuevaRonda();  // Comienza el juego con la primera ronda
    }

}
