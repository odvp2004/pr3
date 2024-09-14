package pr3.vista;

import pr3.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr3.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ImagenPanel extends JPanel implements MouseListener, PropertyChangeListener {
    private Pizarron modelo;
    private RightPanel rightpanel;

    private static final Logger logger = LogManager.getRootLogger();

    public ImagenPanel(Pizarron modelo, RightPanel rightPanel) {
        this.addMouseListener(this);
        this.modelo = modelo;
        this.modelo.getImagen().addObserver(this);
        this.rightpanel = rightPanel;
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        //optimizar sgt linea:
        if ((modelo.getHerramientaSeleccionada() != null) && modelo.getImagen().contains(e.getPoint())) {
            modelo.getImagen().pintar(e.getPoint(), modelo.getColorActual().getRGB(),modelo.getRango());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Imagen) {
            logger.info("Se ha detectado cambios en el modelo");
            repaint();
        }
    }

}
