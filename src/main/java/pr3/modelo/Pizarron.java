package pr3.modelo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr3.cambiosImagen.HistorialCambios;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Pizarron {
    private Imagen imagen;
    private int colorActual;
    private String herramientaSeleccionada;
    private int rango;

    public static final String HERRAMIENTA_CUBETA = "CUBETA";
    public static final String HERRAMIENTA_PINCEL = "PINCEL";
    public static final String HERRAMIENTA_LINEA = "LINEA";
    public static final String HERRAMIENTA_RECTANGULO = "RECTANGULO";

    private static final Logger logger = LogManager.getRootLogger();
    private PropertyChangeSupport supportObserver;
    private final String OBSERVER_COLOR = "OBSERVABLE_COLOR";
    private final String OBSERVER_RANGO = "OBSERVABLE_RANGO";


    public Pizarron(Imagen imagen){
        this.imagen = imagen;
        this.colorActual = Color.WHITE.getRGB();
        this.rango = 10;
        supportObserver = new PropertyChangeSupport(this);
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public int getColorActual() {
        return colorActual;
    }

    public void setColorActual(int colorActual) {
        int oldColor = this.colorActual;
        this.colorActual = colorActual;
        supportObserver.firePropertyChange(OBSERVER_COLOR, oldColor, colorActual);
        logger.info("Se cambió el color");
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
        int oldRango = this.rango;
        this.rango = rango;
        supportObserver.firePropertyChange(OBSERVER_RANGO, oldRango, rango);
        logger.info("Se cambió el rango");
    }

    public void addObserver(PropertyChangeListener observer) {
        supportObserver.addPropertyChangeListener(observer);
        logger.debug("Se ha añadido un nuevo observador");
    }
}
