package org.crue.hercules.sgi.sge.converter;

import javax.annotation.PostConstruct;

import org.crue.hercules.sgi.sge.dto.FacturaPrevistaInput;
import org.crue.hercules.sgi.sge.dto.FacturaPrevistaOutput;
import org.crue.hercules.sgi.sge.model.FacturaPrevista;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FacturaPrevistaConverter {

  private final ModelMapper modelMapper;
  private final I18nFieldValueConverter i18nFieldValueConverter;

  @PostConstruct
  public void mapperConfig() {
    modelMapper.typeMap(FacturaPrevistaInput.class, FacturaPrevista.class)

        .addMappings(mapper -> mapper.using(i18nFieldValueConverter)
            .map(FacturaPrevistaInput::getComentario, FacturaPrevista::setComentario))
        .addMappings(mapper -> mapper.using(i18nFieldValueConverter)
            .map(FacturaPrevistaInput::getTipoFacturacion, FacturaPrevista::setTipoFacturacion));

    modelMapper.typeMap(FacturaPrevistaOutput.class, FacturaPrevista.class)
        .addMappings(mapper -> mapper.using(i18nFieldValueConverter)
            .map(FacturaPrevistaOutput::getComentario, FacturaPrevista::setComentario))
        .addMappings(mapper -> mapper.using(i18nFieldValueConverter)
            .map(FacturaPrevistaOutput::getTipoFacturacion, FacturaPrevista::setTipoFacturacion));
  }

  public FacturaPrevista convert(FacturaPrevistaInput input) {
    return input == null ? null : modelMapper.map(input, FacturaPrevista.class);
  }

  public FacturaPrevista convert(FacturaPrevistaInput input, Long id) {
    FacturaPrevista facturaPrevista = convert(input);
    if (facturaPrevista != null) {
      facturaPrevista.setId(id);
    }
    return facturaPrevista;
  }

  public FacturaPrevistaOutput convert(FacturaPrevista entity) {
    return entity == null ? null : modelMapper.map(entity, FacturaPrevistaOutput.class);
  }

  public Page<FacturaPrevistaOutput> convert(Page<FacturaPrevista> page) {
    return page.map(this::convert);
  }

}