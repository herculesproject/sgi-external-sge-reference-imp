package org.crue.hercules.sgi.sge.dto;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class ProyectoAnualidadPartidaInput implements Serializable {

  @NotBlank
  private String proyectoId;

  @NotNull
  private Integer anualidad;

  @NotBlank
  private String partidaPresupuestaria;

  @NotNull
  private TipoDatoEconomico tipoDatoEconomico;

  @NotNull
  private BigDecimal importe;

  public enum TipoDatoEconomico {
    /** Gasto <code>GASTO</code> */
    GASTO,
    /** Ingreso <code>INGRESO</code> */
    INGRESO;
  }

}
