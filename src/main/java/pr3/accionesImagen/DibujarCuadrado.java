package pr3.accionesImagen;

import pr3.modelo.Imagen;

import java.awt.*;

public class DibujarCuadrado {
    public DibujarCuadrado(Imagen imagen, Point puntoInicial, int alto, int ancho, int colorActual){
        int[][] pixeles = imagen.getPixeles();

        for(int y= puntoInicial.y; y<=puntoInicial.y+alto; y++){
            pixeles[puntoInicial.x][y] = colorActual;
            pixeles[puntoInicial.x + ancho][y] = colorActual;
        }
        for(int x = puntoInicial.x; x<=puntoInicial.x+ancho;x++){
            pixeles[x][puntoInicial.y] = colorActual;
            pixeles[x][puntoInicial.y + alto] = colorActual;
        }
        imagen.setPixeles(pixeles);

    }
}
