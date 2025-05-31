package truco.modelo;
import java.util.List;
import java.util.Scanner;

public class JugadorHumano extends Jugador {
    private Scanner scanner;

    public JugadorHumano(String nombre) {
        super(nombre);
        scanner = new Scanner(System.in);
    }

    @Override
    public Carta jugarCarta() {
        System.out.println("\nTus cartas:");
        for (int i = 0; i < cartas.size(); i++) {
            System.out.println((i + 1) + ") " + cartas.get(i));
        }
        int eleccion = -1;
        do {
            System.out.print("Elegí una carta para jugar (1-" + cartas.size() + "): ");
            eleccion = scanner.nextInt() - 1;
        } while (eleccion < 0 || eleccion >= cartas.size());

        return cartas.remove(eleccion);
    }

    @Override
    public String elegirAccion() {
        System.out.println("\nAcciones disponibles:");
        System.out.println("1) Cantar Truco");
        System.out.println("2) Cantar Envido");
        System.out.println("3) Pasar / Jugar carta sin cantar");
        System.out.print("Elegí una opción: ");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1: return "TRUCO";
            case 2: return "ENVIDO";
            default: return "NINGUNA";
        }
    }


    @Override
    public int cantarTantos() {
        List<Carta> cartas = getCartas();
        int max = 0;
        for (int i = 0; i < cartas.size(); i++) {
            for (int j = i + 1; j < cartas.size(); j++) {
                Carta c1 = cartas.get(i);
                Carta c2 = cartas.get(j);
                if (c1.getPalo().equals(c2.getPalo())) {
                    int valor = c1.getValorEnvido() + c2.getValorEnvido() + 20;
                    if (valor > max) max = valor;
                }
            }
        }
        return (max > 0) ? max : cartas.stream().mapToInt(Carta::getValorEnvido).max().orElse(0);
    }

}