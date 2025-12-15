package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import org.crue.hercules.sgi.sge.converter.CodigoEconomicoIngresoConverter;
import org.crue.hercules.sgi.sge.dto.CodigoEconomicoIngresoOutput;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoIngreso;
import org.crue.hercules.sgi.sge.service.CodigoEconomicoIngresoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CodigoEconomicoIngresoController
 */
@RestController
@RequestMapping(CodigoEconomicoIngresoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class CodigoEconomicoIngresoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "codigos-economicos-ingresos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final CodigoEconomicoIngresoService service;
  private final CodigoEconomicoIngresoConverter converter;

  /**
   * Devuelve una lista de {@link CodigoEconomicoIngreso} ordenados
   * alfabéticamente.
   * 
   * @return el listado de entidades {@link CodigoEconomicoIngreso} ordenados
   *         alfabéticamente
   */
  @GetMapping()
  public ResponseEntity<List<CodigoEconomicoIngresoOutput>> findAllSorted() {
    log.debug("findAllSorted() - start");
    List<CodigoEconomicoIngresoOutput> codigosEconomicos = converter.convert(service.findAllSorted());
    log.debug("findAllSorted() - end");
    return codigosEconomicos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(codigosEconomicos, HttpStatus.OK);
  }

  /**
   * Devuelve la {@link CodigoEconomicoIngreso} con el id indicado.
   * 
   * @param id Identificador de {@link CodigoEconomicoIngreso}.
   * @return {@link CodigoEconomicoIngreso} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public CodigoEconomicoIngresoOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    CodigoEconomicoIngresoOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

}
