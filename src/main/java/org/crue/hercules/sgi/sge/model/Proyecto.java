package org.crue.hercules.sgi.sge.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = Proyecto.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto extends BaseEntity {

  protected static final String TABLE_NAME = "proyecto";
  private static final String SEQUENCE_NAME = TABLE_NAME + "_seq";

  private static final String ID_COLUMN_NAME = "id";
  private static final String TITULO_COLUMN_NAME = "titulo";
  private static final String FECHA_INICIO_COLUMN_NAME = "fecha_inicio";
  private static final String FECHA_FIN_COLUMN_NAME = "fecha_fin";
  private static final String RESPONSABLE_COLUMN_NAME = "responsable_ref";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  /** Titulo */
  @Column(name = TITULO_COLUMN_NAME)
  @NotBlank
  private String titulo;

  /** Fecha inicio */
  @Column(name = FECHA_INICIO_COLUMN_NAME, nullable = true)
  private Instant fechaInicio;

  /** Fecha fin */
  @Column(name = FECHA_FIN_COLUMN_NAME, nullable = true)
  private Instant fechaFin;

  /** Proyecto SGI id */
  @Column(name = RESPONSABLE_COLUMN_NAME)
  private String responsableRef;

}
