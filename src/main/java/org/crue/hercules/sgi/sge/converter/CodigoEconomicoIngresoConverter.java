package org.crue.hercules.sgi.sge.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sge.dto.CodigoEconomicoIngresoOutput;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoIngreso;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CodigoEconomicoIngresoConverter {

  private final ModelMapper modelMapper;

  public CodigoEconomicoIngresoOutput convert(CodigoEconomicoIngreso entity) {
    return entity == null ? null : modelMapper.map(entity, CodigoEconomicoIngresoOutput.class);
  }

  public List<CodigoEconomicoIngresoOutput> convert(List<CodigoEconomicoIngreso> list) {
    return list.stream().map(this::convert).collect(Collectors.toList());
  }

}
