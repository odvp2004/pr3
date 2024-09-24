package pr3.cambiosImagen;

import pr3.modelo.Pizarron;

public class Undo implements ICambioImagen {

    private Pizarron modelo;
    private HistorialCambios historialCambios;

    public Undo(Pizarron modelo){
        this.modelo = modelo;
        this.historialCambios = modelo.getImagen().getHistorialCambios();
    }
    @Override
    public void execute() {
        if(historialCambios.hasPast()){
            modelo.getImagen().setPixeles(historialCambios.getPastPixeles());
        }
    }
}
