package upm.clases.TipoActividades;

import upm.clases.Actividad;

public class Teatro extends Actividad {

    public Teatro(String nombre, String descripcion, int duracion, double costetotal, Integer aforo) {
        super(nombre, descripcion, duracion, costetotal, aforo);
    }


    public double aplicarDescuento(int edad) {
        double costeFinal = super.getCostetotal();

        int EDAD_JOVENES_DESCUENTO_TEATRO = 25;
        int EDAD_JUBILADOS_DESCUENTO_TEATRO = 65;
        if (edad <= EDAD_JOVENES_DESCUENTO_TEATRO) {
            costeFinal *= 0.5;
        } else if (edad >= EDAD_JUBILADOS_DESCUENTO_TEATRO) {
            costeFinal *= 0.3;
        }

        return costeFinal;
    }
}