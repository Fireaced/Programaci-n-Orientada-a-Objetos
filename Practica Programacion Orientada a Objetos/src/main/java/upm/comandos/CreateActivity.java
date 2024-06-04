package upm.comandos;


import upm.Comando;
import upm.clases.TipoActividades.Cine;
import upm.clases.TipoActividades.Generica;
import upm.clases.TipoActividades.Teatro;
import upm.consola.View;
import upm.servicios.ActividadServicio;

public class CreateActivity implements Comando {

    private static final String NAME = "create_activity";
    private static final String HELP = ":<Nombre>;<Tipo>;<Descripcion>;<Duracion>;<Coste>;<Aforo> Se crea una actividad, el aforo es opcional";
    private final View view;
    private final ActividadServicio actividadServicio;

    public CreateActivity(View view, ActividadServicio actividadServicio) {
        this.actividadServicio = actividadServicio;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (values.length == 6 || values.length == 5) {
            String[] newValues = new String[6];
            System.arraycopy(values, 0, newValues, 0, 5);
            if (values.length == 5) {
                newValues[5] = String.valueOf(Integer.MAX_VALUE);
            }
            switch (values[1]) {
                case "cine" -> {
                    Cine actividad = new Cine(newValues[0], newValues[2], Integer.valueOf(newValues[3]), Integer.valueOf(newValues[4]), Integer.valueOf(newValues[5]));
                    actividadServicio.create(actividad);
                    this.view.show(actividad.toString());
                }

                case "teatro" -> {
                    Teatro actividad = new Teatro(newValues[0], newValues[2], Integer.valueOf(newValues[3]), Integer.valueOf(newValues[4]), Integer.valueOf(newValues[5]));
                    actividadServicio.create(actividad);
                    this.view.show(actividad.toString());
                }
                case "generica" -> {
                    Generica actividad = new Generica(newValues[0], newValues[2], Integer.valueOf(newValues[3]), Integer.valueOf(newValues[4]), Integer.valueOf(newValues[5]));
                    actividadServicio.create(actividad);
                    this.view.show(actividad.toString());
                }
                default -> throw new IllegalArgumentException("No existe esa actividad");
            }
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
