package upm.comandos;


import upm.Comando;
import upm.servicios.PlanServicio;

public class CalcularCoste implements Comando {

    private static final String NAME = "calcular_coste";
    private static final String HELP = ":<idPlan> Calcula el coste de un plan para el usuario logueado";

    private final PlanServicio planServicio;

    public CalcularCoste(PlanServicio planServicio) {
        this.planServicio = planServicio;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        System.out.println(planServicio.costeTotal(Integer.valueOf(values[0]), LogIn.getUsuarioLogueado()));
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
