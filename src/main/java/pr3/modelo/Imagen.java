package pr3.modelo;

import pr3.floodFill.FloodFill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

public class Imagen {
    private static final Logger logger = LogManager.getRootLogger();
    private final String OBSERVER_PIXELES = "OBSERVABLE PIXELES";
    private int[][] pixeles;
    private int width;
    private int height;
    private PropertyChangeSupport supportObserver;

    public Imagen() {
        pixeles = new int[][]{};
        supportObserver = new PropertyChangeSupport(this);

    }

    public void setPixeles(int[][] nuevosPixeles) {
        int[][] oldPixeles = Arrays.copyOf(pixeles, pixeles.length);
        this.pixeles = nuevosPixeles;

        this.width = pixeles.length;
        this.height = pixeles[0].length;

        logger.info("Se han establecido un nuevo grupo de pixeles en la matriz");
        supportObserver.firePropertyChange(OBSERVER_PIXELES, oldPixeles, pixeles);

    }

    public void addObserver(PropertyChangeListener observer) {
        supportObserver.addPropertyChangeListener(observer);
        logger.debug("Se ha añadido un nuevo observador");
    }

    public void dibujar(Graphics g) {
        if(width==0 && height==0){
            return;
        }
        Image img = createImageFromMatrix(pixeles, width, height);
        g.drawImage(img, 0,0, null);
        logger.debug("Se han pintado los pixeles en el panel principal");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPixeles() {
        return pixeles;
    }

    public boolean contains(Point point){
        return (point.x > 0 && point.x < width && point.y > 0 && point.y <height);
    }
    private Image createImageFromMatrix(int[][] pixels, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                img.setRGB(x, y, pixels[x][y]);
            }
        }
        return img;
    }


}
