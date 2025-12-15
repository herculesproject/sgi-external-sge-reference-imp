package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.sge.model.CodigoEconomicoIngreso;

/**
 * CodigoEconomicoIngresoNotFoundException
 */
public class CodigoEconomicoIngresoNotFoundException extends SgeNotFoundException {

  public CodigoEconomicoIngresoNotFoundException(String codigoEconomicoIngresoId) {
    super(codigoEconomicoIngresoId, CodigoEconomicoIngreso.class);
  }

}
