package truco.vista;
import truco.modelo.Carta;
import truco.modelo.Jugador;
import java.util.Scanner;
import java.util.List;

public class InterfazConsola implements InterfazUsuario {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println("\n=== " + mensaje + " ===\n");
    }

    @Override
    public int pedirOpcion(String[] opciones) {
        System.out.println("\nOpciones disponibles:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i+1) + ". " + opciones[i]);
        }

        while (true) {
            try {
                System.out.print("\nSeleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine()) - 1;
                if (opcion >= 0 && opcion < opciones.length) {
                    return opcion;
                }
                System.out.println("Opción inválida. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }

    @Override
    public void mostrarCartas(Jugador jugador) {
        System.out.println("\n--- Tus cartas ---");
        List<Carta> mano = jugador.getMano();
        for (int i = 0; i < mano.size(); i++) {
            System.out.println((i+1) + ". " + mano.get(i));
        }
        System.out.println("------------------");
    }

    @Override
    public void actualizarEstadoJuego() {
        System.out.println("\nActualizando estado del juego...");
    }

    @Override
    public void mostrarGanadorRonda(String ganador) {
        System.out.println("\n¡¡ " + ganador + " gana la ronda !!");
    }

    @Override
    public void mostrarPuntos(int equipo1, int equipo2) {
        System.out.println("\nPuntuación actual:");
        System.out.println("Equipo 1: " + equipo1 + " puntos");
        System.out.println("Equipo 2: " + equipo2 + " puntos");
    }

    @Override
    public void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public int pedirCartaAJugar() {
        while (true) {
            try {
                System.out.print("Seleccione carta a jugar (1-3): ");
                int carta = Integer.parseInt(scanner.nextLine()) - 1;
                if (carta >= 0 && carta <= 2) {
                    return carta;
                }
                System.out.println("Número de carta inválido. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entre 1 y 3.");
            }
        }
    }
}