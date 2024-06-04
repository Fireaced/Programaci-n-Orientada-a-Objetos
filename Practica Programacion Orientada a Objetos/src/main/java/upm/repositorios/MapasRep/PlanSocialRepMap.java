package upm.repositorios.MapasRep;

import upm.clases.Plan;
import upm.repositorios.PlanRep;

import java.util.Optional;

public class PlanSocialRepMap extends RepGenericoMap<Plan> implements PlanRep {
    @Override
    protected Integer getId(Plan entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Plan entity, Integer id) {
        entity.setId(id);
    }

    @Override
    public Optional<Plan> findById(Integer id) {
        for (Plan planSocial : this.findAll()) {
            if (planSocial.getId() == id) {
                return Optional.of(planSocial);
            }
        }
        return Optional.empty();
    }
}

