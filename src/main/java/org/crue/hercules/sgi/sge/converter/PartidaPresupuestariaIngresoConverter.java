package org.crue.hercules.sgi.sge.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sge.dto.PartidaPresupuestariaIngresoOutput;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PartidaPresupuestariaIngresoConverter {

  private final ModelMapper modelMapper;

  public PartidaPresupuestariaIngresoOutput convert(PartidaPresupuestariaIngreso entity) {
    return entity == null ? null : modelMapper.map(entity, PartidaPresupuestariaIngresoOutput.class);
  }

  public List<PartidaPresupuestariaIngresoOutput> convert(List<PartidaPresupuestariaIngreso> list) {
    return list.stream().map(this::convert).collect(Collectors.toList());
  }
}
