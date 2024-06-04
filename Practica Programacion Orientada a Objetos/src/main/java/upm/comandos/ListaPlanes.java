package upm.comandos;


import upm.Comando;
import upm.consola.View;
import upm.excepciones.InvalidAtrributeExcepcion;
import upm.servicios.PlanServicio;

public class ListaPlanes implements Comando {
    private final View view;
    private static final String NAME = "lista_planes";
    private static final String HELP = "<fecha> <Puntuacion> Puedes ordenar los planes disponibles por puntuacion o por fecha";
    private final PlanServicio planServicio;

    public ListaPlanes(PlanServicio planServicio, View view) {
        this.planServicio = planServicio;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        switch (values[0]) {
            case "puntuacion":
                System.out.println("Mostrando planes disponibles ordenados por la puntuacion del autor");
                view.show(planServicio.ordenarPorPuntuacion(LogIn.getUsuarioLogueado()).toString());
                break;
            case "fecha":
                System.out.println("Mostrando planes disponibles ordenados por fecha");
                view.show(planServicio.ordenarPorFecha(LogIn.getUsuarioLogueado()).toString());
                break;
            case "":
                System.out.println("Mostrando planes disponibles");
                view.show(planServicio.listaPlanesDisponiblesAux(LogIn.getUsuarioLogueado()).toString());
                break;
            default:
                throw new InvalidAtrributeExcepcion("Valor Incorrecto");
        }
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
