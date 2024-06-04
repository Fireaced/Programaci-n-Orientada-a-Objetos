package upm.comandos;


import upm.Comando;
import upm.clases.Usuario;
import upm.consola.View;
import upm.servicios.UsuarioServicio;

public class LogIn implements Comando {

    private static final String NAME = "login";
    private static final String HELP = ":<nombre>;<contraseña>? Logeas a la persona con dicho nombre";
    private final UsuarioServicio usuarioServicio;
    private final View view;
    private static Usuario usuarioLogueado;

    public LogIn(View view, UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
        this.view = view;
        usuarioLogueado = null;
    }

    @Override
    public void execute(String[] values) {
        usuarioLogueado = usuarioServicio.login(values[0], values[1]);
        view.showBold("Has iniciado sesion correctamente");
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        return HELP;
    }

    public static boolean isSesionIniciada() {
        return usuarioLogueado != null;
    }

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuarioLogueado) {
        LogIn.usuarioLogueado = usuarioLogueado;
    }
}
