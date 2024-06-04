package upm.comandos;


import upm.Comando;
import upm.servicios.PlanServicio;

public class UnirsePlan implements Comando {

    private static final String NAME = "unirse_plan";
    private static final String HELP = ":<idPlan> Te unes al plan que indiques, necesitas estar logueado";
    private final PlanServicio planServicio;

    public UnirsePlan(PlanServicio planServicio) {
        this.planServicio = planServicio;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        planServicio.addParticipante(Integer.valueOf(values[0]), LogIn.getUsuarioLogueado());
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
