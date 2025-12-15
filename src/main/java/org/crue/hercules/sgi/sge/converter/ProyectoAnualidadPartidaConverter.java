package org.crue.hercules.sgi.sge.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.crue.hercules.sgi.sge.dto.ProyectoAnualidadPartidaInput;
import org.crue.hercules.sgi.sge.dto.ProyectoAnualidadPartidaInput.TipoDatoEconomico;
import org.crue.hercules.sgi.sge.model.ProyectoAnualidadPartida;
import org.crue.hercules.sgi.sge.model.ProyectoAnualidadPartida.Tipo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProyectoAnualidadPartidaConverter {

  private final ModelMapper modelMapper;

  @PostConstruct
  public void mapperConfig() {
    Converter<TipoDatoEconomico, Tipo> tipoDatoEconomicoToTipoConverter = mappingContext -> {
      switch (mappingContext.getSource()) {
        case GASTO:
          return ProyectoAnualidadPartida.Tipo.G;
        case INGRESO:
          return ProyectoAnualidadPartida.Tipo.I;
        default:
          return null;
      }
    };

    modelMapper.typeMap(ProyectoAnualidadPartidaInput.class, ProyectoAnualidadPartida.class)
        .addMappings(mapper -> mapper.using(tipoDatoEconomicoToTipoConverter)
            .map(ProyectoAnualidadPartidaInput::getTipoDatoEconomico, ProyectoAnualidadPartida::setTipo));
  }

  public ProyectoAnualidadPartida convert(ProyectoAnualidadPartidaInput input) {
    return input == null ? null : modelMapper.map(input, ProyectoAnualidadPartida.class);
  }

  public List<ProyectoAnualidadPartida> convertToEntities(List<ProyectoAnualidadPartidaInput> list) {
    return list.stream().map(this::convert).collect(Collectors.toList());
  }

}
