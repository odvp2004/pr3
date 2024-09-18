package pr3.accionesImagen;

import pr3.modelo.Imagen;

import java.awt.*;

public class DibujarLinea {
    public DibujarLinea(Imagen imagen, Point puntoInicial, Point puntoFinal, int color){

        int x0 = puntoInicial.x;
        int x1 = puntoFinal.x;

        int y0 = puntoInicial.y;
        int y1 = puntoFinal.y;


        int dx = Math.abs(x1 - x0); //diferencia x
        int dy = Math.abs(y1 - y0); //diferencia y

        int sx = x0 < x1 ? 1 : -1;  //direccion x
        int sy = y0 < y1 ? 1 : -1;  //direccion y

        int err = dx - dy;     //

        int[][] pixeles = imagen.getPixeles();

        while (true) {
            pixeles[x0][y0] = color; // Dibuja el punto actual
            if (x0 == x1 && y0 == y1) break; // Terminar cuando llegamos al punto final

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
        imagen.setPixeles(pixeles);

    }
    public static void di(Imagen imagen, Point puntoInicial, Point puntoFinal, int colorActual){

        int x0 = puntoInicial.x;
        int x1 = puntoFinal.x;

        int y0 = puntoInicial.y;
        int y1 = puntoFinal.y;


        int dx = Math.abs(x1 - x0); //diferencia x
        int dy = Math.abs(y1 - y0); //diferencia y

        int sx = x0 < x1 ? 1 : -1;  //direccion x
        int sy = y0 < y1 ? 1 : -1;  //direccion y

        int err = dx - dy;     //

        int[][] pixeles = imagen.getPixeles();

        while (true) {
            pixeles[x0][y0] = colorActual; // Dibuja el punto actual
            if (x0 == x1 && y0 == y1) break; // Terminar cuando llegamos al punto final

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
        imagen.setPixeles(pixeles);

    }
}
