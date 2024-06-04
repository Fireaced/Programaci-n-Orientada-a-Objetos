package upm.comandos;


import upm.Comando;
import upm.consola.View;
import upm.servicios.UsuarioServicio;

public class UsuariosApuntadosA implements Comando {
    private final View view;
    private static final String NAME = "usarios-apuntados-a";
    private static final String HELP = ":<NumPlanes> Te devuelve una lista de ususarios que esten apuntados a mas de N planes o menos";
    private static UsuarioServicio usuarioServicio;

    public UsuariosApuntadosA(UsuarioServicio usuarioServicio, View view) {
        UsuariosApuntadosA.usuarioServicio = usuarioServicio;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        view.show(usuarioServicio.usuariosApuntadosA(Integer.valueOf(values[0])).toString());
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        return HELP;
    }

}
