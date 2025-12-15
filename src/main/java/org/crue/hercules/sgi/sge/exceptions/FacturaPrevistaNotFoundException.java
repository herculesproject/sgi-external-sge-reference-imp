package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.sge.model.FacturaPrevista;

/**
 * FacturaPrevistaNotFoundException
 */
public class FacturaPrevistaNotFoundException extends SgeNotFoundException {

  public FacturaPrevistaNotFoundException(Long facturaPrevistaId) {
    super(facturaPrevistaId, FacturaPrevista.class);
  }

}
