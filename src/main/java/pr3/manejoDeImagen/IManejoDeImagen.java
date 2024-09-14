package pr3.manejoDeImagen;

import pr3.excepciones.ImagenException;
import pr3.modelo.Imagen;

import javax.swing.*;

public interface IManejoDeImagen {

    /**
     * Cualquier tipo de manejo de imagen que se haga debe implementar esta clase.
     */

    void hacer(JFrame parent, Imagen modelo) throws ImagenException;
}
