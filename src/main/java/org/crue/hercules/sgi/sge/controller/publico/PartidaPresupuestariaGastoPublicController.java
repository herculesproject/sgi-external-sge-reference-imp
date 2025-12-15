package org.crue.hercules.sgi.sge.controller.publico;

import org.crue.hercules.sgi.sge.converter.PartidaPresupuestariaGastoConverter;
import org.crue.hercules.sgi.sge.dto.PartidaPresupuestariaGastoOutput;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto;
import org.crue.hercules.sgi.sge.service.PartidaPresupuestariaGastoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PartidaPresupuestariaGastoPublicController
 */
@RestController
@RequestMapping(PartidaPresupuestariaGastoPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class PartidaPresupuestariaGastoPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "partidas-presupuestarias-gastos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final PartidaPresupuestariaGastoService service;
  private final PartidaPresupuestariaGastoConverter converter;

  /**
   * Devuelve la {@link PartidaPresupuestariaGasto} con el id indicado.
   * 
   * @param id Identificador de {@link PartidaPresupuestariaGasto}.
   * @return {@link PartidaPresupuestariaGasto} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public PartidaPresupuestariaGastoOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    PartidaPresupuestariaGastoOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

}
