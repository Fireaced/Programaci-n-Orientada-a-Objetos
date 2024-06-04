package upm.comandos;


import upm.Comando;
import upm.consola.View;
import upm.servicios.PlanServicio;

public class ListaPlanesSuscritos implements Comando {
    private final View view;
    private static final String NAME = "listar_planes_suscritos";
    private static final String HELP = "Te da una lista de los planes a los que estas suscritos";
    private final PlanServicio planServicio;

    public ListaPlanesSuscritos(PlanServicio planServicio, View view) {
        this.planServicio = planServicio;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        view.show(planServicio.ListaPlanesSuscritos(LogIn.getUsuarioLogueado()).toString());
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
