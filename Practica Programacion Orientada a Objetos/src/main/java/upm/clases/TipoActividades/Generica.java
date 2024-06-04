package upm.clases.TipoActividades;

import upm.clases.Actividad;

public class Generica extends Actividad {
    public Generica(String nombre, String descripcion, int duracion, double costetotal, Integer aforo) {
        super(nombre, descripcion, duracion, costetotal, aforo);
    }

    public double aplicarDescuento(int edad) {
        double costeFinal = super.getCostetotal();
        return costeFinal;
    }
}
