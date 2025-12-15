package org.crue.hercules.sgi.sge.dto;

import java.io.Serializable;
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
public class ProyectoOutput implements Serializable {
  private String id;
  private Instant fechaFin;
  private Instant fechaInicio;
  private String proyectoSGIId;
  private String titulo;
}
