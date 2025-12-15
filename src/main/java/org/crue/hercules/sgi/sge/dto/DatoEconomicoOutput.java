package org.crue.hercules.sgi.sge.dto;

import java.time.LocalDate;
import java.util.Map;

import org.crue.hercules.sgi.sge.model.BaseEntity;
import org.crue.hercules.sgi.sge.model.CodigoEconomico;

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
public class DatoEconomicoOutput extends BaseEntity {

  private String id;
  private String proyectoId;
  private String partidaPresupuestaria;
  private CodigoEconomico codigoEconomico;
  private String anualidad;
  private String tipo;
  private LocalDate fechaDevengo;
  private String clasificacionSGE;
  private Map<String, Object> columnas;

}
