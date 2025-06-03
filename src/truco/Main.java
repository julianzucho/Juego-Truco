package truco;

import truco.controlador.JuegoController;
import truco.vista.InterfazConsola;
import truco.vista.InterfazUsuario;

public class Main {
    public static void main(String[] args) {
        InterfazUsuario interfaz = new InterfazConsola();
        JuegoController controller = new JuegoController(interfaz);
        controller.iniciarJuego();
    }
}