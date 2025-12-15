package org.crue.hercules.sgi.sge.dto;

import java.io.Serializable;

import org.crue.hercules.sgi.sge.enums.SGIEntityTypeEnum;

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
public class ProyectoRelacionesEliminadasInput implements Serializable {
  private String entidadSGIId;
  private SGIEntityTypeEnum tipoEntidadSGI;
}
