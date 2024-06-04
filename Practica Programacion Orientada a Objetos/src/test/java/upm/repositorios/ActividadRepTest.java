package upm.repositorios;

import org.junit.jupiter.api.Test;
import upm.DependencyInjector;
import upm.clases.Actividad;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ActividadRepTest {
    private final ActividadRep actividadRep = DependencyInjector.getDependencyInjector().getActividadRep();

    @Test
    void testCreateAndRead() {
        Optional<Actividad> actividad = this.actividadRep.read(1);
        assertTrue(actividad.isPresent());
        assertEquals("Escondite", actividad.get().getNombre());
    }

    @Test
    void testFindById() {
        Optional<Actividad> actividad = actividadRep.findbyId(2);
        assertTrue(actividad.isPresent());
        assertEquals("PillaPilla", actividad.get().getNombre());
    }

    @Test
    void testFindByIdNotFound() {
        assertFalse(actividadRep.findbyId(-3).isPresent());
    }

    @Test
    void testUpdate() {
        Actividad actividad = this.actividadRep.read(2).get();
        String oldName = actividad.getNombre();
        actividad.setNombre("Nombre cambiado");
        actividadRep.update(actividad);

        Optional<Actividad> actividadOptional = actividadRep.read(2);
        assertTrue(actividadOptional.isPresent());
        assertEquals("Nombre cambiado", actividadOptional.get().getNombre());

        actividad.setNombre(oldName);
        actividadRep.update(actividad);
    }

    @Test
    void testFindAll() {
        Optional<Actividad> actividadOptional = actividadRep.findbyId(1);
        Optional<Actividad> actividadOptional2 = actividadRep.findbyId(2);
        List<Actividad> result = actividadRep.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(actividadOptional.get()));
        assertTrue(result.contains(actividadOptional2.get()));
    }
}
