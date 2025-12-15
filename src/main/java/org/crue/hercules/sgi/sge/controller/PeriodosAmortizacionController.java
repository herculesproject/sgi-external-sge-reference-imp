package org.crue.hercules.sgi.sge.controller;

import javax.validation.Valid;

import org.crue.hercules.sgi.sge.dto.PeriodoAmortizacionInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import liquibase.pro.packaged.T;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PeriodosAmortizacionController
 */
@RestController
@RequestMapping(PeriodosAmortizacionController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class PeriodosAmortizacionController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "periodos-amortizacion";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  /**
   * Mock de un nuevo {@link PeriodoAmortizacionInput}
   * 
   * @param periodoAmortizacion nuevo {@link PeriodoAmortizacionInput}.
   * @return {@link PeriodoAmortizacionInput} creado
   */
  @PostMapping()
  public ResponseEntity<T> create(@Valid @RequestBody PeriodoAmortizacionInput periodoAmortizacion) {
    log.debug("create(PeriodosAmortizacionInput periodoAmortizacion) - end");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Actualiza un {@link PeriodoAmortizacionInput}
   * 
   * @param periodoAmortizacion el {@link PeriodoAmortizacionInput} actualizado.
   * @param id                  Identificador del
   *                            {@link PeriodoAmortizacionInput}.
   * @return {@link PeriodoAmortizacionInput} actualizado.
   */
  @PutMapping(PATH_ID)
  public ResponseEntity<T> update(@Valid @RequestBody PeriodoAmortizacionInput periodoAmortizacion,
      @PathVariable Long id) {
    log.debug("update(PeriodoAmortizacionInput periodoAmortizacion, Long id) - end");
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
