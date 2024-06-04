package upm.repositorios;

import upm.clases.Plan;

import java.util.Optional;

public interface PlanRep extends RepGenerico<Plan> {
    Optional<Plan> findById(Integer id);
}
