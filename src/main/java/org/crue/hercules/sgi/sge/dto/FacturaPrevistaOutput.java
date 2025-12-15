package org.crue.hercules.sgi.sge.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.crue.hercules.sgi.framework.i18n.I18nFieldValueDto;

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
public class FacturaPrevistaOutput implements Serializable {

  private Long id;
  private String proyectoSgeId;
  private Long proyectoIdSGI;
  private String numeroPrevision;
  private Instant fechaEmision;
  private BigDecimal importeBase;
  private Integer porcentajeIVA;
  private List<I18nFieldValueDto> comentario;
  private List<I18nFieldValueDto> tipoFacturacion;
  private String numeroFactura;
}