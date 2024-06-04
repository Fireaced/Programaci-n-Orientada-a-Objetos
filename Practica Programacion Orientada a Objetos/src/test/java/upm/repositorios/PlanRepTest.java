package upm.repositorios;

import org.junit.jupiter.api.Test;
import upm.DependencyInjector;
import upm.clases.Plan;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PlanRepTest {
    private final PlanRep planRep = DependencyInjector.getDependencyInjector().getPlanRep();

    @Test
    void testCreateAndRead() {
        Optional<Plan> plan = this.planRep.read(1);
        assertTrue(plan.isPresent());
        assertEquals("AAA", plan.get().getNombre());
    }

    @Test
    void testFindById() {
        Optional<Plan> plan = planRep.findById(1);
        assertTrue(plan.isPresent());
        assertEquals("AAA", plan.get().getNombre());
    }

    @Test
    void testFindByIdNotFound() {
        assertFalse(planRep.findById(-3).isPresent());
    }

    @Test
    void testUpdate() {
        Plan plan = this.planRep.read(2).get();
        String oldName = plan.getNombre();
        plan.setNombre("Nombre cambiado");
        planRep.update(plan);

        Optional<Plan> planOptional = planRep.read(2);
        assertTrue(planOptional.isPresent());
        assertEquals("Nombre cambiado", planOptional.get().getNombre());

        plan.setNombre(oldName);
        planRep.update(plan);
    }

    @Test
    void testFindAll() {
        Optional<Plan> planOptional = planRep.findById(2);
        Optional<Plan> planOptional2 = planRep.findById(3);
        List<Plan> result = planRep.findAll();
        assertEquals(8, result.size());
        assertTrue(result.contains(planOptional.get()));
        assertTrue(result.contains(planOptional2.get()));
    }
}

