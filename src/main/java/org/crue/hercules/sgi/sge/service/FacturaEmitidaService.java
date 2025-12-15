package org.crue.hercules.sgi.sge.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLJPASupport;
import org.crue.hercules.sgi.sge.config.SgiConfigProperties;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaDto;
import org.crue.hercules.sgi.sge.dto.FacturaEmitidaDetalleOutput;
import org.crue.hercules.sgi.sge.dto.FacturaEmitidaOutput;
import org.crue.hercules.sgi.sge.enums.EjecucionEconomicaTipoOperacionEnum;
import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.crue.hercules.sgi.sge.model.DatoEconomico_;
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
public class FacturaEmitidaService {

  private final DatoEconomicoRepository repository;
  private final DocumentoDatoEconomicoRepository documentoRepository;
  private final SgiConfigProperties sgiConfigProperties;

  /**
   * Obtiene una entidad {@link FacturaEmitidaDetalleOutput} por su identificador
   * 
   * @param id identificador del {@link FacturaEmitidaDetalleOutput}.
   * @return la entidad {@link FacturaEmitidaDetalleOutput}.
   */
  public FacturaEmitidaDetalleOutput findById(String id) {
    log.debug("findById({}) - end", id);
    return getDetalleFacturaEmitida(id);
  }

  /**
   * Devuelve una lista de {@link FacturaEmitidaDetalleOutput}s ordenadas y
   * filtradas.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link FacturaEmitidaDetalleOutput}s
   *         ordenadas y
   *         filtradas.
   */
  public List<FacturaEmitidaOutput> findAllFacturasEmitidas(String query, Pageable paging) {
    log.debug("findAllFacturasEmitidas({}, {}) - start", query, paging);

    Specification<DatoEconomico> spec = SgiRSQLJPASupport.toSpecification(query,
        DatoEconomicoPredicateResolver.getInstance(sgiConfigProperties));

    // Añadir filtro por tipo de factura-emitida
    Specification<DatoEconomico> filtroTipoOperacion = (root, query1, cb) -> cb
        .equal(root.get(DatoEconomico_.tipoOperacion), EjecucionEconomicaTipoOperacionEnum.FEM);

    Specification<DatoEconomico> specFinal = Specification.where(spec).and(filtroTipoOperacion);

    return getFacturasEmitidas(specFinal, paging);
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
    log.debug("findAllColumnas({}, {}) - end", query, paging);
    return DatoEconomicoColumnasFactory.getColumnasFEM(true);
  }

  public List<FacturaEmitidaOutput> getFacturasEmitidas(Specification<DatoEconomico> spec, Pageable paging) {

    List<DatoEconomico> resultados = repository.findAll(spec);

    if (resultados.isEmpty()) {
      return Collections.emptyList();
    }

    // Obtener definición de columnas según tipo
    List<DatoEconomicoColumnaDto> columnasDef = DatoEconomicoColumnasFactory.getColumnasFEM(true);

    return resultados.stream()
        .sorted(
            DatoEconomicoUtil.getComparator(paging.getSort()))
        .map(dato -> {
          Map<String, Object> columnas = buildColumnasMap(dato, columnasDef, true);

          return new FacturaEmitidaOutput(
              dato.getId(),
              dato.getProyectoId(),
              dato.getAnualidad(),
              dato.getNumDocumento(),
              columnas);
        })
        .collect(Collectors.toList());
  }

  private Map<String, Object> buildColumnasMap(DatoEconomico dato, List<DatoEconomicoColumnaDto> columnasDef,
      boolean reducido) {

    Map<String, Object> columnas = new HashMap<>();

    if (reducido) {
      columnas.put(columnasDef.get(0).getId(), dato.getNumPrevision());
      columnas.put(columnasDef.get(1).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
      columnas.put(columnasDef.get(2).getId(), dato.getTercero());
      columnas.put(columnasDef.get(3).getId(), dato.getDescripcion());
      columnas.put(columnasDef.get(4).getId(), dato.getImporteInicial());
      columnas.put(columnasDef.get(5).getId(), dato.getImporteDefinitivo());
    } else {
      columnas.put(columnasDef.get(0).getId(), dato.getNumPrevision());
      columnas.put(columnasDef.get(1).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFecha()));
      columnas.put(columnasDef.get(2).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFechaEmision()));
      columnas.put(columnasDef.get(3).getId(), dato.getTercero());
      columnas.put(columnasDef.get(4).getId(), dato.getDescripcion());
      columnas.put(columnasDef.get(5).getId(), dato.getImporteInicial());
      columnas.put(columnasDef.get(6).getId(), dato.getPorcentajeIVA());
      columnas.put(columnasDef.get(7).getId(), dato.getImporteIva());
      columnas.put(columnasDef.get(8).getId(), dato.getImporteDefinitivo());
      columnas.put(columnasDef.get(9).getId(), dato.getEstado());
      columnas.put(columnasDef.get(10).getId(), DatoEconomicoUtil.formatInstantToDateString(dato.getFechaCobro()));
      columnas.put(columnasDef.get(11).getId(), dato.getCobros());
    }

    return columnas;
  }

  public FacturaEmitidaDetalleOutput getDetalleFacturaEmitida(String id) {
    Optional<DatoEconomico> datoEconomico = repository.findById(id);
    FacturaEmitidaDetalleOutput facturaEmitidaDetalle = null;
    if (datoEconomico.isPresent()) {
      List<DatoEconomicoColumnaDto> definicionColumnas = DatoEconomicoColumnasFactory.getColumnasFEM(false);
      Map<String, Object> columnas = buildColumnasMap(datoEconomico.get(), definicionColumnas, false);
      facturaEmitidaDetalle = getFacturaEmitidaDetalle(datoEconomico.get(), definicionColumnas, columnas);
    }

    return facturaEmitidaDetalle;
  }

  private FacturaEmitidaDetalleOutput getFacturaEmitidaDetalle(DatoEconomico datoEconomico,
      List<DatoEconomicoColumnaDto> definicionColumnas, Map<String, Object> columnas) {
    if (datoEconomico == null) {
      return null;
    }

    List<DocumentoDetalle> documentos = documentoRepository.findByDatoEconomicoId(datoEconomico.getId());

    return FacturaEmitidaDetalleOutput.builder()
        .id(datoEconomico.getId())
        .numeroFactura(datoEconomico.getNumDocumento())
        .anualidad(datoEconomico.getAnualidad())
        .campos(DatoEconomicoUtil.convertColumnasToCampos(columnas, definicionColumnas))
        .proyectoId(datoEconomico.getProyectoId())
        .documentos(documentos)
        .build();
  }

}
