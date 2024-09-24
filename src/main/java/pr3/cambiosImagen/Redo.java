package pr3.cambiosImagen;

import pr3.modelo.Pizarron;

public class Redo implements ICambioImagen {
    private Pizarron modelo;
    private HistorialCambios historialCambios;

    public Redo(Pizarron modelo){
        this.modelo = modelo;
        this.historialCambios = modelo.getImagen().getHistorialCambios();
    }
    @Override
    public void execute() {
        if(modelo.getImagen().getHistorialCambios().hasNext()){
            modelo.getImagen().setPixeles(historialCambios.getNextPixeles());
        }
    }
}
