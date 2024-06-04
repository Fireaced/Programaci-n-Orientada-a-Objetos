package upm.comandos;


import upm.Comando;
import upm.consola.View;
import upm.servicios.ActividadServicio;

public class ActividadesPosibles implements Comando {
    private final View view;
    private static final String NAME = "actividades_posibles";
    private static final String HELP = ":<idPlan> Te devuelve una lista de actividades que puedes incluir en un plan, las que no se pasan del aforo de tu plan";
    private static ActividadServicio actividadServicio;

    public ActividadesPosibles(ActividadServicio actividadServicio, View view) {
        this.actividadServicio = actividadServicio;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("Inicia sesion primero");
        }
        view.show(actividadServicio.actividadesPosibles(Integer.valueOf(values[0])).toString());
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
