package pr3.cambiosImagen;

import java.util.ArrayList;
import java.util.Arrays;

public class HistorialCambios{
    private ArrayList<int[][]> historialPixeles;
    private int posicionActual;
    public HistorialCambios(){
        historialPixeles = new ArrayList<>();
        posicionActual = -1;
    }
    public int[][] getPastPixeles(){
        posicionActual--;
        System.out.println(posicionActual);
        return historialPixeles.get(posicionActual);

    }
    public int[][] getNextPixeles(){
        posicionActual++;
        System.out.println(posicionActual);
        return historialPixeles.get(posicionActual);
    }

    public void guardarPixeles(int[][] pixelesNuevos){
        posicionActual++;
        if (posicionActual < historialPixeles.size()) {
            historialPixeles.set(posicionActual, pixelesNuevos);
        } else {
            historialPixeles.add(pixelesNuevos);
        }
        historialPixeles.subList(posicionActual + 1, historialPixeles.size()).clear();
        if(posicionActual>=1){
            boolean igual = Arrays.deepEquals(historialPixeles.get(posicionActual), historialPixeles.get(posicionActual - 1));
            System.out.println(igual);
            System.out.println(historialPixeles.size());
        }

    }

    public ArrayList<int[][]> getHistorialPixeles() {
        return historialPixeles;
    }



    public boolean hasNext(){
        return historialPixeles.size() - 1  > posicionActual;
    }
    public boolean hasPast(){
        return posicionActual > 0;
    }

}