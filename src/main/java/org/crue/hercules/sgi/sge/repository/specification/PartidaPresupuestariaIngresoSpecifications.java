package org.crue.hercules.sgi.sge.repository.specification;

import java.time.Instant;

import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso_;
import org.springframework.data.jpa.domain.Specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PartidaPresupuestariaIngresoSpecifications {

  /**
   * {@link PartidaPresupuestariaIngreso} activas en una fecha
   * 
   * @param fecha una fecha
   * @return specification para obtener los {@link PartidaPresupuestariaIngreso}
   *         que cumplan con el filtro de busqueda.
   */
  public static Specification<PartidaPresupuestariaIngreso> activas(Instant fecha) {
    return (root, query, cb) -> cb.and(
        cb.or(cb.isNull(root.get(PartidaPresupuestariaIngreso_.fechaInicio)),
            cb.lessThanOrEqualTo(root.get(PartidaPresupuestariaIngreso_.fechaInicio), fecha)),
        cb.or(cb.isNull(root.get(PartidaPresupuestariaIngreso_.fechaFin)),
            cb.greaterThanOrEqualTo(root.get(PartidaPresupuestariaIngreso_.fechaFin), fecha)));
  }

}
