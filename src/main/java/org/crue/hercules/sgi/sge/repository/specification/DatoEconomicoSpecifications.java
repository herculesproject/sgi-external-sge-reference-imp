package org.crue.hercules.sgi.sge.repository.specification;

import java.util.List;

import org.crue.hercules.sgi.sge.enums.EjecucionEconomicaTipoOperacionEnum;
import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.springframework.data.jpa.domain.Specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatoEconomicoSpecifications {

  public static Specification<DatoEconomico> proyectoId(String proyectoId) {
    return (root, query, cb) -> proyectoId == null ? cb.disjunction() : cb.equal(root.get("proyectoId"), proyectoId);
  }

  public static Specification<DatoEconomico> tipoOperacion(EjecucionEconomicaTipoOperacionEnum tipo) {
    return (root, query, cb) -> tipo == null ? null : cb.equal(root.get("tipoOperacion"), tipo);
  }

  public static Specification<DatoEconomico> anualidadesIn(List<Integer> anualidades) {
    return (root, query, cb) -> (anualidades == null || anualidades.isEmpty()) ? null
        : root.get("anualidad").in(anualidades);
  }
}
