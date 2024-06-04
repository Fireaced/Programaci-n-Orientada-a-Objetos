package upm.comandos;


import upm.Comando;
import upm.servicios.PlanServicio;

public class AddActivityPlan implements Comando {

    private static final String NAME = "add_activity_plan";
    private static final String HELP = ":<idActividad>;<idPlan> Añade una actividad a un plan, se necesita estar logueado";
    private final PlanServicio planServicio;

    public AddActivityPlan(PlanServicio planServicio) {
        this.planServicio = planServicio;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        planServicio.addActividad(Integer.valueOf(values[0]), Integer.valueOf(values[1]), LogIn.getUsuarioLogueado());
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
