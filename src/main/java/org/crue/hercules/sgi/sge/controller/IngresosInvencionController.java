package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaDto;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaPiiDto;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoDetalleOutput;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoOutput;
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
 * IngresosInvencionController
 */
@RestController
@RequestMapping(IngresosInvencionController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class IngresosInvencionController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "ingresos-invencion";

  public static final String PATH_ID = PATH_DELIMITER + "{invencionId}";
  public static final String PATH_COLUMNAS = PATH_DELIMITER + "columnas";
  public static final String TIPO_OPERACION_PII_INGRESOS = "ING";

  private final EjecucionEconomicaService service;

  /**
   * Obtiene una entidad {@link DatoEconomicoDetalleOutput} por su identificador
   * 
   * @param invencionId identificador del {@link DatoEconomicoDetalleOutput}.
   *                    pertenece el {@link DatoEconomicoDetalleOutput}.
   * @return la entidad {@link DatoEconomicoDetalleOutput}.
   */
  @GetMapping(PATH_ID)
  public DatoEconomicoDetalleOutput findById(@PathVariable String invencionId) {
    log.debug("findById({}, {}) - start", invencionId, TIPO_OPERACION_PII_INGRESOS);
    DatoEconomicoDetalleOutput datoEconomico = service.findById(invencionId, TIPO_OPERACION_PII_INGRESOS);
    log.debug("findById({}, {}) - end", invencionId, TIPO_OPERACION_PII_INGRESOS);
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
    // Añadir condición fija
    String fixedCondition = "tipoOperacion==\"ING\"";

    String queryFinal;
    if (query == null || query.isBlank()) {
      queryFinal = fixedCondition;
    } else {
      queryFinal = query + ";" + fixedCondition;
    }
    List<DatoEconomicoOutput> datosEconomicos = service.findAllDatosEconomicos(queryFinal, paging);
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
  public ResponseEntity<List<DatoEconomicoColumnaPiiDto>> findAllColumnas(
      @RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAllColumnas() - start");
    List<DatoEconomicoColumnaPiiDto> columnas = service.findAllColumnasPii(query, TIPO_OPERACION_PII_INGRESOS, paging);
    log.debug("findAllColumnas() - end");
    return columnas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(columnas, HttpStatus.OK);
  }

}
