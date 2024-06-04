package upm.comandos;


import upm.Comando;
import upm.consola.View;
import upm.servicios.PlanServicio;

public class AbandonarPlan implements Comando {

    private static final String NAME = "abandonar_plan";
    private static final String HELP = ":<IdPlan> Te desapunta de un plan, al que estes apuntado";
    private final PlanServicio planServicio;

    public AbandonarPlan(PlanServicio planServicio) {
        this.planServicio = planServicio;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        planServicio.abandonarPlan(Integer.valueOf(values[0]), LogIn.getUsuarioLogueado());
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
