package upm.repositorios.MapasRep;

import upm.clases.Usuario;
import upm.repositorios.UsuarioRep;

import java.util.*;

public class UsuarioRepMap extends RepGenericoMap<Usuario> implements UsuarioRep {


    @Override
    protected Integer getId(Usuario entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Usuario entity, Integer id) {

    }

    @Override
    public Optional<Usuario> findByName(String nombre) {
        for (Usuario usuario : this.findAll()) {
            if (usuario.getNombre().equals(nombre)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }
    @Override
    public Optional<Usuario> findByMobile(Integer movil) {
        for (Usuario usuario : this.findAll()) {
            if (usuario.getNumero() == (movil)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }
}
