package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.sge.model.CodigoEconomicoGasto;

/**
 * CodigoEconomicoGastoNotFoundException
 */
public class CodigoEconomicoGastoNotFoundException extends SgeNotFoundException {

  public CodigoEconomicoGastoNotFoundException(String codigoEconomicoGastoId) {
    super(codigoEconomicoGastoId, CodigoEconomicoGasto.class);
  }

}
