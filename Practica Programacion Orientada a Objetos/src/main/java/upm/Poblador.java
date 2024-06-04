package upm;

import upm.clases.Actividad;
import upm.clases.Plan;
import upm.clases.TipoActividades.Cine;
import upm.clases.TipoActividades.Teatro;
import upm.clases.Usuario;
import upm.consola.View;
import upm.repositorios.ActividadRep;
import upm.repositorios.PlanRep;
import upm.repositorios.UsuarioRep;
import upm.servicios.PlanServicio;
import upm.servicios.UsuarioServicio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Poblador {
    public PlanRep planSocialRep;
    public UsuarioRep usuarioRep;
    public ActividadRep actividadRep;
    public View view;

    public void seed() {
        Usuario[] usuarios = {
                Usuario.builder().nombre("Juan").contrasenia("Juan123").edad(20).numero(574369428).build(),
                Usuario.builder().nombre("Rigoberto").contrasenia("12345").edad(70).numero(426874236).build(),
        };
        for (Usuario usuario : usuarios) {
            this.usuarioRep.create(usuario);
        }

        Actividad[] actividades = {
                new Cine("Escondite", "Pues un escondite", 60, 20, 100),
                new Teatro("PillaPilla", "Pues un escondite", 70, 100, 100),
        };
        for (Actividad actividad : actividades) {
            this.actividadRep.create(actividad);
        }
        Plan[] planes = {
                new Plan("AAA", LocalDate.of(2024, 10, 15), LocalTime.of(9, 00), "Los patos", 100000),
                new Plan("AAB", LocalDate.of(2024, 11, 20), LocalTime.of(15, 00), "Los patos", 10000),
                new Plan("AAC", LocalDate.of(2024, 10, 10), LocalTime.of(10, 00), "Los patos", 10000),
                new Plan("AAD", LocalDate.of(2023, 10, 15), LocalTime.of(10, 00), "Los patos", 1000),
                new Plan("AAE", LocalDate.of(2021, 10, 15), LocalTime.of(9, 00), "Los patos", 100000),
                new Plan("AAF", LocalDate.of(2021, 11, 20), LocalTime.of(15, 00), "Los patos", 10000),
                new Plan("AAG", LocalDate.of(2020, 10, 10), LocalTime.of(10, 00), "Los patos", 10000),
                new Plan("AAH", LocalDate.of(2020, 10, 15), LocalTime.of(10, 00), "Los patos", 1000),
        };
        PlanServicio planServicio = new PlanServicio(planSocialRep, actividadRep);
        UsuarioServicio usuarioServicio = new UsuarioServicio(usuarioRep, planSocialRep);
        planServicio.crear(planes[0], usuarios[1]);
        planServicio.crear(planes[1], usuarios[0]);

        planServicio.crear(planes[2], usuarios[1]);
        planServicio.crear(planes[3], usuarios[1]);
        planServicio.crear(planes[4], usuarios[0]);
        planServicio.crear(planes[5], usuarios[0]);
        planServicio.crear(planes[6], usuarios[0]);
        planServicio.crear(planes[7], usuarios[0]);

        planes[0].setPuntuacion(2, usuarios[0].getNombre());
        planes[1].setPuntuacion(5, usuarios[0].getNombre());
        planes[2].setPuntuacion(7, usuarios[0].getNombre());
        planes[3].setPuntuacion(8, usuarios[0].getNombre());
        planes[4].setPuntuacion(10, usuarios[0].getNombre());
        planes[5].setPuntuacion(10, usuarios[0].getNombre());

        planes[0].setPuntuacion(2, usuarios[1].getNombre());
        planes[1].setPuntuacion(10, usuarios[1].getNombre());

    }

    public Poblador(UsuarioRep usuarioRep, PlanRep planRep, ActividadRep actividadRep) {
        this.usuarioRep = usuarioRep;
        this.planSocialRep = planRep;
        this.actividadRep = actividadRep;
    }

}
//new ShopSeeder(this.articleRepository, this.shoppingCartRepository, this.tagRepository, this.userRepository);