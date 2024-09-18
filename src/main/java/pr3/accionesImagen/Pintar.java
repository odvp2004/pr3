package pr3.accionesImagen;

import pr3.floodFill.FloodFill;
import pr3.modelo.Imagen;

import java.awt.*;

public class Pintar {
    public Pintar(Imagen imagen, Point point, int color, int rango){
        FloodFill floodFill = new FloodFill();
        imagen.setPixeles(floodFill.floodFill(imagen.getPixeles(), point.x, point.y, color, rango));
    }
}
