package org.crue.hercules.sgi.sge.controller.publico;

import org.crue.hercules.sgi.sge.converter.PartidaPresupuestariaIngresoConverter;
import org.crue.hercules.sgi.sge.dto.PartidaPresupuestariaIngresoOutput;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso;
import org.crue.hercules.sgi.sge.service.PartidaPresupuestariaIngresoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PartidaPresupuestariaIngresoPublicController
 */
@RestController
@RequestMapping(PartidaPresupuestariaIngresoPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class PartidaPresupuestariaIngresoPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "partidas-presupuestarias-ingresos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final PartidaPresupuestariaIngresoService service;
  private final PartidaPresupuestariaIngresoConverter converter;

  /**
   * Devuelve la {@link PartidaPresupuestariaIngreso} con el id indicado.
   * 
   * @param id Identificador de {@link PartidaPresupuestariaIngreso}.
   * @return {@link PartidaPresupuestariaIngreso} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public PartidaPresupuestariaIngresoOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    PartidaPresupuestariaIngresoOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

}
