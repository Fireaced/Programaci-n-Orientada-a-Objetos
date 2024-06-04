package upm.comandos;

import upm.Comando;
import upm.clases.Plan;
import upm.consola.View;
import upm.servicios.PlanServicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CrearPlan implements Comando {

    private static final String NAME = "crear-plan";
    private static final String HELP = ":<nombre>;<descripcion>;<fecha>;<lugar>;<capacidad> Se crea un plan, capacidad es opcional";
    private final PlanServicio planServicio;
    private final View view;

    public CrearPlan(View view, PlanServicio planServicio) {
        this.planServicio = planServicio;
        this.view = view;
    }

    @Override
    public void execute(String[] values) {
        if (!LogIn.isSesionIniciada()) {
            throw new IllegalArgumentException("No has iniciado sesion");
        }
        Plan crearPlan;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaComoCadena = values[1];
        LocalDate fechaParseada = LocalDate.parse(fechaComoCadena, formatter);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH-mm");
        String horaComoCadena = values[2];
        LocalTime horaParseada = LocalTime.parse(horaComoCadena, formatter2);
        if (values.length == 4) {
            crearPlan = this.planServicio.crear(new Plan(values[0], fechaParseada, horaParseada, values[3]), LogIn.getUsuarioLogueado());
        } else if (values.length == 5) {
            crearPlan = this.planServicio.crear(new Plan(values[0], fechaParseada, horaParseada, values[3], Integer.valueOf(values[4])), LogIn.getUsuarioLogueado());
        } else {
            throw new IllegalArgumentException(help());
        }

        this.view.show(crearPlan.toString());
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
