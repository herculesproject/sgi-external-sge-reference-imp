package org.crue.hercules.sgi.sge.controller;

import javax.validation.Valid;

import org.crue.hercules.sgi.sge.converter.FacturaPrevistaConverter;
import org.crue.hercules.sgi.sge.dto.FacturaPrevistaInput;
import org.crue.hercules.sgi.sge.dto.FacturaPrevistaOutput;
import org.crue.hercules.sgi.sge.model.FacturaPrevista;
import org.crue.hercules.sgi.sge.service.FacturaPrevistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * FacturaPrevistaController
 */
@RestController
@RequestMapping(FacturaPrevistaController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class FacturaPrevistaController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "facturas-previstas";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final FacturaPrevistaService service;
  private final FacturaPrevistaConverter converter;

  /**
   * Crea una nueva {@link FacturaPrevista}
   * 
   * @param facturaPrevista la nueva {@link FacturaPrevista}.
   * @return {@link FacturaPrevista} creada
   */
  @PostMapping()
  public ResponseEntity<FacturaPrevistaOutput> create(@Valid @RequestBody FacturaPrevistaInput facturaPrevista) {
    log.debug("create(FacturaPrevistaInput facturaPrevista) - start");
    FacturaPrevistaOutput facturaPrevistaCreated = converter
        .convert(service.create(converter.convert(facturaPrevista)));
    log.debug("create(FacturaPrevistaInput facturaPrevista) - end");
    return new ResponseEntity<>(facturaPrevistaCreated, HttpStatus.OK);
  }

  /**
   * Actualiza una {@link FacturaPrevista}
   * 
   * @param facturaPrevista la {@link FacturaPrevista} actualizada.
   * @param id              Identificador de la {@link FacturaPrevista}.
   * @return {@link FacturaPrevista} actualizada.
   */
  @PutMapping(PATH_ID)
  public ResponseEntity<FacturaPrevistaOutput> update(@Valid @RequestBody FacturaPrevistaInput facturaPrevista,
      @PathVariable Long id) {
    log.debug("update(FacturaPrevistaInput facturaPrevista, Long id) - start");
    FacturaPrevistaOutput facturaPrevistaUpdated = converter
        .convert(service.update(converter.convert(facturaPrevista, id)));
    log.debug("update(FacturaPrevistaInput facturaPrevista, Long id) - end");
    return new ResponseEntity<>(facturaPrevistaUpdated, HttpStatus.OK);
  }

}
