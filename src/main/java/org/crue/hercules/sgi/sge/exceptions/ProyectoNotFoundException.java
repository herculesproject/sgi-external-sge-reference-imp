package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.sge.model.Proyecto;

/**
 * ProyectoNotFoundException
 */
public class ProyectoNotFoundException extends SgeNotFoundException {

  public ProyectoNotFoundException(Long proyectoId) {
    super(proyectoId, Proyecto.class);
  }

}
