package org.crue.hercules.sgi.sge.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class CodigoEconomico extends BaseEntity {

  protected static final String TABLE_NAME = "codigo_economico";

  private static final String ID_COLUMN_NAME = "id";
  private static final String NOMBRE_COLUMN_NAME = "nombre";
  private static final String FECHA_INICIO_COLUMN_NAME = "fecha_inicio";
  private static final String FECHA_FIN_COLUMN_NAME = "fecha_fin";
  private static final String TIPO_COLUMN_NAME = "tipo";

  public enum Tipo {
    /** Gasto <code>G</code> */
    G,
    /** Ingreso <code>I</code> */
    I;
  }

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

  /** Tipo */
  @Column(name = TIPO_COLUMN_NAME, nullable = false)
  @Enumerated(EnumType.STRING)
  private Tipo tipo;

}