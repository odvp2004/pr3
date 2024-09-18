package pr3.accionesImagen;

import pr3.modelo.Imagen;
import pr3.modelo.Pizarron;

import java.awt.*;


public class DibujarPunto {
    public static void hacer(Pizarron modelo, Point punto){
        int[][] pixeles = modelo.getImagen().getPixeles();
        pixeles[punto.x][punto.y] = modelo.getColorActual();
        modelo.getImagen().setPixeles(pixeles);
    }
}

