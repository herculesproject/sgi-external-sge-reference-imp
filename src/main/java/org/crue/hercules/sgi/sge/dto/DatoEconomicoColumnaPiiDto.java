package org.crue.hercules.sgi.sge.dto;

import org.crue.hercules.sgi.sge.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class DatoEconomicoColumnaPiiDto extends BaseEntity {

  /** Id */
  private String id;

  /** Nombre */
  private String nombre;

  /** Acumulable */
  private boolean acumulable;

  private boolean importeReparto;

}
