package org.crue.hercules.sgi.sge.dto;

import java.util.List;

import org.crue.hercules.sgi.sge.model.BaseEntity;
import org.crue.hercules.sgi.sge.model.DocumentoDetalle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class FacturaEmitidaDetalleOutput extends BaseEntity {
  private String id;
  private String proyectoId;
  private String anualidad;
  private String numeroFactura;
  private List<DatoEconomicoCampoDto> campos;
  private List<DocumentoDetalle> documentos;
}
