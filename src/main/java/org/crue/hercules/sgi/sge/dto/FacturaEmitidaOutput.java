package org.crue.hercules.sgi.sge.dto;

import java.util.Map;

import org.crue.hercules.sgi.sge.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaEmitidaOutput extends BaseEntity {

  private String id;
  private String proyectoId;
  private String anualidad;
  private String numeroFactura;
  private Map<String, Object> columnas;

}
