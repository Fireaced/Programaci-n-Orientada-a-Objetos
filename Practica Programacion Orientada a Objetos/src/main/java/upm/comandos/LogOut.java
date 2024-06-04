package upm.comandos;


import upm.Comando;
import upm.consola.View;

public class LogOut implements Comando {

    private static final String NAME = "logout";
    private static final String HELP = "Cierra sesion de dicha persona";
    private final View view;

    public LogOut(View view) {
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        LogIn.setUsuarioLogueado(null);
        view.showBold("Has cerrado sesion correctamente");
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
