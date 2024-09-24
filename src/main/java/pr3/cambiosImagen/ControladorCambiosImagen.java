package pr3.cambiosImagen;

public class ControladorCambiosImagen {
    private ICambioImagen cambioCommand;

    public void setCambioCommand(ICambioImagen cambioCommand) {
        this.cambioCommand = cambioCommand;
    }
    public void execute(){
        cambioCommand.execute();
    }
}
