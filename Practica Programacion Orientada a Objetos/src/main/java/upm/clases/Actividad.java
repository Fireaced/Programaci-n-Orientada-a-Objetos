package upm.clases;

public abstract class Actividad {
    private int id;
    private String nombre;
    private String descripcion;
    private int duracion;
    private double costetotal;
    private int aforo;

    public Actividad(String nombre, String Descripcion, int duracion, double costetotal, Integer aforo) {
        setNombre(nombre);
        setDescripcion(Descripcion);
        setDuracion(duracion);
        setCostetotal(costetotal);
        setAforo(aforo);

    }

    public double aplicarDescuento(int edad) {
        return this.costetotal;
    }

    public String toString() {
        if (aforo < Integer.MAX_VALUE)
            return "creada: " + "id:" + id + " ;nombre:" + nombre + " ;descripción" + descripcion + " ;duración:" + duracion + " ;coste:" + costetotal + " ;aforo:" + aforo;
        else
            return "creada: " + "id:" + id + " ;nombre:" + nombre + " ;descripción" + descripcion + " ;duración:" + duracion + " ;coste:" + costetotal + " ;aforo:" + "sin limite";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion() {
        return duracion;
    }

    public double getCostetotal() {
        return costetotal;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public void setCostetotal(double costetotal) {
        this.costetotal = costetotal;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAforo() {
        return aforo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }
}