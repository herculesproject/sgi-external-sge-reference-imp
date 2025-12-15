package org.crue.hercules.sgi.sge.converter;

import org.crue.hercules.sgi.sge.dto.ProyectoOutput;
import org.crue.hercules.sgi.sge.model.Proyecto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProyectoConverter {

  private final ModelMapper modelMapper;

  public ProyectoOutput convert(Proyecto entity) {
    return entity == null ? null : modelMapper.map(entity, ProyectoOutput.class);
  }

  public Page<ProyectoOutput> convert(Page<Proyecto> page) {
    return page.map(this::convert);
  }

}
