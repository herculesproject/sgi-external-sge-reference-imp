package org.crue.hercules.sgi.sge.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = ProyectoAnualidadPartida.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoAnualidadPartida extends BaseEntity {

  protected static final String TABLE_NAME = "proyecto_anualidad_partida";
  private static final String SEQUENCE_NAME = TABLE_NAME + "_seq";

  private static final String ID_COLUMN_NAME = "id";
  private static final String PROYECTO_ID_COLUMN_NAME = "proyecto_id";
  private static final String ANUALIDAD_COLUMN_NAME = "anualidad";
  private static final String PARTIDA_PRESUPUESTARIA_COLUMN_NAME = "partidaPresupuestaria";
  private static final String TIPO_COLUMN_NAME = "tipo";
  private static final String IMPORTE_COLUMN_NAME = "importe";

  private static final String PROYECTO_FK = "FK_PROYECTO_ANUALIDAD_PARTIDA_PROYECTO";

  public enum Tipo {
    /** Gasto <code>G</code> */
    G,
    /** Ingreso <code>I</code> */
    I;
  }

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  /** Proyecto Id */
  @Column(name = PROYECTO_ID_COLUMN_NAME, nullable = false)
  @NotNull
  private String proyectoId;

  /** Partida presupuestaria */
  @Column(name = PARTIDA_PRESUPUESTARIA_COLUMN_NAME, nullable = false)
  @NotBlank
  private String partidaPresupuestaria;

  /** Anualidad */
  @Column(name = ANUALIDAD_COLUMN_NAME, nullable = false)
  @NotNull
  private Integer anualidad;

  /** Importe */
  @Column(name = IMPORTE_COLUMN_NAME)
  @NotNull
  private BigDecimal importe;

  /** Tipo */
  @Column(name = TIPO_COLUMN_NAME, nullable = false)
  @Enumerated(EnumType.STRING)
  @NotNull
  private Tipo tipo;

  // Relations mapping, only for JPA metamodel generation
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = PROYECTO_ID_COLUMN_NAME, insertable = false, updatable = false, foreignKey = @ForeignKey(name = PROYECTO_FK))
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private final Proyecto proyecto = null;

}
