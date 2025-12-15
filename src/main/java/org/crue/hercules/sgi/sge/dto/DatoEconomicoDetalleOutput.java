package org.crue.hercules.sgi.sge.dto;

import java.time.LocalDate;
import java.util.List;

import org.crue.hercules.sgi.sge.model.BaseEntity;
import org.crue.hercules.sgi.sge.model.CodigoEconomico;
import org.crue.hercules.sgi.sge.model.DocumentoDetalle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class DatoEconomicoDetalleOutput extends BaseEntity {
  private String id;
  private String proyectoId;
  private String partidaPresupuestaria;
  private CodigoEconomico codigoEconomico;
  private String anualidad;
  private LocalDate fechaDevengo;
  private String clasificacionSGE;
  private List<DatoEconomicoCampoDto> campos;
  private List<DocumentoDetalle> documentos;
}
