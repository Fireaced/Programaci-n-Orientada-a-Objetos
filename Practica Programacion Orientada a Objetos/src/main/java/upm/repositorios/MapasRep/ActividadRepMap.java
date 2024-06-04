package upm.repositorios.MapasRep;

import upm.clases.Actividad;
import upm.repositorios.ActividadRep;

import java.util.Optional;

public class ActividadRepMap extends RepGenericoMap<Actividad> implements ActividadRep {
    @Override
    protected Integer getId(Actividad actividad) {
        return actividad.getId();
    }

    @Override
    protected void setId(Actividad entity, Integer id) {
        entity.setId(id);
    }

    @Override
    public Optional<Actividad> findbyId(int id) {
        for (Actividad actividad : this.findAll()) {
            if (actividad.getId() == id) {
                return Optional.of(actividad);
            }
        }
        return Optional.empty();
    }

}