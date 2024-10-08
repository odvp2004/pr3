package pr3.accionesImagen;

import pr3.floodFill.FloodFill;
import pr3.modelo.Imagen;
import pr3.modelo.Pizarron;

import java.awt.*;

public class Pintar {
    public static void hacer(Pizarron modelo, Point point){
        FloodFill floodFill = new FloodFill();
        int[][] pixeles = floodFill.floodFill(modelo.getImagen().getPixeles(), point.x, point.y, modelo.getColorActual(), modelo.getRango());
        modelo.getImagen().setPixeles(pixeles);
        modelo.getImagen().getHistorialCambios().guardarPixeles(pixeles);
    }
}
