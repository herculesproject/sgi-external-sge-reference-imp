package org.crue.hercules.sgi.sge.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.crue.hercules.sgi.sge.converter.TipoDatoEconomicoConverter;
import org.crue.hercules.sgi.sge.enums.EjecucionEconomicaTipoOperacionEnum;
import org.crue.hercules.sgi.sge.enums.TipoDatoEconomicoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = DatoEconomico.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatoEconomico extends BaseEntity {

  protected static final String TABLE_NAME = "dato_economico";

  private static final String ID_COLUMN_NAME = "id";
  private static final String PROYECTO_ID_COLUMN_NAME = "proyecto_id";
  private static final String INVENCION_ID_COLUMN_NAME = "invencion_id";
  private static final String ANUALIDAD_COLUMN_NAME = "anualidad";
  private static final String TIPO_OPERACION_COLUMN_NAME = "tipo_operacion";
  private static final String TIPO_DATO_ECONOMICO_COLUMN_NAME = "tipo_dato_economico";
  private static final String APLICACION_PRESUPUESTARIA_COLUMN_NAME = "aplicacion_presupuestaria";
  private static final String IMPORTE_INICIAL_COLUMN_NAME = "importe_inicial";
  private static final String IMPORTE_DEFINITIVO_COLUMN_NAME = "importe_definitivo";
  private static final String IMPORTE_DISPONIBLE_COLUMN_NAME = "importe_disponible";
  private static final String IMPORTE_PRESUPUESTADO_COLUMN_NAME = "importe_presupuestado";
  private static final String IMPORTE_CONCEDIDO_COLUMN_NAME = "importe_concedido";
  private static final String GASTADO_COLUMN_NAME = "gastado";
  private static final String PAGADO_COLUMN_NAME = "pagado";
  private static final String PENDIENTE_PAGO_COLUMN_NAME = "pendiente_pago";
  private static final String RESERVADO_COLUMN_NAME = "reservado";
  private static final String DISPONIBLE_COLUMN_NAME = "disponible";
  private static final String DERECHOS_COLUMN_NAME = "derechos";
  private static final String COBROS_COLUMN_NAME = "cobros";
  private static final String FECHA_COLUMN_NAME = "fecha";
  private static final String FECHA_EMISION_COLUMN_NAME = "fecha_emision";
  private static final String FECHA_COBRO_COLUMN_NAME = "fecha_cobro";
  private static final String FECHA_DEVENGO_COLUMN_NAME = "fecha_devengo";
  private static final String FECHA_SALIDA_COLUMN_NAME = "fecha_salida";
  private static final String FECHA_VUELTA_COLUMN_NAME = "fecha_vuelta";
  private static final String GASTADO_EJECUTADO_COLUMN_NAME = "gastado_ejecutado";
  private static final String GASTADO_COMPROMETIDO_COLUMN_NAME = "gastado_comprometido";
  private static final String DESCRIPCION_COLUMN_NAME = "descripcion";
  private static final String DESTINO_COLUMN_NAME = "destino";
  private static final String TERCERO_COLUMN_NAME = "tercero";
  private static final String IMPORTE_IVA_COLUMN_NAME = "importe_iva";
  private static final String NUM_DOCUMENTO_COLUMN_NAME = "numero_documento";
  private static final String NUM_IDENTIFICACION_COLUMN_NAME = "numero_identificacion";
  private static final String NUM_PREVISION_COLUMN_NAME = "numero_prevision";
  private static final String NOMBRE_COMPLETO_COLUMN_NAME = "nombre_completo";
  private static final String NUM_COMISION_COLUMN_NAME = "numero_comision";
  private static final String IMPORTE_LOCOMOCION_COLUMN_NAME = "importe_locomocion";
  private static final String IMPORTE_ALOJAMIENTO_COLUMN_NAME = "importe_alojamiento";
  private static final String IMPORTE_DIETAS_COLUMN_NAME = "importe_dietas";
  private static final String PORCENTAJE_IVA_COLUMN_NAME = "porcentaje_iva";
  private static final String ESTADO_COLUMN_NAME = "estado";
  private static final String CODIGO_ECONOMICO_COLUMN_NAME = "codigo_economico";
  private static final String SEQ_NAME = "dato_economico_seq";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
  @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
  @NotNull
  private String id;

  /** proyectoId */
  @Column(name = PROYECTO_ID_COLUMN_NAME, nullable = true)
  private String proyectoId;

  /** invencionId */
  @Column(name = INVENCION_ID_COLUMN_NAME, nullable = true)
  private String invencionId;

  /** Anualidad */
  @Column(name = ANUALIDAD_COLUMN_NAME, nullable = true)
  private String anualidad;

  /** Tipo de operación */
  @Column(name = TIPO_OPERACION_COLUMN_NAME, nullable = false)
  @Enumerated(EnumType.STRING)
  @NotNull
  private EjecucionEconomicaTipoOperacionEnum tipoOperacion;

  /** Tipo de operación */
  @Column(name = TIPO_DATO_ECONOMICO_COLUMN_NAME, nullable = true)
  @Convert(converter = TipoDatoEconomicoConverter.class)
  private TipoDatoEconomicoEnum tipo;

  /** Aplicación presupuestaria */
  @Column(name = APLICACION_PRESUPUESTARIA_COLUMN_NAME, nullable = true)
  private String aplicacionPresupuestaria;

  /** Descripción */
  @Column(name = DESCRIPCION_COLUMN_NAME, nullable = true)
  private String descripcion;

  /** Código económico */
  @Column(name = CODIGO_ECONOMICO_COLUMN_NAME, nullable = true)
  private String codigoEconomico;

  /** Destino */
  @Column(name = DESTINO_COLUMN_NAME, nullable = true)
  private String destino;

  /** Número de documento */
  @Column(name = NUM_DOCUMENTO_COLUMN_NAME, nullable = true)
  private String numDocumento;

  /** Número de identificación */
  @Column(name = NUM_IDENTIFICACION_COLUMN_NAME, nullable = true)
  private String numIdentificacion;

  /** Número de previsión */
  @Column(name = NUM_PREVISION_COLUMN_NAME, nullable = true)
  private String numPrevision;

  /** Nombre y apellidos */
  @Column(name = NOMBRE_COMPLETO_COLUMN_NAME, nullable = true)
  private String nombreCompleto;

  /** Número de comisión */
  @Column(name = NUM_COMISION_COLUMN_NAME, nullable = true)
  private String numComision;

  /** Fecha registro */
  @Column(name = FECHA_COLUMN_NAME, nullable = true)
  private Instant fecha;

  /** Fecha emisión */
  @Column(name = FECHA_EMISION_COLUMN_NAME, nullable = true)
  private Instant fechaEmision;

  /** Fecha cobro */
  @Column(name = FECHA_COBRO_COLUMN_NAME, nullable = true)
  private Instant fechaCobro;

  /** Fecha devengo */
  @Column(name = FECHA_DEVENGO_COLUMN_NAME, nullable = true)
  private Instant fechaDevengo;

  /** Fecha salida */
  @Column(name = FECHA_SALIDA_COLUMN_NAME, nullable = true)
  private Instant fechaSalida;

  /** Fecha vuelta */
  @Column(name = FECHA_VUELTA_COLUMN_NAME, nullable = true)
  private Instant fechaVuelta;

  @Column(name = IMPORTE_INICIAL_COLUMN_NAME, nullable = true)
  private BigDecimal importeInicial;

  @Column(name = IMPORTE_DEFINITIVO_COLUMN_NAME, nullable = true)
  private BigDecimal importeDefinitivo;

  @Column(name = IMPORTE_DISPONIBLE_COLUMN_NAME, nullable = true)
  private BigDecimal importeDisponible;

  @Column(name = IMPORTE_PRESUPUESTADO_COLUMN_NAME, nullable = true)
  private BigDecimal importePresupuestado;

  @Column(name = IMPORTE_CONCEDIDO_COLUMN_NAME, nullable = true)
  private BigDecimal importeConcedido;

  @Column(name = GASTADO_COLUMN_NAME, nullable = true)
  private BigDecimal gastado;

  @Column(name = PAGADO_COLUMN_NAME, nullable = true)
  private BigDecimal pagado;

  @Column(name = PENDIENTE_PAGO_COLUMN_NAME, nullable = true)
  private BigDecimal pendientePago;

  @Column(name = RESERVADO_COLUMN_NAME, nullable = true)
  private BigDecimal reservado;

  @Column(name = DISPONIBLE_COLUMN_NAME, nullable = true)
  private BigDecimal disponible;

  @Column(name = DERECHOS_COLUMN_NAME, nullable = true)
  private BigDecimal derechos;

  @Column(name = COBROS_COLUMN_NAME, nullable = true)
  private BigDecimal cobros;

  @Column(name = GASTADO_EJECUTADO_COLUMN_NAME, nullable = true)
  private BigDecimal gastadoEjecutado;

  @Column(name = GASTADO_COMPROMETIDO_COLUMN_NAME, nullable = true)
  private BigDecimal gastadoComprometido;

  @Column(name = TERCERO_COLUMN_NAME, nullable = true)
  private String tercero;

  @Column(name = IMPORTE_IVA_COLUMN_NAME, nullable = true)
  private BigDecimal importeIva;

  @Column(name = IMPORTE_LOCOMOCION_COLUMN_NAME, nullable = true)
  private BigDecimal importeLocomocion;

  @Column(name = IMPORTE_ALOJAMIENTO_COLUMN_NAME, nullable = true)
  private BigDecimal importeAlojamiento;

  @Column(name = IMPORTE_DIETAS_COLUMN_NAME, nullable = true)
  private BigDecimal importeDietas;

  @Max(100)
  @Column(name = PORCENTAJE_IVA_COLUMN_NAME, nullable = true)
  private Integer porcentajeIVA;

  @Column(name = ESTADO_COLUMN_NAME, nullable = true)
  private String estado;

}
