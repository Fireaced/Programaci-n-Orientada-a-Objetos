package upm.servicios;


import upm.clases.Plan;
import upm.clases.Usuario;
import upm.excepciones.IncorrectAtrributeExcepcion;
import upm.excepciones.InvalidAtrributeExcepcion;
import upm.repositorios.PlanRep;
import upm.repositorios.UsuarioRep;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UsuarioServicio {
    private final UsuarioRep usuarioRep;
    private final PlanRep planSocialRep;

    public UsuarioServicio(UsuarioRep usuarioRep, PlanRep planSocialRep) {
        this.usuarioRep = usuarioRep;
        this.planSocialRep = planSocialRep;
    }

    public void create(Usuario usuario) {
        if (this.usuarioRep.findByName(usuario.getNombre()).isPresent()) {
            throw new InvalidAtrributeExcepcion("El nombre deberia ser único y " + usuario.getNombre() + " ya existe");
        }
        if (this.usuarioRep.findByMobile(usuario.getNumero()).isPresent()) {
            throw new InvalidAtrributeExcepcion("El numero deberia ser único y " + usuario.getNumero() + " ya existe");
        }
        this.usuarioRep.create(usuario);
    }

    public Usuario login(String nombre, String contrasenia) {
        if (!usuarioRep.findByName(nombre).isPresent() || !usuarioRep.findByName(nombre).get().getContrasenia().equals(contrasenia)) {
            throw new IncorrectAtrributeExcepcion("El nombre o la contraseña son incorrectos ");
        }
        return usuarioRep.findByName(nombre).get();
    }

    public List<Usuario> usuariosApuntadosA(int numPlanes) {
        List<Usuario> listaUsuarios = usuarioRep.findAll();
        List<Plan> listaPlanes = planSocialRep.findAll();
        List<Usuario> listaUsuariosSol = new LinkedList<>();
        for (Usuario usuario : listaUsuarios) {
            int numPlanesApuntados = planesApuntados(listaPlanes, usuario);
            if (numPlanesApuntados >= numPlanes) {
                listaUsuariosSol.add(usuario);
            }
        }
        return listaUsuariosSol;
    }

    private int planesApuntados(List<Plan> listaPlanes, Usuario usuario) {
        Iterator<Plan> planIterator = listaPlanes.iterator();
        int num = 0;
        while (planIterator.hasNext()) {
            Plan actual = planIterator.next();
            if (actual.getListaparticipantes().contains(usuario)) {
                num++;
            }
        }
        return num;
    }


}



