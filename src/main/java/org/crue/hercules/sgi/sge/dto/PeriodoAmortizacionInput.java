package org.crue.hercules.sgi.sge.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class PeriodoAmortizacionInput implements Serializable {
  @NotNull
  String id;
  @NotBlank
  private String proyectoId;

  @NotNull
  private Integer anualidad;

  @NotBlank
  private String empresaRef;

  @NotNull
  private BigDecimal importe;
  private Instant fecha;
  private I18nFieldValueInput tipoFinanciacion;
  private I18nFieldValueInput fuenteFinanciacion;

}
