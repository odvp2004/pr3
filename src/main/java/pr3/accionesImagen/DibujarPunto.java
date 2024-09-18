package pr3.accionesImagen;

import pr3.modelo.Imagen;

import java.awt.*;


public class DibujarPunto {
    public DibujarPunto(Imagen imagen, Point punto, int color){
        int[][] pixeles = imagen.getPixeles();
        pixeles[punto.x][punto.y] = color;
        pixeles[punto.x+1][punto.y] = color;
        pixeles[punto.x-1][punto.y] = color;
        pixeles[punto.x][punto.y+1] = color;
        pixeles[punto.x][punto.y-1] = color;

        imagen.setPixeles(pixeles);
    }
}

