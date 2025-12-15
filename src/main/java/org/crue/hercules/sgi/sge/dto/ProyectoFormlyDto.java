package org.crue.hercules.sgi.sge.dto;

import java.time.Instant;

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
public class ProyectoFormlyDto {
  private String id;
  private String titulo;
  private Instant fechaInicio;
  private Instant fechaFin;
  private String proyectoSgiId;
  private String responsableRef;
}
