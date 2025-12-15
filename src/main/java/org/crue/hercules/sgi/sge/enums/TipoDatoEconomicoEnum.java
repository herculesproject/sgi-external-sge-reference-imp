package org.crue.hercules.sgi.sge.enums;

/** Tipo de operación simple: Gasto o Ingreso */
public enum TipoDatoEconomicoEnum {
  GASTO("Gasto"),
  INGRESO("Ingreso");

  private final String descripcion;

  TipoDatoEconomicoEnum(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }
}
