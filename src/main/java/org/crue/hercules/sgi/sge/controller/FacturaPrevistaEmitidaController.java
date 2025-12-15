package org.crue.hercules.sgi.sge.controller;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sge.converter.FacturaPrevistaConverter;
import org.crue.hercules.sgi.sge.dto.FacturaPrevistaOutput;
import org.crue.hercules.sgi.sge.model.FacturaPrevista;
import org.crue.hercules.sgi.sge.service.FacturaPrevistaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * FacturaPrevistaEmitidaController
 */
@RestController
@RequestMapping(FacturaPrevistaEmitidaController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class FacturaPrevistaEmitidaController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "facturas-previstas-emitidas";

  private final FacturaPrevistaService service;
  private final FacturaPrevistaConverter converter;

  /**
   * Devuelve una lista paginada y filtrada de {@link FacturaPrevista}s.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link FacturaPrevista}s paginadas y
   *         filtradas.
   */
  @GetMapping()
  public ResponseEntity<Page<FacturaPrevistaOutput>> findAll(
      @RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAll({}, {}) - start", query, paging);
    Page<FacturaPrevistaOutput> page = converter.convert(service.findAll(query, paging));
    log.debug("findAll({}, {}) - end", query, paging);
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

}