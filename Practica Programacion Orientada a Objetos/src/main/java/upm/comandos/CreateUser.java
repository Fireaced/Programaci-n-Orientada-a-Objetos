package upm.comandos;


import upm.Comando;
import upm.clases.Usuario;
import upm.consola.View;
import upm.servicios.UsuarioServicio;

public class CreateUser implements Comando {

    private static final String NAME = "usarios-apuntados-a";
    private static final String HELP = ":<NumPlanes> Te devuelve una lista de ususarios que esten apuntados a mas de N planes o menos";
    private static UsuarioServicio usuarioServicio;
    private final View view;

    public CreateUser(View view, UsuarioServicio usuarioServicio) {
        this.view = view;
        CreateUser.usuarioServicio = usuarioServicio;
    }

    @Override
    public void execute(String[] values) {
        if (values.length != 4) {
            throw new IllegalArgumentException(help());
        }
        Usuario crearUsuario = Usuario.builder().nombre(values[0]).contrasenia(values[1]).edad(Integer.valueOf(values[2])).numero(Integer.valueOf(values[3])).build();
        usuarioServicio.create(crearUsuario);
        this.view.show(crearUsuario.toString());

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
