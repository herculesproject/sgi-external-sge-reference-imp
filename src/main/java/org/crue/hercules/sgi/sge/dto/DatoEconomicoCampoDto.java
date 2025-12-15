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
public class DatoEconomicoCampoDto extends BaseEntity {
  private String nombre;
  private String valor;
}
