package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso;

/**
 * PartidaPresupuestariaIngresoNotFoundException
 */
public class PartidaPresupuestariaIngresoNotFoundException extends SgeNotFoundException {

  public PartidaPresupuestariaIngresoNotFoundException(String partidaPresupuestariaIngresoId) {
    super(partidaPresupuestariaIngresoId, PartidaPresupuestariaIngreso.class);
  }

}
