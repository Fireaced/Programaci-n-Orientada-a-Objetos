package upm.servicios;

import org.junit.jupiter.api.Test;
import upm.DependencyInjector;
import upm.clases.Usuario;
import upm.excepciones.InvalidAtrributeExcepcion;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class usuarioServicioTest {
    private final UsuarioServicio usuarioServicio = DependencyInjector.getDependencyInjector().getUsuarioServicio();

    @Test
    void testCreateUserException() {
        assertThrows(InvalidAtrributeExcepcion.class,
                () -> this.usuarioServicio.create(Usuario.builder().nombre("Juan").contrasenia("123456").edad(24).numero(123344).build()));
    }

    @Test
    void TestLogin() {
       Usuario usuarioTest = Usuario.builder().nombre("Gema").contrasenia("123456").edad(24).numero(12345678).build();
       usuarioServicio.create(usuarioTest);
        assertEquals(usuarioTest, usuarioServicio.login("Gema", "123456"));
    }

    @Test
    void TestUsuariosapuntadosA() {
        List<Usuario> usuarios = usuarioServicio.usuariosApuntadosA(10);
        assertEquals(usuarios.size(), 0);
    }
}

