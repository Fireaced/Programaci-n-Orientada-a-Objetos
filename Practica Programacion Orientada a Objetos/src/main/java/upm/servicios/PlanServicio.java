package upm.servicios;

import upm.clases.Actividad;
import upm.clases.Plan;
import upm.clases.Usuario;
import upm.excepciones.InvalidAtrributeExcepcion;
import upm.excepciones.NonExistentAtributeException;
import upm.repositorios.ActividadRep;
import upm.repositorios.PlanRep;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PlanServicio {
    private final PlanRep planSocialRep;
    private final ActividadRep actividadRep;

    public PlanServicio(PlanRep planSocialRep, ActividadRep actividadRep) {
        this.planSocialRep = planSocialRep;
        this.actividadRep = actividadRep;
    }

    public Plan crear(Plan planSocial, Usuario usuarioLogueado) {
        if (planSocialRep.findById(planSocial.getId()).isPresent()) {
            throw new InvalidAtrributeExcepcion("El nombre deberia ser único y " + planSocial.getNombre() + " ya existe");
        }

        planSocial.setNombreCreador(usuarioLogueado.getNombre());
        return this.planSocialRep.create(planSocial);
    }

    public void eliminar(Integer planId, Usuario usuarioLogueado) {
        if (planSocialRep.findById(planId).isPresent()) {
            throw new NonExistentAtributeException("Ese Plan no existe");
        }
        Plan plan = planSocialRep.findById(planId).get();
        if (!plan.getNombreCreador().equals(usuarioLogueado.getNombre())) {
            throw new InvalidAtrributeExcepcion("No eres el creador");
        }
        planSocialRep.deleteById(planId);
    }

    public void addParticipante(Integer planSocialId, Usuario usuarioLogueado) {
        if (!planSocialRep.findById(planSocialId).isPresent()) {
            throw new NonExistentAtributeException("No existe ese plan");
        }
        Plan planSocial = planSocialRep.findById(planSocialId).get();

        if (planSocial.getListaparticipantes().contains(usuarioLogueado) || solapanPlanes(planSocial, usuarioLogueado) || planPasado(planSocial)) {
            throw new InvalidAtrributeExcepcion("El usuario ya esta suscrito a esa actividad, es una actividad pasada o se solapa con otra");
        }
        planSocial.aniadirparticipante(usuarioLogueado);
    }

    public LinkedList<Plan> ListaPlanesSuscritos(Usuario usuario) {
        LinkedList<Plan> listaPlanesSuscritos = new LinkedList<>();
        LinkedList<Plan> listaplanes = planSocialRep.findAll();
        for (Plan plan : listaplanes) {
            if (plan.getListaparticipantes().contains(usuario)) {
                listaPlanesSuscritos.add(plan);
            }
        }
        return listaPlanesSuscritos;
    }

    public void abandonarPlan(Integer planSocialId, Usuario usuarioLogueado) {
        if (!planSocialRep.findById(planSocialId).isPresent()) {
            throw new NonExistentAtributeException("No existe ese plan");
        }
        Plan planSocial = planSocialRep.findById(planSocialId).get();
        if (planPasado(planSocial)) {
            throw new InvalidAtrributeExcepcion("Es un plan del pasado, no te puedes desapuntar");
        }
        if (!planSocial.getListaparticipantes().contains(usuarioLogueado)) {
            throw new InvalidAtrributeExcepcion("No estas apuntado a ese plan");
        }
        planSocial.borrarParticipante(usuarioLogueado);
    }

    private boolean solapanPlanes(Plan planSocial, Usuario usuario) {
        Iterator<Plan> iterator = ListaPlanesSuscritos(usuario).iterator();
        boolean solapa = false;
        LocalTime FinPlan = planSocial.getHora().plusMinutes(planSocial.tiempoTotal());
        while (iterator.hasNext() && !solapa) {
            Plan actual = iterator.next();
            LocalTime FinPlanAct = actual.getHora().plusMinutes(actual.tiempoTotal());
            if (planSocial.getFecha().equals(actual.getFecha())) {
                if (FinPlan.isAfter(actual.getHora()) && planSocial.getHora().isBefore(FinPlanAct)) {
                    solapa = true;
                }
            }
        }
        return solapa;
    }

    public boolean planPasado(Plan planSocial) {
        if (planSocial.getFecha().isBefore(LocalDate.now())) {
            return true;
        } else return planSocial.getFecha().equals(LocalDate.now()) && planSocial.getHora().isBefore(LocalTime.now());
    }

    public List<Plan> listaPlanesDisponiblesAux(Usuario usuario) {
        return planSocialRep.findAll().stream()
                .filter(plan -> !plan.getListaparticipantes().contains(usuario) && !planPasado(plan))
                .collect(Collectors.toList());
    }

    public List<Plan> listaPlanesPasados() {
        return planSocialRep.findAll().stream()
                .filter(this::planPasado)
                .collect(Collectors.toList());
    }

    public void puntuarPlanSocial(Integer idPlan, float puntuacion, Usuario usuarioLogueado) {
        if (!planSocialRep.findById(idPlan).isPresent()) {
            throw new NonExistentAtributeException("El plan no exite");
        }
        if (!planPasado(planSocialRep.findById(idPlan).get())) {
            throw new InvalidAtrributeExcepcion("No puedes puntuar un plan que no ha pasado");
        }
        if (!planSocialRep.findById(idPlan).get().getListaparticipantes().contains(usuarioLogueado)) {
            throw new InvalidAtrributeExcepcion("No puedes puntuar un plan en el que no participaste");
        }
        Plan plan = planSocialRep.findById(idPlan).get();
        plan.puntuar(puntuacion, usuarioLogueado.getNombre());
    }

    public List<Plan> ordenarPorPuntuacion(Usuario usuarioLogueado) {
        List<Plan> listaPlanes = listaPlanesDisponiblesAux(usuarioLogueado);
        List<Plan> listaOrdenada = new LinkedList<>();
        if (listaPlanes.isEmpty()) {
            throw new NonExistentAtributeException("No hay planes disponibles");
        }
        while (!listaPlanes.isEmpty()) {
            String peor = mejorUsuario(listaPlanes);
            ordenarPlanesUsuario(peor, listaOrdenada, listaPlanes);
        }
        return listaOrdenada;
    }

    private String mejorUsuario(List<Plan> listaPlanes) {
        String peor = null;
        for (Plan plan : listaPlanes) {
            if (peor == null) {
                peor = plan.getNombreCreador();
            } else if (getNotaMediaUsuario(plan.getNombreCreador()) > getNotaMediaUsuario(peor)) {
                peor = plan.getNombreCreador();
            }
        }
        return peor;
    }

    public float getNotaMediaUsuario(String nombreCreador) {
        List<Plan> planesPasados = listaPlanesPasados();
        float notaMedia = 0;
        int numPlanes = 0;
        for (Plan plan : planesPasados) {
            if (plan.getNombreCreador().equals(nombreCreador)) {
                numPlanes++;
                notaMedia += plan.puntuacionMedia();
            }
        }
        return notaMedia / numPlanes;
    }

    private void ordenarPlanesUsuario(String nombreUsuario, List<Plan> listaPlanesOrdenado, List<Plan> listaPlanes) {
        List<Plan> listaaux = new LinkedList<>();
        for (Plan plan : listaPlanes) {
            if (plan.getNombreCreador().equals(nombreUsuario)) {
                listaPlanesOrdenado.add(plan);
                listaaux.add(plan);
            }
        }
        for (Plan plan : listaaux) {
            listaPlanes.remove(plan);
        }
    }

    public List<Plan> ordenarPorFecha(Usuario usuarioLogueado) {
        List<Plan> listaPlanes = listaPlanesDisponiblesAux(usuarioLogueado);
        if (listaPlanes.isEmpty()) {
            throw new NonExistentAtributeException("No hay planes disponibles");

        }
        return listaPlanes.stream()
                .sorted(Comparator.comparing(Plan::getFecha).reversed()
                        .thenComparing(Plan::getHora))
                .collect(Collectors.toList());
    }

    public void addActividad(Integer actividadId, Integer planSocialId, Usuario usuarioLogueado) {
        if (planSocialRep.findById(planSocialId).isPresent() || actividadRep.findbyId(actividadId).isPresent()) {
            throw new NonExistentAtributeException("El plan o la actividad no existe");
        }
        Plan planSocial = planSocialRep.findById(planSocialId).get();
        Actividad actividad = actividadRep.findbyId(actividadId).get();
        if (planSocial.getListactividades().contains(actividad)) {
            throw new InvalidAtrributeExcepcion("La actividad ya esta incluida en este plan");
        }
        if (!usuarioLogueado.getNombre().equals(planSocial.getNombreCreador())) {
            throw new InvalidAtrributeExcepcion("No eres el creador del plan");
        }
        planSocial.aniadiractividad(actividad);
    }

    public double costeTotal(Integer planSocialId, Usuario usuarioLogueado) {
        if (planSocialRep.findById(planSocialId).isEmpty()) {
            throw new NonExistentAtributeException("No existe ese plan");
        }
        Plan planSocial = planSocialRep.findById(planSocialId).get();
        double CostePlan = 0;

        for (Actividad actividad : planSocial.getListactividades()) {
            CostePlan += actividad.aplicarDescuento(usuarioLogueado.getEdad());
        }
        return CostePlan;
    }
}
