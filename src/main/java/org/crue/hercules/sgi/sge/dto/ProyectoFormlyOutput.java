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
public class ProyectoFormlyOutput implements Serializable {
  private String id;
  private String titulo;
  private Instant fechaInicio;
  private Instant fechaFin;
  private String proyectoSGIId;
  private String responsableRef;
}
