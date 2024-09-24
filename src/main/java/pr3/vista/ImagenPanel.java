package pr3.vista;

import pr3.accionesImagen.*;
import pr3.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr3.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ImagenPanel extends JPanel implements MouseListener, MouseMotionListener, PropertyChangeListener {
    private Pizarron modelo;
    private RightPanel rightpanel;

    private Point puntoFinal;
    private Point puntoInicio;
    private boolean figuraEnCurso;

    private static final Logger logger = LogManager.getRootLogger();

    public ImagenPanel(Pizarron modelo, RightPanel rightPanel) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.modelo = modelo;
        this.modelo.getImagen().addObserver(this);
        this.modelo.addObserver(this);
        this.rightpanel = rightPanel;

        figuraEnCurso = false;

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

        modelo.getImagen().dibujar(g);

        if(figuraEnCurso && validarEvento(puntoFinal)){
            previsualizacionFiguras(puntoFinal.x, puntoFinal.y, g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(validarEvento(e.getPoint())){
            puntoFinal = e.getPoint();
            puntoInicio = e.getPoint();
            if(modelo.getHerramientaSeleccionada().equals(Pizarron.HERRAMIENTA_RECTANGULO) || modelo.getHerramientaSeleccionada().equals(Pizarron.HERRAMIENTA_LINEA)){
                figuraEnCurso = true;
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (validarEvento(e.getPoint())) {
            realizarAccionDePintado(e.getPoint());
            figuraEnCurso = false;
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
        if (validarEvento(e.getPoint()) && modelo.getHerramientaSeleccionada().equals(Pizarron.HERRAMIENTA_PINCEL)) {
            DibujarLinea.hacer(modelo, new Point(puntoFinal.x,puntoFinal.y), new Point(newX,newY));
        }
        if(modelo.getImagen().contains(e.getPoint())){
            puntoFinal = new Point(newX, newY);
        }
        if(figuraEnCurso){
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Imagen) {
            logger.info("Se ha detectado cambios en el modelo");
        }
        repaint();

    }

    public void realizarAccionDePintado(Point point){
        switch (modelo.getHerramientaSeleccionada()){
            case Pizarron.HERRAMIENTA_CUBETA:
                Pintar.hacer(modelo, point);
                break;
            case Pizarron.HERRAMIENTA_PINCEL:
                DibujarPunto.hacer(modelo, point);
                break;
            case Pizarron.HERRAMIENTA_LINEA:
                DibujarLinea.hacer(modelo, puntoInicio, puntoFinal);
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
                DibujarCuadrado.hacer(modelo, new Point(x,y), alto, ancho);
                break;
        }
    }
    public void previsualizacionFiguras(int x, int y, Graphics g){
        switch(modelo.getHerramientaSeleccionada()){

            case Pizarron.HERRAMIENTA_RECTANGULO:
                g.setColor(new Color(modelo.getColorActual()));

                int ancho = Math.abs(puntoInicio.x - x);
                int alto = Math.abs(puntoInicio.y - y);

                int xIzq = puntoInicio.x;
                int yIzq = puntoInicio.y;

                if(puntoInicio.y > y){
                    yIzq = y;
                }
                if(puntoInicio.x > x){
                    xIzq = x;
                }


                g.drawRect(xIzq, yIzq, ancho, alto);
                break;
            case Pizarron.HERRAMIENTA_LINEA:

                g.setColor(new Color(modelo.getColorActual()));
                g.drawLine(puntoInicio.x, puntoInicio.y, x, y);
                break;
        }
    }

    public boolean validarEvento(Point e){
        return (modelo.getHerramientaSeleccionada() != null) && modelo.getImagen().contains(e);
    }

}
