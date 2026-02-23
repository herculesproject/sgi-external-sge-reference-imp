package org.crue.hercules.sgi.sge.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLJPASupport;
import org.crue.hercules.sgi.sge.config.SgiConfigProperties;
import org.crue.hercules.sgi.sge.dto.CodigoEconomicoOutput;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaDto;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaPiiDto;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoDetalleOutput;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoOutput;
import org.crue.hercules.sgi.sge.enums.EjecucionEconomicaTipoOperacionEnum;
import org.crue.hercules.sgi.sge.exceptions.EjecucionEconomicaTipoOperacionNotValidException;
import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.crue.hercules.sgi.sge.model.DocumentoDetalle;
import org.crue.hercules.sgi.sge.repository.DatoEconomicoRepository;
import org.crue.hercules.sgi.sge.repository.DocumentoDatoEconomicoRepository;
import org.crue.hercules.sgi.sge.repository.predicate.DatoEconomicoPredicateResolver;
import org.crue.hercules.sgi.sge.util.DatoEconomicoColumnasFactory;
import org.crue.hercules.sgi.sge.util.DatoEconomicoUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EjecucionEconomicaService {

  private final DatoEconomicoRepository repository;

  private final DocumentoDatoEconomicoRepository documentoRepository;

  private final SgiConfigProperties sgiConfigProperties;

  /**
   * Obtiene una entidad {@link DatoEconomicoDetalleOutput} por su identificador
   * 
   * @param id            identificador del {@link DatoEconomicoDetalleOutput}.
   * @param tipoOperacion el {@link EjecucionEconomicaTipoOperacionEnum} al que
   *                      pertenece el {@link DatoEconomicoDetalleOutput}.
   * @return la entidad {@link DatoEconomicoDetalleOutput}.
   */
  public DatoEconomicoDetalleOutput findById(String id, String tipoOperacion) {
    log.debug("findById({}, {}) - end", id, tipoOperacion);
    return getDetalleDatoEconomico(id, EjecucionEconomicaTipoOperacionEnum.valueOf(tipoOperacion));
  }

  /**
   * Devuelve una lista de {@link DatoEconomico}s ordenados y filtrados.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link DatoEconomico}s ordenados y filtrados.
   */
  public List<DatoEconomicoOutput> findAllDatosEconomicos(String query, Pageable paging) {
    log.debug("findAllDatosEconomicos({}, {}) - start", query, paging);

    Map<String, String> params = DatoEconomicoUtil.parseQuery(query);
    EjecucionEconomicaTipoOperacionEnum tipoOperacion = EjecucionEconomicaTipoOperacionEnum
        .valueOf(params.get("tipoOperacion"));

    Specification<DatoEconomico> spec = SgiRSQLJPASupport.toSpecification(query,
        DatoEconomicoPredicateResolver.getInstance(sgiConfigProperties));

    log.debug("findAllDatosEconomicos({}, {}) - end", query, paging);
    return getDatosEconomicos(spec, tipoOperacion, paging);
  }

  public List<DatoEconomicoColumnaDto> findAllColumnas(String query, String tipoOperacion, Pageable paging) {

    log.debug("findAllColumnas({}, {}) - end", paging);
    return getColumnasPorTipo(EjecucionEconomicaTipoOperacionEnum.valueOf(tipoOperacion), true);
  }

  public List<DatoEconomicoColumnaPiiDto> findAllColumnasPii(String query, String tipoOperacion, Pageable paging) {

    log.debug("findAllColumnasPii({}, {}) - end", paging);
    return getColumnasPiiPorTipo(EjecucionEconomicaTipoOperacionEnum.valueOf(tipoOperacion), true);
  }

  /**
   * Devuelve una lista de {@link DatoEconomicoColumnaDto}s ordenados y filtrados.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link DatoEconomicoColumnaDto}s ordenados y
   *         filtrados.
   */
  public List<DatoEconomicoColumnaDto> findAllColumnas(String query, Pageable paging) {
    log.debug("findAllColumnas({}, {}) - start", paging);

    Map<String, String> params = DatoEconomicoUtil.parseQuery(query);
    EjecucionEconomicaTipoOperacionEnum tipoOperacion = EjecucionEconomicaTipoOperacionEnum
        .valueOf(params.get("tipoOperacion"));

    log.debug("findAllColumnas({}, {}) - end", paging);
    return getColumnasPorTipo(tipoOperacion, true);
  }

  public List<DatoEconomicoOutput> getDatosEconomicos(
      Specification<DatoEconomico> spec,
      EjecucionEconomicaTipoOperacionEnum tipoOperacion,
      Pageable paging) {

    List<DatoEconomico> resultados = repository.findAll(spec);

    if (resultados.isEmpty()) {
      return Collections.emptyList();
    }

    // Obtener definición de columnas según tipo
    List<DatoEconomicoColumnaDto> columnasDef = getColumnasPorTipo(tipoOperacion, true);
    if (columnasDef == null || columnasDef.isEmpty()) {
      throw new EjecucionEconomicaTipoOperacionNotValidException(tipoOperacion.name());
    }

    return resultados.stream()
        .sorted(
            DatoEconomicoUtil.getComparator(paging.getSort()))
        .map(dato -> {
          Map<String, Object> columnas = buildColumnasMap(dato, columnasDef, tipoOperacion, true);

          return new DatoEconomicoOutput(
              dato.getId(),
              dato.getProyectoId(),
              dato.getAplicacionPresupuestaria(),
              new CodigoEconomicoOutput(dato.getCodigoEconomico(), null),
              dato.getAnualidad() != null ? dato.getAnualidad().toString() : null,
              dato.getTipo() != null ? dato.getTipo().getDescripcion() : null,
              dato.getFechaDevengo() != null
                  ? dato.getFechaDevengo().atZone(sgiConfigProperties.getTimeZone().toZoneId()).toLocalDate()
                  : null,
              null,
              columnas);
        })
        .collect(Collectors.toList());
  }

  public DatoEconomicoDetalleOutput getDetalleDatoEconomico(String id,
      EjecucionEconomicaTipoOperacionEnum tipoOperacion) {
    Optional<DatoEconomico> datoEconomico = repository.findById(id);
    DatoEconomicoDetalleOutput datoEconomicoDetalle = null;
    if (datoEconomico.isPresent()) {
      List<DatoEconomicoColumnaDto> definicionColumnas = getColumnasPorTipo(tipoOperacion, false);
      Map<String, Object> columnas = buildColumnasMap(datoEconomico.get(), definicionColumnas, tipoOperacion, false);
      datoEconomicoDetalle = getDatoEconomicoDetalle(datoEconomico.get(), definicionColumnas, columnas);
    }

    return datoEconomicoDetalle;
  }

  private DatoEconomicoDetalleOutput getDatoEconomicoDetalle(DatoEconomico datoEconomico,
      List<DatoEconomicoColumnaDto> definicionColumnas, Map<String, Object> columnas) {
    if (datoEconomico == null) {
      return null;
    }

    List<DocumentoDetalle> documentos = documentoRepository.findByDatoEconomicoId(datoEconomico.getId());

    return DatoEconomicoDetalleOutput.builder()
        .id(datoEconomico.getId())
        .anualidad(datoEconomico.getAnualidad())
        .campos(DatoEconomicoUtil.convertColumnasToCampos(columnas, definicionColumnas))
        .clasificacionSGE(null)
        .codigoEconomico(null)
        .fechaDevengo(datoEconomico.getFechaDevengo() != null
            ? datoEconomico.getFechaDevengo().atZone(sgiConfigProperties.getTimeZone().toZoneId()).toLocalDate()
            : null)
        .partidaPresupuestaria(datoEconomico.getAplicacionPresupuestaria())
        .proyectoId(datoEconomico.getProyectoId())
        .documentos(documentos)
        .build();
  }

  private List<DatoEconomicoColumnaDto> getColumnasPorTipo(EjecucionEconomicaTipoOperacionEnum tipoOperacion,
      boolean reducido) {
    switch (tipoOperacion) {
      case EPA:
        return DatoEconomicoColumnasFactory.getColumnasEPA(reducido);
      case EPG:
        return DatoEconomicoColumnasFactory.getColumnasEPG(reducido);
      case EPI:
        return DatoEconomicoColumnasFactory.getColumnasEPI(reducido);
      case DOG:
        return DatoEconomicoColumnasFactory.getColumnasDOG(reducido);
      case DOI:
        return DatoEconomicoColumnasFactory.getColumnasDOI(reducido);
      case DOM:
        return DatoEconomicoColumnasFactory.getColumnasDOM(reducido);
      case FJF:
        return DatoEconomicoColumnasFactory.getColumnasFJF(reducido);
      case FJV:
        return DatoEconomicoColumnasFactory.getColumnasFJV(reducido);
      case FJP:
        return DatoEconomicoColumnasFactory.getColumnasFJP(reducido);
      case GAS:
        return DatoEconomicoColumnasFactory.getColumnasGAS(reducido);
      case ING:
        return DatoEconomicoColumnasFactory.getColumnasING(reducido);
      case REP:
        return DatoEconomicoColumnasFactory.getColumnasREP(reducido);
      default:
        throw new EjecucionEconomicaTipoOperacionNotValidException(tipoOperacion.name());
    }
  }

  private List<DatoEconomicoColumnaPiiDto> getColumnasPiiPorTipo(EjecucionEconomicaTipoOperacionEnum tipoOperacion,
      boolean reducido) {
    switch (tipoOperacion) {
      case GAS:
        return DatoEconomicoColumnasFactory.getColumnasPiiGAS(reducido);
      case ING:
        return DatoEconomicoColumnasFactory.getColumnasPiiING();
      default:
        throw new EjecucionEconomicaTipoOperacionNotValidException(tipoOperacion.name());
    }
  }

  private Map<String, Object> buildColumnasMap(DatoEconomico dato, List<DatoEconomicoColumnaDto> columnasDef,
      EjecucionEconomicaTipoOperacionEnum tipoOperacion, boolean reducido) {

    Map<String, Object> columnas = new HashMap<>();

    switch (tipoOperacion) {
      case EPA:
        columnas.put(columnasDef.get(0).getId(), dato.getImporteInicial());
        columnas.put(columnasDef.get(1).getId(), dato.getImporteDefinitivo());
        columnas.put(columnasDef.get(2).getId(), dato.getImporteDisponible());
        columnas.put(columnasDef.get(3).getId(), dato.getDerechos());
        columnas.put(columnasDef.get(4).getId(), dato.getCobros());
        break;
      case EPG:
        columnas.put(columnasDef.get(0).getId(), dato.getImportePresupuestado());
        columnas.put(columnasDef.get(1).getId(), dato.getImporteConcedido());
        columnas.put(columnasDef.get(2).getId(), dato.getImporteDefinitivo());
        columnas.put(columnasDef.get(3).getId(), dato.getGastado());
        columnas.put(columnasDef.get(4).getId(), dato.getPagado());
        columnas.put(columnasDef.get(5).getId(), dato.getPendientePago());
        columnas.put(columnasDef.get(6).getId(), dato.getReservado());
        columnas.put(columnasDef.get(7).getId(), dato.getImporteDisponible());
        break;
      case EPI:
        columnas.put(columnasDef.get(0).getId(), dato.getImportePresupuestado());
        columnas.put(columnasDef.get(1).getId(), dato.getImporteDefinitivo());
        columnas.put(columnasDef.get(2).getId(), dato.getDerechos());
        columnas.put(columnasDef.get(3).getId(), dato.getCobros());
        break;
      case DOG:
        columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
        columnas.put(columnasDef.get(1).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
        columnas.put(columnasDef.get(2).getId(), dato.getDescripcion());
        columnas.put(columnasDef.get(3).getId(), dato.getGastadoEjecutado());
        columnas.put(columnasDef.get(4).getId(), dato.getGastadoComprometido());
        columnas.put(columnasDef.get(5).getId(), dato.getDisponible());
        break;
      case DOI:
        columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
        columnas.put(columnasDef.get(1).getId(), dato.getDescripcion());
        columnas.put(columnasDef.get(2).getId(), dato.getTercero());
        columnas.put(columnasDef.get(3).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
        columnas.put(columnasDef.get(4).getId(), dato.getImporteInicial());
        columnas.put(columnasDef.get(5).getId(), dato.getImporteIva());
        break;
      case DOM:
        columnas.put(columnasDef.get(0).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
        columnas.put(columnasDef.get(1).getId(), dato.getDescripcion());
        columnas.put(columnasDef.get(2).getId(), dato.getImporteInicial());
        break;
      case FJF:
        if (reducido) {
          columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
          columnas.put(columnasDef.get(1).getId(), dato.getDescripcion());
          columnas.put(columnasDef.get(2).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
          columnas.put(columnasDef.get(3).getId(), dato.getImporteInicial());
        } else {
          columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
          columnas.put(columnasDef.get(1).getId(), dato.getDescripcion());
          columnas.put(columnasDef.get(2).getId(), dato.getTercero());
          columnas.put(columnasDef.get(3).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
          columnas.put(columnasDef.get(4).getId(), dato.getImporteInicial());
          columnas.put(columnasDef.get(5).getId(), dato.getImporteIva());
          columnas.put(columnasDef.get(6).getId(), dato.getPagado());
        }
        break;
      case FJV:
        if (reducido) {
          columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
          columnas.put(columnasDef.get(1).getId(), dato.getNumComision());
          columnas.put(columnasDef.get(2).getId(), dato.getDescripcion());
          columnas.put(columnasDef.get(3).getId(), dato.getTercero());
          columnas.put(columnasDef.get(4).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
          columnas.put(columnasDef.get(5).getId(), dato.getImporteDefinitivo());
        } else {
          columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
          columnas.put(columnasDef.get(1).getId(), dato.getNumComision());
          columnas.put(columnasDef.get(2).getId(), dato.getDescripcion());
          columnas.put(columnasDef.get(3).getId(), dato.getTercero());
          columnas.put(columnasDef.get(4).getId(), dato.getDestino());
          columnas.put(columnasDef.get(5).getId(), dato.getFechaSalida());
          columnas.put(columnasDef.get(6).getId(), dato.getFechaVuelta());
          columnas.put(columnasDef.get(7).getId(), dato.getImporteLocomocion());
          columnas.put(columnasDef.get(8).getId(), dato.getImporteAlojamiento());
          columnas.put(columnasDef.get(9).getId(), dato.getImporteDietas());
          columnas.put(columnasDef.get(10).getId(), dato.getImporteDefinitivo());
          columnas.put(columnasDef.get(11).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
        }
        break;
      case FJP:
        columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
        columnas.put(columnasDef.get(1).getId(), dato.getDescripcion());
        columnas.put(columnasDef.get(2).getId(), dato.getNombreCompleto());
        columnas.put(columnasDef.get(3).getId(), dato.getNumIdentificacion());
        columnas.put(columnasDef.get(4).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
        columnas.put(columnasDef.get(5).getId(), dato.getImporteInicial());
        break;
      case GAS:
        if (reducido) {
          columnas.put(columnasDef.get(0).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
          columnas.put(columnasDef.get(1).getId(), dato.getNumDocumento());
          columnas.put(columnasDef.get(2).getId(), dato.getDescripcion());
          columnas.put(columnasDef.get(3).getId(), dato.getTipo());
          columnas.put(columnasDef.get(4).getId(), dato.getImporteInicial());
        } else {
          columnas.put(columnasDef.get(0).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
          columnas.put(columnasDef.get(1).getId(), dato.getTipo());
          columnas.put(columnasDef.get(2).getId(), dato.getNumDocumento());
          columnas.put(columnasDef.get(3).getId(), dato.getDescripcion());
          columnas.put(columnasDef.get(4).getId(), dato.getTercero());
          columnas.put(columnasDef.get(5).getId(), dato.getImporteInicial());
          columnas.put(columnasDef.get(6).getId(), dato.getImporteIva());
          columnas.put(columnasDef.get(7).getId(), dato.getImporteDefinitivo());
        }
        break;
      case ING:
        columnas.put(columnasDef.get(0).getId(), dato.getNumDocumento());
        columnas.put(columnasDef.get(1).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
        columnas.put(columnasDef.get(2).getId(), dato.getDescripcion());
        columnas.put(columnasDef.get(3).getId(), dato.getTercero());
        columnas.put(columnasDef.get(4).getId(), dato.getImporteInicial());
        columnas.put(columnasDef.get(5).getId(), dato.getImporteIva());
        break;
      case REP:
        columnas.put(columnasDef.get(0).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
        columnas.put(columnasDef.get(1).getId(), dato.getNumDocumento());
        columnas.put(columnasDef.get(2).getId(), dato.getDescripcion());
        columnas.put(columnasDef.get(3).getId(), dato.getTipo().name());
        columnas.put(columnasDef.get(4).getId(), dato.getImporteInicial());
        break;
      default:
        throw new EjecucionEconomicaTipoOperacionNotValidException(tipoOperacion.name());
    }

    return columnas;
  }

}
