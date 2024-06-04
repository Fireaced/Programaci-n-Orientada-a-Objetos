package upm.comandos;


import upm.Comando;
import upm.servicios.PlanServicio;

public class EliminarPlan implements Comando {

    private static final String NAME = "eliminar_plan";
    private static final String HELP = "<IdPlan>; Debes ser el dueño del plan para elimninarlo";

    private final PlanServicio planServicio;

    public EliminarPlan(PlanServicio planServicio) {
        this.planServicio = planServicio;

    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        planServicio.eliminar(Integer.valueOf(values[0]), LogIn.getUsuarioLogueado());
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
