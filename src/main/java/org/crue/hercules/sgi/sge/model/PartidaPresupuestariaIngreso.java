package org.crue.hercules.sgi.sge.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = PartidaPresupuestariaIngreso.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidaPresupuestariaIngreso extends BaseEntity {

  protected static final String TABLE_NAME = "partida_presupuestaria_ingreso";

  private static final String ID_COLUMN_NAME = "id";
  private static final String CODIGO_COLUMN_NAME = "codigo";
  private static final String DESCRIPCION_COLUMN_NAME = "descripcion";
  private static final String FECHA_INICIO_COLUMN_NAME = "fecha_inicio";
  private static final String FECHA_FIN_COLUMN_NAME = "fecha_fin";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @NotBlank
  private String id;

  /** Codigo */
  @Column(name = CODIGO_COLUMN_NAME, nullable = false)
  @NotBlank
  private String codigo;

  /** Descripcion */
  @Column(name = DESCRIPCION_COLUMN_NAME, nullable = false)
  @NotBlank
  private String descripcion;

  /** Fecha inicio */
  @Column(name = FECHA_INICIO_COLUMN_NAME, nullable = true)
  private Instant fechaInicio;

  /** Fecha fin */
  @Column(name = FECHA_FIN_COLUMN_NAME, nullable = true)
  private Instant fechaFin;

}
