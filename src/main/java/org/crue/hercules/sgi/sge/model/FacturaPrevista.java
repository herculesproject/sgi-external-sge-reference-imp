package org.crue.hercules.sgi.sge.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
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
@Table(name = FacturaPrevista.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaPrevista extends BaseEntity {

  protected static final String TABLE_NAME = "calendario_facturacion";

  private static final String ID_COLUMN_NAME = "id";
  private static final String PROYECTO_SGE_ID_COLUMN_NAME = "proyecto_sge_id";
  private static final String PROYECTO_ID_COLUMN_NAME = "proyecto_id_sgi";
  private static final String NUMERO_PREVISION_COLUMN_NAME = "numero_prevision";
  private static final String FECHA_EMISION_COLUMN_NAME = "fecha_emision";
  private static final String IMPORTE_BASE_COLUMN_NAME = "importe_base";
  private static final String PORCENTAJE_IVA_COLUMN_NAME = "porcentaje_iva";
  private static final String COMENTARIO_COLUMN_NAME = "comentario";
  private static final String TIPO_FACTURACION_COLUMN_NAME = "tipo_facturacion";
  private static final String NUM_FACTURA_COLUMN_NAME = "numero_factura";
  private static final String SEQ_NAME = "calendario_facturacion_seq";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
  @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
  private Long id;

  /** Proyecto SGE id */
  @Column(name = PROYECTO_SGE_ID_COLUMN_NAME, nullable = true)
  private String proyectoSgeId;

  /** Proyecto id */
  @Column(name = PROYECTO_ID_COLUMN_NAME, nullable = false)
  @NotNull
  private Long proyectoIdSGI;

  /** Numero previsión */
  @Column(name = NUMERO_PREVISION_COLUMN_NAME, nullable = false)
  @NotNull
  private String numeroPrevision;

  /** Fecha emisión */
  @Column(name = FECHA_EMISION_COLUMN_NAME, nullable = false)
  @NotNull
  private Instant fechaEmision;

  /** Importe base */
  @Column(name = IMPORTE_BASE_COLUMN_NAME, nullable = false)
  @NotNull
  private BigDecimal importeBase;

  /** Porcentaje IVA */
  @Column(name = PORCENTAJE_IVA_COLUMN_NAME, nullable = false)
  @Max(100)
  @NotNull
  private Integer porcentajeIVA;

  /** Comentario */
  @Column(name = COMENTARIO_COLUMN_NAME, nullable = true)
  private String comentario;

  /** Tipo de facturación */
  @Column(name = TIPO_FACTURACION_COLUMN_NAME, nullable = true)
  private String tipoFacturacion;

  /** Numero de factura */
  @Column(name = NUM_FACTURA_COLUMN_NAME, nullable = true)
  private String numeroFactura;

}
