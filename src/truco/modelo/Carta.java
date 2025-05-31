package truco.modelo;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.*;

public class Carta {
    private String palo;
    private int numero;
    private int poderTruco;

    public Carta(String palo, int numero, int poderTruco) {
        this.palo = palo;
        this.numero = numero;
        this.poderTruco = poderTruco;
    }

    public String getPalo() { return palo; }
    public int getNumero() { return numero; }
    public int getPoderTruco() { return poderTruco; }

    public int getValorEnvido() {
        return (numero >= 10) ? 0 : numero;
    }

    @Override
    public String toString() {
        return numero + " de " + palo;
    }

    public int getValorTruco() {
        return poderTruco;
    }
    public int comparar(Carta otra) {
        return Integer.compare(this.getValorTruco(), otra.getValorTruco());
    }
    // Devuelve el nombre del archivo, ej: "1_espada.png"
    public String getNombreArchivo() {
        return numero + "_" + palo + ".png";
    }

    public Image getImagen() {
        String path = "/cartas/" + getNombreArchivo();
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        return icon.getImage();
    }


}