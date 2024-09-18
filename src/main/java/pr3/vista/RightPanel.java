package pr3.vista;

import pr3.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr3.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RightPanel extends JPanel implements PropertyChangeListener {

    private JButton actualColorButton;

    private Pizarron modelo;
    private static final Logger logger = LogManager.getRootLogger();
    private JLabel infoImagenLabel;
    private JLabel rangoLabel;
    private JSpinner rangoSpinner;
    private HerramientasPanel herramientasPanel;




    public RightPanel(Pizarron modelo) {
        super(new GridBagLayout());
        this.modelo = modelo;
        modelo.addObserver(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        infoImagenLabel = new JLabel("Tamaño: 0 x 0" );

        add(infoImagenLabel, gbc);

        gbc.gridy++;

        actualColorButton = new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(60, 60);
            }
        };
        actualColorButton.setMinimumSize(new Dimension(60, 60));
        actualColorButton.setBackground(new Color(modelo.getColorActual()));
        actualColorButton.setBorderPainted(false);
        actualColorButton.addActionListener(e -> {

            Color nuevoColor = JColorChooser.showDialog(this, "Select Color", new Color(modelo.getColorActual()));
            if (nuevoColor != null) {
                modelo.setColorActual(nuevoColor.getRGB());
                logger.info("Nuevo color seleccionado para pintar");
            }

        });

        add(actualColorButton, gbc);
        gbc.gridy++;

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(modelo.getRango(), 10, 50, 10);
        rangoSpinner = new JSpinner(spinnerModel);

        rangoLabel = new JLabel("Rango: " + rangoSpinner.getValue());
        add(rangoLabel, gbc);
        gbc.gridy++;


        rangoSpinner.addChangeListener(e -> {
            int nuevoRango = (int) rangoSpinner.getValue();
            modelo.setRango(nuevoRango);
        });

        add(rangoSpinner, gbc);
        gbc.gridy++;

        herramientasPanel = new HerramientasPanel(modelo);
        add(herramientasPanel, gbc);

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        infoImagenLabel.setText("Tamaño: " + modelo.getImagen().getWidth() + " x " + modelo.getImagen().getHeight());
        rangoLabel.setText("Rango: " + modelo.getRango());
        actualColorButton.setBackground(new Color(modelo.getColorActual()));
    }
}
