package pr3.vista;

import pr3.cambiosImagen.ControladorCambiosImagen;
import pr3.cambiosImagen.ICambioImagen;
import pr3.cambiosImagen.Redo;
import pr3.cambiosImagen.Undo;
import pr3.excepciones.ImagenException;
import pr3.manejoDeImagen.AbrirImagen;
import pr3.manejoDeImagen.GuardarImagen;
import pr3.manejoDeImagen.IManejoDeImagen;
import pr3.modelo.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pr3.modelo.Pizarron;

import javax.swing.*;
import java.awt.*;


public class ImagenFrame extends JFrame {
    private Pizarron modelo;
    private ImagenPanel centerPanel;
    private RightPanel rightPanel;
    private JMenu jMenuArchivo, jMenuEditar;
    private JMenuItem nuevoItem, guardarItem, salirItem, undoItem, redoItem;
    private JMenuBar jMenubar;
    private static final Logger logger = LogManager.getRootLogger();
    private ControladorCambiosImagen controladorImagen;


    public ImagenFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,480));
        this.controladorImagen = new ControladorCambiosImagen();
        init();
    }

    private void init() {
        modelo = new Pizarron(new Imagen());
        rightPanel = new RightPanel(modelo);
        rightPanel.setPreferredSize(new Dimension(150, 0));

        centerPanel = new ImagenPanel(modelo, rightPanel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(rightPanel, BorderLayout.EAST);

        nuevoItem = new JMenuItem("Cargar Imagen");
        guardarItem = new JMenuItem("Guardar Imagen");
        salirItem = new JMenuItem("Salir");

        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");

        salirItem.addActionListener(e -> {
            logger.info("Se ha cerrado el programa");
            System.exit(0);
        });

        nuevoItem.addActionListener(e -> manejarImagen(new AbrirImagen()));
        guardarItem.addActionListener(e -> manejarImagen(new GuardarImagen()));

        undoItem.addActionListener(e -> executeEditCommand(new Undo(modelo)));
        redoItem.addActionListener(e -> executeEditCommand(new Redo(modelo)));

        jMenuArchivo = new JMenu("Archivo");
        jMenuArchivo.add(nuevoItem);
        jMenuArchivo.add(guardarItem);
        jMenuArchivo.add(salirItem);
        jMenubar = new JMenuBar();
        jMenubar.add(jMenuArchivo);

        jMenuEditar = new JMenu("Editar");
        jMenuEditar.add(undoItem);
        jMenuEditar.add(redoItem);
        jMenubar.add(jMenuEditar);

        setJMenuBar(jMenubar);
        centerPanel.setPreferredSize(new Dimension(500,400));

        this.pack();
        this.setVisible(true);
    }

    private void manejarImagen(IManejoDeImagen manejoDeImagen){
        try{
            manejoDeImagen.hacer(this, modelo.getImagen());
            logger.info("El manejo de la imagen ha sido exitoso");
        }catch (ImagenException e){
            logger.error("El manejo de la imagen tubo un error");
            e.printStackTrace();
        }

    }
    private void executeEditCommand(ICambioImagen cambioImagen){
        controladorImagen.setCambioCommand(cambioImagen);
        controladorImagen.execute();
    }
}
