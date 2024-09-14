package pr3.modelo;

import java.awt.*;

public class Pizarron {
    private Imagen imagen;
    private Color colorActual;
    private String herramientaSeleccionada;
    private int rango;

    public static final String HERRAMIENTA_CUBETA = "CUBETA";
    public static String HERRAMIENTA_PINCEL = "PINCEL";
    public static String HERRAMIENTA_LINEA = "LINEA";
    public static String HERRAMIENTA_RECTANGULO = "RECTANGULO";


    public Pizarron(Imagen imagen){
        this.imagen = imagen;
        this.colorActual = Color.WHITE;
        this.rango = 10;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Color getColorActual() {
        return colorActual;
    }

    public void setColorActual(Color colorActual) {
        this.colorActual = colorActual;
    }

    public String getHerramientaSeleccionada() {
        return herramientaSeleccionada;
    }

    public void setHerramientaSeleccionada(String herramientaSeleccionada) {
        this.herramientaSeleccionada = herramientaSeleccionada;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }
}
