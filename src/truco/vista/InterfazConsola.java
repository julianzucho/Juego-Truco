package truco.vista;

import truco.modelo.*;

import java.util.Scanner;

public class InterfazConsola implements InterfazUsuario {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public int pedirOpcion(String[] opciones) {
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i+1) + ". " + opciones[i]);
        }

        while (true) {
            try {
                System.out.print("Elija una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine()) - 1;
                if (opcion >= 0 && opcion < opciones.length) {
                    return opcion;
                }
                System.out.println("Opción inválida. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número.");
            }
        }
    }

    @Override
    public void mostrarCartas(Jugador jugador) {
        System.out.println("\nTus cartas:");
        ArrayList<Carta> mano = jugador.getMano();
        for (int i = 0; i < mano.size(); i++) {
            System.out.println((i+1) + ". " + mano.get(i));
        }
    }
}