package org.crue.hercules.sgi.sge.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sge.dto.CodigoEconomicoGastoOutput;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoGasto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CodigoEconomicoGastoConverter {

  private final ModelMapper modelMapper;

  public CodigoEconomicoGastoOutput convert(CodigoEconomicoGasto entity) {
    return entity == null ? null : modelMapper.map(entity, CodigoEconomicoGastoOutput.class);
  }

  public List<CodigoEconomicoGastoOutput> convert(List<CodigoEconomicoGasto> list) {
    return list.stream().map(this::convert).collect(Collectors.toList());
  }
}
