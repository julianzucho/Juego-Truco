package truco.modelo;
import java.util.List;
import java.util.Random;

public class JugadorIA extends Jugador {
    private Random random;

    public JugadorIA(String nombre) {
        super(nombre);
        random = new Random();
    }

    @Override
    public Carta jugarCarta() {
        // Elige la carta con mayor poder de Truco
        Carta mejorCarta = cartas.get(0);
        for (Carta c : cartas) {
            if (c.getPoderTruco() > mejorCarta.getPoderTruco()) {
                mejorCarta = c;
            }
        }
        cartas.remove(mejorCarta);
        System.out.println(nombre + " juega: " + mejorCarta);
        return mejorCarta;
    }

    @Override
    public String elegirAccion() {
        // IA simple: decide aleatoriamente si canta Truco o Envido, o pasa
        int opcion = random.nextInt(10); // 0 a 9
        if (opcion < 2) return "TRUCO"; // 20%
        if (opcion < 4) return "ENVIDO"; // 20%
        return "NINGUNA"; // 60%
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