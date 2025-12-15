package org.crue.hercules.sgi.sge.controller.publico;

import org.crue.hercules.sgi.sge.converter.CodigoEconomicoGastoConverter;
import org.crue.hercules.sgi.sge.dto.CodigoEconomicoGastoOutput;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoGasto;
import org.crue.hercules.sgi.sge.service.CodigoEconomicoGastoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CodigoEconomicoGastoPublicController
 */
@RestController
@RequestMapping(CodigoEconomicoGastoPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class CodigoEconomicoGastoPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "codigos-economicos-gastos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final CodigoEconomicoGastoService service;
  private final CodigoEconomicoGastoConverter converter;

  /**
   * Devuelve el {@link CodigoEconomicoGasto} con el id indicado.
   * 
   * @param id Identificador de {@link CodigoEconomicoGasto}.
   * @return {@link CodigoEconomicoGasto} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public CodigoEconomicoGastoOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    CodigoEconomicoGastoOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

}
