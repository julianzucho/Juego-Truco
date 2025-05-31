package truco.ui;

import java.util.Scanner;

public class ConsolaUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void mostrarError(String error) {
        System.err.println("[ERROR] " + error);
    }

    public static String leerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int leerNumero(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese un número válido.");
            scanner.next(); // descarta entrada no válida
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // limpia buffer
        return num;
    }

    public static boolean leerSiNo(String prompt) {
        System.out.print(prompt + " (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s");
    }

    public static void esperarEnter() {
        System.out.print("Presioná Enter para continuar...");
        scanner.nextLine();
    }
}