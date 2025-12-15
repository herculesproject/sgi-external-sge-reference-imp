package org.crue.hercules.sgi.sge.repository.specification;

import java.time.Instant;

import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto_;
import org.springframework.data.jpa.domain.Specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PartidaPresupuestariaGastoSpecifications {

  /**
   * {@link PartidaPresupuestariaGasto} activas en una fecha
   * 
   * @param fecha una fecha
   * @return specification para obtener los {@link PartidaPresupuestariaGasto} que
   *         cumplan con el filtro de busqueda.
   */
  public static Specification<PartidaPresupuestariaGasto> activas(Instant fecha) {
    return (root, query, cb) -> cb.and(
        cb.or(cb.isNull(root.get(PartidaPresupuestariaGasto_.fechaInicio)),
            cb.lessThanOrEqualTo(root.get(PartidaPresupuestariaGasto_.fechaInicio), fecha)),
        cb.or(cb.isNull(root.get(PartidaPresupuestariaGasto_.fechaFin)),
            cb.greaterThanOrEqualTo(root.get(PartidaPresupuestariaGasto_.fechaFin), fecha)));
  }

}
