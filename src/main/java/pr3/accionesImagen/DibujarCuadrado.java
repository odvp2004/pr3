package pr3.accionesImagen;
import pr3.modelo.Pizarron;
import java.awt.*;

public class DibujarCuadrado {
    public static void hacer(Pizarron modelo, Point puntoInicial, int alto, int ancho){

        int[][] pixeles = modelo.getImagen().getPixeles();

        for(int y= puntoInicial.y; y<=puntoInicial.y+alto; y++){
            pixeles[puntoInicial.x][y] = modelo.getColorActual();
            pixeles[puntoInicial.x + ancho][y] = modelo.getColorActual();
        }
        for(int x = puntoInicial.x; x<=puntoInicial.x+ancho;x++){
            pixeles[x][puntoInicial.y] = modelo.getColorActual();
            pixeles[x][puntoInicial.y + alto] = modelo.getColorActual();
        }
        modelo.getImagen().setPixeles(pixeles);

    }
}
