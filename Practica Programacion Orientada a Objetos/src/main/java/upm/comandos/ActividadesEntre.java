package upm.comandos;


import upm.Comando;
import upm.consola.View;
import upm.servicios.ActividadServicio;

public class ActividadesEntre implements Comando {
    private final View view;
    private static final String NAME = "actividades_entre";
    private static final String HELP = ":<PrecioMin><PrecioMax><Y/N>(Para aplicar o no tu descuento a las actividades)";
    private static ActividadServicio actividadServicio;

    public ActividadesEntre(ActividadServicio actividadServicio, View view) {
        this.actividadServicio = actividadServicio;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        switch (values[2]) {
            case "Y":
                if (!LogIn.isSesionIniciada()) {
                    throw new IllegalArgumentException("Inicia sesion primero");
                }
                view.show(actividadServicio.intervaloCosteDescuento(Integer.valueOf(values[0]), Integer.valueOf(values[1]), LogIn.getUsuarioLogueado()).toString());
                break;
            case "N":
                view.show(actividadServicio.intervaloCosteNormal(Integer.valueOf(values[0]), Integer.valueOf(values[1])).toString());
                break;
            default:
                throw new IllegalArgumentException("SOLO SE ADMITE Y O N");
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
