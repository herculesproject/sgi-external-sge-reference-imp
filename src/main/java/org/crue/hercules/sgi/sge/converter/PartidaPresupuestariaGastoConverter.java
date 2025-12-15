package org.crue.hercules.sgi.sge.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sge.dto.PartidaPresupuestariaGastoOutput;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PartidaPresupuestariaGastoConverter {

  private final ModelMapper modelMapper;

  public PartidaPresupuestariaGastoOutput convert(PartidaPresupuestariaGasto entity) {
    return entity == null ? null : modelMapper.map(entity, PartidaPresupuestariaGastoOutput.class);
  }

  public List<PartidaPresupuestariaGastoOutput> convert(List<PartidaPresupuestariaGasto> list) {
    return list.stream().map(this::convert).collect(Collectors.toList());
  }
}
