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
@Table(name = CodigoEconomicoGasto.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodigoEconomicoGasto extends BaseEntity {

  protected static final String TABLE_NAME = "codigo_economico_gasto";

  private static final String ID_COLUMN_NAME = "id";
  private static final String NOMBRE_COLUMN_NAME = "nombre";
  private static final String FECHA_INICIO_COLUMN_NAME = "fecha_inicio";
  private static final String FECHA_FIN_COLUMN_NAME = "fecha_fin";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @NotBlank
  private String id;

  /** Nombre */
  @Column(name = NOMBRE_COLUMN_NAME, nullable = false)
  @NotBlank
  private String nombre;

  /** Fecha inicio */
  @Column(name = FECHA_INICIO_COLUMN_NAME, nullable = true)
  private Instant fechaInicio;

  /** Fecha fin */
  @Column(name = FECHA_FIN_COLUMN_NAME, nullable = true)
  private Instant fechaFin;

}
