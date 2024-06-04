package upm.repositorios;

import upm.clases.Usuario;

import java.util.Optional;

public interface UsuarioRep extends RepGenerico<Usuario> {
    Optional<Usuario> findByName(String nombre);

    Optional<Usuario> findByMobile(Integer movil);
}
