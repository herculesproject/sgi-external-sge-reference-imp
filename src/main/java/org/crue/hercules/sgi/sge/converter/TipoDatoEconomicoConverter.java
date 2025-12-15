package org.crue.hercules.sgi.sge.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.crue.hercules.sgi.sge.enums.TipoDatoEconomicoEnum;

@Converter(autoApply = true)
public class TipoDatoEconomicoConverter implements AttributeConverter<TipoDatoEconomicoEnum, String> {

  @Override
  public String convertToDatabaseColumn(TipoDatoEconomicoEnum attribute) {
    return attribute != null ? attribute.getDescripcion() : null;
  }

  @Override
  public TipoDatoEconomicoEnum convertToEntityAttribute(String dbData) {
    if (dbData == null)
      return null;
    for (TipoDatoEconomicoEnum tipo : TipoDatoEconomicoEnum.values()) {
      if (tipo.getDescripcion().equals(dbData)) {
        return tipo;
      }
    }
    throw new IllegalArgumentException("Valor desconocido en BD: " + dbData);
  }
}
