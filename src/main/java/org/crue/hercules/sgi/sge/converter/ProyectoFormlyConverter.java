package org.crue.hercules.sgi.sge.converter;

import javax.annotation.PostConstruct;

import org.crue.hercules.sgi.sge.dto.ProyectoFormlyDto;
import org.crue.hercules.sgi.sge.dto.ProyectoFormlyInput;
import org.crue.hercules.sgi.sge.dto.ProyectoFormlyOutput;
import org.crue.hercules.sgi.sge.model.Proyecto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProyectoFormlyConverter {

  private final ModelMapper modelMapper;
  private final I18nFieldValueConverter i18nFieldValueConverter;

  @PostConstruct
  public void mapperConfig() {
    modelMapper.typeMap(ProyectoFormlyInput.class, ProyectoFormlyDto.class)
        .addMappings(mapper -> mapper.using(i18nFieldValueConverter).map(
            ProyectoFormlyInput::getTitulo, ProyectoFormlyDto::setTitulo));
  }

  public ProyectoFormlyDto convert(ProyectoFormlyInput input) {
    return input == null ? null : modelMapper.map(input, ProyectoFormlyDto.class);
  }

  public ProyectoFormlyDto convert(Proyecto entity) {
    return entity == null ? null : modelMapper.map(entity, ProyectoFormlyDto.class);
  }

  public Proyecto convertToEntity(ProyectoFormlyDto entity) {
    return entity == null ? null : modelMapper.map(entity, Proyecto.class);
  }

  public ProyectoFormlyOutput convert(ProyectoFormlyDto entity) {
    return entity == null ? null : modelMapper.map(entity, ProyectoFormlyOutput.class);
  }

}
