package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaDto;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoDetalleOutput;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoOutput;
import org.crue.hercules.sgi.sge.enums.EjecucionEconomicaTipoOperacionEnum;
import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.crue.hercules.sgi.sge.service.EjecucionEconomicaService;
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
 * EjecucionEconomicaController
 */
@RestController
@RequestMapping(EjecucionEconomicaController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class EjecucionEconomicaController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "ejecucion-economica";

  public static final String PATH_ID = PATH_DELIMITER + "{proyectoId}";
  public static final String PATH_COLUMNAS = PATH_DELIMITER + "columnas";

  private final EjecucionEconomicaService service;

  /**
   * Obtiene una entidad {@link DatoEconomicoDetalleOutput} por su identificador
   * 
   * @param proyectoId    identificador del {@link DatoEconomicoDetalleOutput}.
   * @param tipoOperacion el {@link EjecucionEconomicaTipoOperacionEnum} al que
   *                      pertenece el {@link DatoEconomicoDetalleOutput}.
   * @return la entidad {@link DatoEconomicoDetalleOutput}.
   */
  @GetMapping(PATH_ID)
  public DatoEconomicoDetalleOutput findById(@PathVariable String proyectoId, @RequestParam String tipoOperacion) {
    log.debug("findById({}, {}) - start", proyectoId, tipoOperacion);
    DatoEconomicoDetalleOutput datoEconomico = service.findById(proyectoId, tipoOperacion);
    log.debug("findById({}, {}) - end", proyectoId, tipoOperacion);
    return datoEconomico;
  }

  /**
   * Devuelve una lista de {@link DatoEconomico}s ordenados y filtrados.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link DatoEconomico}s ordenados y filtrados.
   */
  @GetMapping()
  public ResponseEntity<List<DatoEconomicoOutput>> findAllDatosEconomicos(
      @RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAllDatosEconomicos() - start");
    List<DatoEconomicoOutput> datosEconomicos = service.findAllDatosEconomicos(query, paging);
    log.debug("findAllDatosEconomicos() - end");
    return datosEconomicos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(datosEconomicos, HttpStatus.OK);
  }

  /**
   * Devuelve una lista de {@link DatoEconomicoColumnaDto}s ordenados y filtrados.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link DatoEconomicoColumnaDto}s ordenados y
   *         filtrados.
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
