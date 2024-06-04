package upm.servicios;

import upm.clases.Actividad;
import upm.clases.Plan;
import upm.clases.Usuario;
import upm.excepciones.InvalidAtrributeExcepcion;
import upm.excepciones.NonExistentAtributeException;
import upm.repositorios.ActividadRep;
import upm.repositorios.PlanRep;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ActividadServicio {
    private final ActividadRep actividadRep;
    private final PlanRep planRep;

    public ActividadServicio(ActividadRep actividadRep, PlanRep planRep) {
        this.actividadRep = actividadRep;
        this.planRep = planRep;
    }

    public Actividad create(Actividad actividad) {
        if (this.actividadRep.findbyId(actividad.getId()).isPresent()) {
            throw new InvalidAtrributeExcepcion("El nombre deberia ser único y " + actividad.getId() + " ya existe");
        }
        return this.actividadRep.create(actividad);
    }

    public List<Actividad> intervaloCosteDescuento(int costeMin, int costeMax, Usuario usuarioLogueado) {
        List<Actividad> listaActividades = actividadRep.findAll();
        List<Actividad> actividadesEnIntervalo = new LinkedList<>();
        for (Actividad actividad : listaActividades) {
            if (actividad.aplicarDescuento(usuarioLogueado.getEdad()) >= costeMin && actividad.aplicarDescuento(usuarioLogueado.getEdad()) <= costeMax) {
                actividadesEnIntervalo.add(actividad);
            }
        }
        return actividadesEnIntervalo;
    }

    public List<Actividad> intervaloCosteNormal(int costeMin, int costeMax) {
        List<Actividad> listaActividades = actividadRep.findAll();
        List<Actividad> actividadesEnIntervalo = new LinkedList<>();
        for (Actividad actividad : listaActividades) {
            if (actividad.getCostetotal() >= costeMin && actividad.getCostetotal() <= costeMax) {
                actividadesEnIntervalo.add(actividad);
            }
        }
        return actividadesEnIntervalo;
    }


    public List<Actividad> actividadesPosibles(Integer idPlan) {
        if (!planRep.findById(idPlan).isPresent()) {
            throw new NonExistentAtributeException("No existe ese plan");
        }
        List<Actividad> listaActividades = actividadRep.findAll();
        Iterator<Actividad> iterator = listaActividades.iterator();
        List<Actividad> listaPosibles = new LinkedList<>();
        Plan plan = planRep.findById(idPlan).get();
        while (iterator.hasNext()) {
            Actividad actual = iterator.next();
            if (actual.getAforo() < plan.getCapacidadMax()) {
                listaPosibles.add(actual);
            }
        }
        return listaPosibles;
    }
}

