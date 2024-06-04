package upm.clases;

import upm.excepciones.InvalidAtrributeExcepcion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Plan {
    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;
    private String lugarEncuentro;
    private int capacidadMax;
    private int id;
    private String nombreCreador;
    private final List<Actividad> listactividades;
    private final List<Usuario> listaparticipantes;
    private final Map<String, Float> puntuaciones;

    public Plan(String nombre, LocalDate fecha, LocalTime hora, String lugarEncuentro, int capacidadMax) {
        setNombre(nombre);
        setfecha(fecha);
        setHora(hora);
        setlugarEncuentro(lugarEncuentro);
        setcapacidadMax(capacidadMax);
        listactividades = new LinkedList<>();
        listaparticipantes = new LinkedList<>();
        this.puntuaciones = new HashMap<>();
    }

    public Plan(String nombre, LocalDate fecha, LocalTime hora, String lugarEncuentro) {
        setNombre(nombre);
        setfecha(fecha);
        setHora(hora);
        setlugarEncuentro(lugarEncuentro);
        setcapacidadMax(Integer.MAX_VALUE);
        listactividades = new LinkedList<>();
        listaparticipantes = new LinkedList<>();
        this.puntuaciones = new HashMap<>();
    }

    public void puntuar(float puntuacion, String nombreUsuario) {
        if (this.puntuaciones.containsKey(nombreUsuario)) {
            throw new InvalidAtrributeExcepcion("Ya has puntuado ese plan");
        }
        this.puntuaciones.put(nombreUsuario, puntuacion);
    }

    public String toString() {
        return "Id:" + id + "; Dueño: " + nombreCreador + "; Nombre:" + nombre + "; Fecha:" + fecha.toString()
                + "; Hora:" + hora.toString() + "; LugarEncuentro:" + lugarEncuentro + "; Puntuacion:" + puntuacionMedia();
    }

    public int tiempoTotal() {
        int Tiempototal = 0;
        int numactividades = -1;
        for (Actividad listactividade : listactividades) {
            Tiempototal += listactividade.getDuracion();
            numactividades++;
        }
        if (numactividades > 0)
            Tiempototal += numactividades * 20;
        return Tiempototal;
    }


    public void aniadiractividad(Actividad actividad) {
        if (!(actividad.getAforo() <= getCapacidadMax())) {
            throw new InvalidAtrributeExcepcion("No se permiten mas usuarios en esta actividad");
        }
        listactividades.add(actividad);
        setcapacidadMax(getCapacidadMax() - actividad.getAforo());
    }

    public void borrarParticipante(Usuario usuario) {
        if (!listaparticipantes.contains(usuario)) {
            throw new InvalidAtrributeExcepcion("No estas suscrito a esta actividad");
        }
        listaparticipantes.remove(usuario);
    }

    public void aniadirparticipante(Usuario usuario) {
        if (!(this.getCapacidadMax() >= this.getListaparticipantes().size() + 1)) {
            throw new InvalidAtrributeExcepcion("No hay mas huecos en el plan");
        } else {
            this.getListaparticipantes().add(usuario);
        }
    }

    public float puntuacionMedia() {
        if (puntuaciones.isEmpty()) {
            return 0;
        } else {
            float media = 0;
            for (Map.Entry<String, Float> puntuacion : puntuaciones.entrySet()) {
                media += puntuacion.getValue();
            }
            media = media / puntuaciones.size();
            return media;
        }
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
    }

    public String getNombreCreador() {
        return nombreCreador;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public LocalTime getHora() {
        return hora;
    }

    public List<Actividad> getListactividades() {
        return listactividades;
    }

    public List<Usuario> getListaparticipantes() {
        return listaparticipantes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setcapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public void setfecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setlugarEncuentro(String lugar_encuentro) {
        lugarEncuentro = lugar_encuentro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPuntuacion(float puntuacion, String nombreUsuario) {
        if (this.puntuaciones.containsKey(nombreUsuario)) {
            throw new IllegalArgumentException("Ya has puntuado ese plan");
        }
        this.puntuaciones.put(nombreUsuario, puntuacion);
    }
}
