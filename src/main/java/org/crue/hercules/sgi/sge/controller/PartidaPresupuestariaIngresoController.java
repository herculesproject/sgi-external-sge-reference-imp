package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import org.crue.hercules.sgi.sge.converter.PartidaPresupuestariaIngresoConverter;
import org.crue.hercules.sgi.sge.dto.PartidaPresupuestariaIngresoOutput;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso;
import org.crue.hercules.sgi.sge.service.PartidaPresupuestariaIngresoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PartidaPresupuestariaIngresoController
 */
@RestController
@RequestMapping(PartidaPresupuestariaIngresoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class PartidaPresupuestariaIngresoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "partidas-presupuestarias-ingresos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final PartidaPresupuestariaIngresoService service;
  private final PartidaPresupuestariaIngresoConverter converter;

  /**
   * Devuelve la lista de {@link PartidaPresupuestariaIngreso} activas en la fecha
   * actual ordenadas alfabéticamente.
   * 
   * @return el listado de entidades {@link PartidaPresupuestariaIngreso}
   *         ordenados
   *         alfabéticamente
   */
  @GetMapping()
  public ResponseEntity<List<PartidaPresupuestariaIngresoOutput>> findAllSorted() {
    log.debug("findAllSorted({}) - start");
    List<PartidaPresupuestariaIngresoOutput> codigosEconomicos = converter
        .convert(service.findAllSorted());
    log.debug("findAllSorted({}) - end");
    return codigosEconomicos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(codigosEconomicos, HttpStatus.OK);
  }

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
