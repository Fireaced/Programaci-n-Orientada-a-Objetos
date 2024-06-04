package upm.servicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upm.DependencyInjector;
import upm.clases.Plan;
import upm.clases.Usuario;
import upm.excepciones.InvalidAtrributeExcepcion;
import upm.excepciones.NonExistentAtributeException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class planServicioTest {
    Usuario usuario;
    private final PlanServicio planServicio = DependencyInjector.getDependencyInjector().getPlanServicio();

    @BeforeEach
    void before() {
       usuario = Usuario.builder().nombre("Gema").contrasenia("123456").numero(24).numero(123344).build();
    }

    @Test
    void testElimnarPlanException() {
        assertThrows(NonExistentAtributeException.class, () -> this.planServicio.eliminar(1, usuario));
    }

    @Test
    void testAddParticipanteException() {
        assertThrows(NonExistentAtributeException.class, () -> this.planServicio.addParticipante(10, usuario));
    }

    @Test
    void testAbandonarPlanException() {
            assertThrows(InvalidAtrributeExcepcion.class, () -> this.planServicio.abandonarPlan(4, usuario));
    }

    @Test
    void TestPlanPasado() {
        assertTrue(planServicio.planPasado(new Plan("AAA", LocalDate.of(2000, 10, 10), LocalTime.of(15, 00), "Parque", 43)));
        assertFalse(planServicio.planPasado(new Plan("AAA", LocalDate.of(2025, 10, 10), LocalTime.of(10, 00), "Parque")));
    }
}
