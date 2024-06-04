package upm.repositorios;

import upm.clases.Actividad;

import java.util.Optional;

public interface ActividadRep extends RepGenerico<Actividad> {
    Optional<Actividad> findbyId(int id);
}