package upm.comandos;


import upm.Comando;
import upm.servicios.PlanServicio;

public class PuntuarPlan implements Comando {

    private static final String NAME = "puntuar_plan";
    private static final String HELP = ":<idPlan>;<Puntuacion> Solo se puede puntuar una vez cada plan";
    private final PlanServicio planServicio;

    public PuntuarPlan(PlanServicio planServicio) {
        this.planServicio = planServicio;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("Inicia sesion primero");
        }
        if (Float.valueOf(values[1]) < 0 || Float.valueOf(values[1]) > 10) {
            throw new IllegalArgumentException("La puntuacion debe estar entre 0 y 10");
        }
        planServicio.puntuarPlanSocial(Integer.valueOf(values[0]), Float.valueOf(values[1]), LogIn.getUsuarioLogueado());
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
