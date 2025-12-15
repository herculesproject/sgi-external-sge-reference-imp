package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto;

/**
 * PartidaPresupuestariaGastoNotFoundException
 */
public class PartidaPresupuestariaGastoNotFoundException extends SgeNotFoundException {

  public PartidaPresupuestariaGastoNotFoundException(String partidaPresupuestariaGastoId) {
    super(partidaPresupuestariaGastoId, PartidaPresupuestariaGasto.class);
  }

}
