package org.crue.hercules.sgi.sge.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class ProyectoFormlyInput implements Serializable {

  @NotNull
  private Instant fechaFin;

  @NotNull
  private Instant fechaInicio;

  @NotBlank
  private String proyectoSgiId;

  @NotEmpty
  private List<I18nFieldValueInput> titulo;

  private String responsableRef;

}
