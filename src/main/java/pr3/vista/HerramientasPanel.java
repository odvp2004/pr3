package pr3.vista;

import pr3.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HerramientasPanel extends JPanel {
    private JToggleButton cubetaButton, pincelButton, lineaButton, rectanguloButton;
    private Pizarron modelo;
    public HerramientasPanel(Pizarron modelo){
        super(new GridBagLayout());
        this.modelo = modelo;

        cubetaButton = new JToggleButton();
        pincelButton = new JToggleButton();
        lineaButton = new JToggleButton();
        rectanguloButton = new JToggleButton();

        ImageIcon[] iconos = new ImageIcon[4];
        iconos[0] = new ImageIcon("src/main/java/pr3/Icons/bote-de-pintura.png");
        iconos[1] = new ImageIcon("src/main/java/pr3/Icons/pincel.png");
        iconos[2] = new ImageIcon("src/main/java/pr3/Icons/linea.png");
        iconos[3] = new ImageIcon("src/main/java/pr3/Icons/cuadrado.png");

        JToggleButton[] herramientas = new JToggleButton[4];
        herramientas[0] = cubetaButton;
        herramientas[1] = pincelButton;
        herramientas[2] = lineaButton;
        herramientas[3] = rectanguloButton;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0,5,10,5);

        ButtonGroup group = new ButtonGroup();
        ActionListener actionListener = e -> {
            JToggleButton selectedButton = (JToggleButton) e.getSource();

            if (selectedButton == cubetaButton && cubetaButton.isSelected()) {
                modelo.setHerramientaSeleccionada(Pizarron.HERRAMIENTA_CUBETA);;
            } else if (selectedButton == lineaButton && lineaButton.isSelected()) {
                modelo.setHerramientaSeleccionada(Pizarron.HERRAMIENTA_LINEA);
            } else if (selectedButton == pincelButton && pincelButton.isSelected()) {
                modelo.setHerramientaSeleccionada(Pizarron.HERRAMIENTA_PINCEL);
            } else if (selectedButton == rectanguloButton && rectanguloButton.isSelected()) {
                modelo.setHerramientaSeleccionada(Pizarron.HERRAMIENTA_RECTANGULO);
            }
        };


        for(int i = 0; i<herramientas.length; i++){
            herramientas[i].setPreferredSize(new Dimension(40,40));
            herramientas[i].setMinimumSize(new Dimension(40, 40));
            herramientas[i].setBorderPainted(false);
            herramientas[i].setSelected(false);
            herramientas[i].addActionListener(actionListener);

            Image iconoEscala = iconos[i].getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            herramientas[i].setIcon(new ImageIcon(iconoEscala));
            add(herramientas[i], gbc);
            group.add(herramientas[i]);
            gbc.gridy++;
        }
    }
}
