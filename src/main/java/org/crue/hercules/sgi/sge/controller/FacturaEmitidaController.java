package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaDto;
import org.crue.hercules.sgi.sge.dto.FacturaEmitidaDetalleOutput;
import org.crue.hercules.sgi.sge.dto.FacturaEmitidaOutput;
import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.crue.hercules.sgi.sge.service.FacturaEmitidaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * FacturaEmitidaController
 */
@RestController
@RequestMapping(FacturaEmitidaController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class FacturaEmitidaController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "facturas-emitidas";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";
  public static final String PATH_COLUMNAS = PATH_DELIMITER + "columnas";

  private final FacturaEmitidaService service;

  /**
   * Obtiene una entidad {@link FacturaEmitidaDetalleOutput} por su identificador
   * 
   * @param id identificador del {@link FacturaEmitidaDetalleOutput}.
   * @return la entidad {@link FacturaEmitidaDetalleOutput}.
   */
  @GetMapping(PATH_ID)
  public FacturaEmitidaDetalleOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    FacturaEmitidaDetalleOutput facturaEmitidaDetalle = service.findById(id);
    log.debug("findById({}}) - end", id);
    return facturaEmitidaDetalle;
  }

  /**
   * Devuelve una lista de {@link FacturaEmitidaDetalleOutput}s ordenadas y
   * filtradas.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link DatoEconomico}s ordenadas y filtradas.
   */
  @GetMapping()
  public ResponseEntity<List<FacturaEmitidaOutput>> findAllFacturasEmitidas(
      @RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAllFacturasEmitidas() - start");
    List<FacturaEmitidaOutput> facturasEmitidas = service.findAllFacturasEmitidas(query, paging);
    log.debug("findAllFacturasEmitidas() - end");
    return facturasEmitidas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(facturasEmitidas, HttpStatus.OK);
  }

  /**
   * Devuelve una lista de {@link DatoEconomicoColumnaDto}s ordenadas y filtradas.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link DatoEconomicoColumnaDto}s ordenadas y
   *         filtradas.
   */
  @GetMapping(PATH_COLUMNAS)
  public ResponseEntity<List<DatoEconomicoColumnaDto>> findAllColumnas(
      @RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAllColumnas() - start");
    List<DatoEconomicoColumnaDto> columnas = service.findAllColumnas(query, paging);
    log.debug("findAllColumnas() - end");
    return columnas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(columnas, HttpStatus.OK);
  }

}