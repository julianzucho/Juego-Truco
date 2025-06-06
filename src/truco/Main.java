package truco;

import truco.controlador.JuegoController;

import truco.vista.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        InterfazUsuario interfaz = seleccionarInterfaz();
        JuegoController juego = new JuegoController(interfaz);
        juego.iniciarJuego();
    }

    private static InterfazUsuario seleccionarInterfaz() {
        String[] opciones = {"Interfaz de Consola", "Interfaz Gráfica"};
        int seleccion = JOptionPane.showOptionDialog(null,
                "Seleccione el tipo de interfaz:",
                "Truco Argentino",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        return (seleccion == 0) ? new InterfazConsola() : new InterfazGrafica();
    }
}