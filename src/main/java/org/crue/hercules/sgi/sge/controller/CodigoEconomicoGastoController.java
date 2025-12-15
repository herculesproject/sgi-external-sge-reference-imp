package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import org.crue.hercules.sgi.sge.converter.CodigoEconomicoGastoConverter;
import org.crue.hercules.sgi.sge.dto.CodigoEconomicoGastoOutput;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoGasto;
import org.crue.hercules.sgi.sge.service.CodigoEconomicoGastoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CodigoEconomicoGastoController
 */
@RestController
@RequestMapping(CodigoEconomicoGastoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class CodigoEconomicoGastoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "codigos-economicos-gastos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final CodigoEconomicoGastoService service;
  private final CodigoEconomicoGastoConverter converter;

  /**
   * Devuelve una lista de {@link CodigoEconomicoGasto} ordenados alfabéticamente.
   * 
   * @return el listado de entidades {@link CodigoEconomicoGasto} ordenados
   *         alfabéticamente
   */
  @GetMapping()
  public ResponseEntity<List<CodigoEconomicoGastoOutput>> findAllSorted() {
    log.debug("findAllSorted() - start");
    List<CodigoEconomicoGastoOutput> codigosEconomicos = converter.convert(service.findAllSorted());
    log.debug("findAllSorted() - end");
    return codigosEconomicos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(codigosEconomicos, HttpStatus.OK);
  }

  /**
   * Devuelve la {@link CodigoEconomicoGasto} con el id indicado.
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
