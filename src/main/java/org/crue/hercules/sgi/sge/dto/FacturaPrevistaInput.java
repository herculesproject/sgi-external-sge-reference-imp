package org.crue.hercules.sgi.sge.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

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
public class FacturaPrevistaInput implements Serializable {

  private Long id;

  private String proyectoSgeId;

  @NotNull
  private Long proyectoIdSGI;

  @NotNull
  private String numeroPrevision;

  @NotNull
  private Instant fechaEmision;

  @NotNull
  private BigDecimal importeBase;

  @NotNull
  private Integer porcentajeIVA;

  private List<I18nFieldValueInput> comentario;

  private List<I18nFieldValueInput> tipoFacturacion;

  private String numeroFactura;

}
