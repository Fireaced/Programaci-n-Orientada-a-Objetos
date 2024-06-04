package upm.clases.TipoActividades;

import upm.clases.Actividad;

public class Cine extends Actividad {

    public Cine(String nombre, String Descripcion, int duracion, double costetotal, Integer aforo) {
        super(nombre, Descripcion, duracion, costetotal, aforo);
    }


    public double aplicarDescuento(int edad) {
        double costeFinal = super.getCostetotal();

        int EDAD_JOVENES_DESCUENTO_CINE = 21;
        if (edad <= EDAD_JOVENES_DESCUENTO_CINE) {
            costeFinal *= 0.5;
        }

        return costeFinal;
    }
}
