package upm.repositorios;


import org.junit.jupiter.api.Test;
import upm.DependencyInjector;
import upm.clases.Usuario;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class UsuarioRepTest {
    private final UsuarioRep userRepository = DependencyInjector.getDependencyInjector().getUsuarioRep();

    @Test
    void testCreateAndRead() {
        Optional<Usuario> user = this.userRepository.read(1);
        assertTrue(user.isPresent());
        assertEquals("Juan", user.get().getNombre());
    }

    @Test
    void testFindByName() {
        Optional<Usuario> foundUser = userRepository.findByName("Juan");
        assertTrue(foundUser.isPresent());
        assertEquals("Juan", foundUser.get().getNombre());
    }

    @Test
    void testFindByNameNotFound() {
        assertFalse(userRepository.findByName("Federico").isPresent());
    }

    @Test
    void testUpdate() {
        Usuario usuario = this.userRepository.read(2).get();
        String oldName = usuario.getNombre();
        usuario.setNombre("Nombre cambiado");
        userRepository.update(usuario);

        Optional<Usuario> retrievedUser = userRepository.read(2);
        assertTrue(retrievedUser.isPresent());
        assertEquals("Nombre cambiado", retrievedUser.get().getNombre());

        usuario.setNombre(oldName);
        userRepository.update(usuario);
    }

    @Test
    void testFindAll() {
        Optional<Usuario> usuarioOptional = userRepository.findByName("Juan");
        Optional<Usuario> usuarioOptional2 = userRepository.findByName("Rigoberto");
        List<Usuario> result = userRepository.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(usuarioOptional2.get()));
        assertTrue(result.contains(usuarioOptional.get()));
    }
}
