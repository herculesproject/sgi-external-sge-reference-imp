package org.crue.hercules.sgi.sge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = DocumentoDetalle.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoDetalle extends BaseEntity {

  protected static final String TABLE_NAME = "dato_economico_documento";

  private static final String ID_COLUMN_NAME = "id";
  private static final String ID_DATO_ECONOMICO_COLUMN_NAME = "dato_economico_id";
  private static final String NOMBRE_COLUMN_NAME = "nombre";
  private static final String NOMBRE_FICHERO_COLUMN_NAME = "nombre_fichero";
  private static final String CONTENIDO_COLUMN_NAME = "contenido";
  private static final String SEQ_NAME = "dato_economico_documento_seq";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
  @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
  @NotNull
  private String id;

  @Column(name = ID_DATO_ECONOMICO_COLUMN_NAME, nullable = false)
  @NotNull
  private String datoEconomicoId;

  /** Nombre */
  @Column(name = NOMBRE_COLUMN_NAME, nullable = false)
  @NotNull
  private String nombre;

  /** Nombre fichero */
  @Column(name = NOMBRE_FICHERO_COLUMN_NAME, nullable = false)
  @NotNull
  private String nombreFichero;

  @Lob
  @Column(name = CONTENIDO_COLUMN_NAME, nullable = true, columnDefinition = "blob")
  private byte[] contenido;
}
