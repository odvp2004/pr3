package pr3.accionesImagen;
import pr3.modelo.Pizarron;
import java.awt.*;


public class DibujarPunto {
    public static void hacer(Pizarron modelo, Point punto){
        int[][] pixeles = new int[modelo.getImagen().getPixeles().length][];
        for (int i = 0; i < modelo.getImagen().getPixeles().length; i++) {
            pixeles[i] = modelo.getImagen().getPixeles()[i].clone();
        }

        pixeles[punto.x][punto.y] = modelo.getColorActual();
        modelo.getImagen().setPixeles(pixeles);
        modelo.getImagen().getHistorialCambios().guardarPixeles(pixeles);
    }

}

