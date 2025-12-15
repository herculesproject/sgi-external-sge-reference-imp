package org.crue.hercules.sgi.sge.converter;

import java.util.List;

import org.crue.hercules.sgi.sge.dto.I18nFieldValueInput;
import org.crue.hercules.sgi.sge.enums.Language;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class I18nFieldValueConverter extends AbstractConverter<List<I18nFieldValueInput>, String> {

  /**
   * Converts a list of I18nFieldValueInput to a String.
   * If the list is null or empty, it returns null.
   * If the list contains values for the ES language, it returns the first one.
   * Otherwise, it returns the first value in the list.
   *
   * @param i18nFieldValues the list of I18nFieldValueInput to convert
   * @return the converted String
   */
  @Override
  protected String convert(List<I18nFieldValueInput> i18nFieldValues) {
    if (i18nFieldValues == null || i18nFieldValues.isEmpty()) {
      return null;
    }

    return i18nFieldValues.stream()
        .filter(i18nFieldValue -> Language.ES.equals(i18nFieldValue.getLang()))
        .map(I18nFieldValueInput::getValue)
        .findFirst()
        .orElse(i18nFieldValues.get(0).getValue());
  }

}