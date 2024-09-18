package pr3.vista;

import pr3.accionesImagen.DibujarCuadrado;
import pr3.accionesImagen.DibujarLinea;
import pr3.accionesImagen.DibujarPunto;
import pr3.accionesImagen.Pintar;
import pr3.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr3.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImagenPanel extends JPanel implements MouseListener, MouseMotionListener, PropertyChangeListener {
    private Pizarron modelo;
    private RightPanel rightpanel;

    private Point puntoFinal;
    private Point puntoInicio;

    private static final Logger logger = LogManager.getRootLogger();

    public ImagenPanel(Pizarron modelo, RightPanel rightPanel) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.modelo = modelo;
        this.modelo.getImagen().addObserver(this);
        this.modelo.addObserver(this);
        this.rightpanel = rightPanel;

        puntoInicio = new Point();
        puntoFinal = new Point();
    }

    @Override
    public Dimension getPreferredSize() {
        if(modelo.getImagen().getWidth() > 0 && modelo.getImagen().getHeight()>0){
            return new Dimension(modelo.getImagen().getWidth(), modelo.getImagen().getHeight());
        }
        return new Dimension(500,400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getMousePosition() != null){
            modelo.getImagen().dibujar(g);
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        puntoFinal = e.getPoint();
        puntoInicio = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (validarEvento(e)) {
            realizarAccionDePintado(e.getPoint());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int newX = e.getX();
        int newY = e.getY();
        if (validarEvento(e) && modelo.getHerramientaSeleccionada().equals(Pizarron.HERRAMIENTA_PINCEL)) {
            pincelar(puntoFinal.x, puntoFinal.y, newX, newY);
        }
        puntoFinal = new Point(newX, newY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Imagen) {
            logger.info("Se ha detectado cambios en el modelo");
            repaint();
        }
    }

    public void realizarAccionDePintado(Point point){
        switch (modelo.getHerramientaSeleccionada()){
            case Pizarron.HERRAMIENTA_CUBETA:
                Pintar pintar = new Pintar(modelo.getImagen(), point, modelo.getColorActual(), modelo.getRango());
                break;
            case Pizarron.HERRAMIENTA_PINCEL:
                DibujarPunto dibujarPunto =new DibujarPunto(modelo.getImagen(), point, modelo.getColorActual());
                break;
            case Pizarron.HERRAMIENTA_LINEA:
                DibujarLinea dibujarLinea = new DibujarLinea(modelo.getImagen(), puntoInicio, puntoFinal, modelo.getColorActual());
                break;
            case Pizarron.HERRAMIENTA_RECTANGULO:
                int x = puntoInicio.x;
                int y = puntoInicio.y;

                int ancho = Math.abs(puntoInicio.x - puntoFinal.x);
                int alto = Math.abs(puntoInicio.y - puntoFinal.y);

                if(puntoInicio.y > puntoFinal.y){
                    y = puntoFinal.y;
                }
                if(puntoInicio.x > puntoFinal.x){
                    x = puntoFinal.x;
                }

                DibujarCuadrado dibujarCuadrado =new DibujarCuadrado(modelo.getImagen(), new Point(x,y), alto, ancho, modelo.getColorActual());
                break;

        }
    }
    public void pincelar(int x1, int y1, int x2, int y2) {
        DibujarLinea.di(modelo.getImagen(), new Point(x1,y1), new Point(x2,y2), modelo.getColorActual());
    }

    public boolean validarEvento(MouseEvent e){
        return (modelo.getHerramientaSeleccionada() != null) && modelo.getImagen().contains(e.getPoint());
    }

}
