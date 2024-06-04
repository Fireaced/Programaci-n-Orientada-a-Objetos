package upm;

import upm.comandos.*;
import upm.consola.CommandLineInterface;
import upm.consola.ErrorHandler;
import upm.consola.View;
import upm.repositorios.ActividadRep;
import upm.repositorios.MapasRep.ActividadRepMap;
import upm.repositorios.MapasRep.PlanSocialRepMap;
import upm.repositorios.MapasRep.UsuarioRepMap;
import upm.repositorios.PlanRep;
import upm.repositorios.UsuarioRep;
import upm.servicios.ActividadServicio;
import upm.servicios.PlanServicio;
import upm.servicios.UsuarioServicio;

public class DependencyInjector {
    private static final DependencyInjector dependencyInjector = new DependencyInjector();
    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UsuarioRep usuarioRep;
    private final PlanRep planRep;
    private final ActividadRep actividadRep;
    private final UsuarioServicio usuarioServicio;
    private final PlanServicio planServicio;
    private final ActividadServicio actividadServicio;

    private final Poblador poblador;

    private DependencyInjector() {
        this.view = new View();

        this.usuarioRep = new UsuarioRepMap();
        this.planRep = new PlanSocialRepMap();
        this.actividadRep = new ActividadRepMap();

        this.usuarioServicio = new UsuarioServicio(usuarioRep, planRep);
        this.planServicio = new PlanServicio(planRep, actividadRep);
        this.actividadServicio = new ActividadServicio(actividadRep, planRep);

        this.commandLineInterface = new CommandLineInterface(view);

        this.errorHandler = new ErrorHandler(this.commandLineInterface, this.view);

        commandLineInterface.add(new AbandonarPlan(planServicio));
        commandLineInterface.add(new ActividadesEntre(actividadServicio, view));
        commandLineInterface.add(new ActividadesPosibles(actividadServicio, view));
        commandLineInterface.add(new AddActivityPlan(planServicio));
        commandLineInterface.add(new CalcularCoste(planServicio));
        commandLineInterface.add(new CrearPlan(view, planServicio));
        commandLineInterface.add(new CreateActivity(view, actividadServicio));
        commandLineInterface.add(new EliminarPlan(planServicio));
        commandLineInterface.add(new ListaPlanes(planServicio, view));
        commandLineInterface.add(new ListaPlanesSuscritos(planServicio, view));
        commandLineInterface.add(new LogIn(view, usuarioServicio));
        commandLineInterface.add(new LogOut(view));
        commandLineInterface.add(new PuntuarPlan(planServicio));
        commandLineInterface.add(new UnirsePlan(planServicio));
        commandLineInterface.add(new UsuariosApuntadosA(usuarioServicio, view));
        commandLineInterface.add(new CreateUser(view, usuarioServicio));


        this.poblador = new Poblador(usuarioRep, planRep, actividadRep);
        poblador.seed();
    }

    public static DependencyInjector getDependencyInjector() {
        return dependencyInjector;
    }

    public void run() {
        this.errorHandler.handlesErrors();
    }

    public UsuarioRep getUsuarioRep() {
        return this.usuarioRep;
    }

    public ActividadRep getActividadRep() {
        return this.actividadRep;
    }

    public PlanRep getPlanRep() {
        return this.planRep;
    }

    public UsuarioServicio getUsuarioServicio() {
        return this.usuarioServicio;
    }

    public PlanServicio getPlanServicio() {
        return this.planServicio;
    }


}
