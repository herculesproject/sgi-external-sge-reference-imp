package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import org.crue.hercules.sgi.sge.converter.PartidaPresupuestariaGastoConverter;
import org.crue.hercules.sgi.sge.dto.PartidaPresupuestariaGastoOutput;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto;
import org.crue.hercules.sgi.sge.service.PartidaPresupuestariaGastoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PartidaPresupuestariaGastoController
 */
@RestController
@RequestMapping(PartidaPresupuestariaGastoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class PartidaPresupuestariaGastoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "partidas-presupuestarias-gastos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final PartidaPresupuestariaGastoService service;
  private final PartidaPresupuestariaGastoConverter converter;

  /**
   * Devuelve la lista de {@link PartidaPresupuestariaGasto} activas en la fecha
   * actual ordenadas alfabéticamente.
   * 
   * @return el listado de entidades {@link PartidaPresupuestariaGasto} ordenados
   *         alfabéticamente
   */
  @GetMapping()
  public ResponseEntity<List<PartidaPresupuestariaGastoOutput>> findAllSorted() {
    log.debug("findAllSorted({}) - start");
    List<PartidaPresupuestariaGastoOutput> codigosEconomicos = converter
        .convert(service.findAllSorted());
    log.debug("findAllSorted({}) - end");
    return codigosEconomicos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(codigosEconomicos, HttpStatus.OK);
  }

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
