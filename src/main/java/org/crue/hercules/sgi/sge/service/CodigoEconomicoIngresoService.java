package org.crue.hercules.sgi.sge.service;

import java.util.List;

import org.crue.hercules.sgi.sge.exceptions.CodigoEconomicoIngresoNotFoundException;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoIngreso;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoIngreso_;
import org.crue.hercules.sgi.sge.repository.CodigoEconomicoIngresoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodigoEconomicoIngresoService {

  private final CodigoEconomicoIngresoRepository repository;

  /**
   * Obtiene una entidad {@link CodigoEconomicoIngreso} por id.
   * 
   * @param id identificador de la entidad {@link CodigoEconomicoIngreso}.
   * @return la entidad {@link CodigoEconomicoIngreso}.
   */
  public CodigoEconomicoIngreso findById(String id) {
    log.debug("findById(String id) - start");
    final CodigoEconomicoIngreso codigoEconomicoIngreso = repository.findById(id)
        .orElseThrow(() -> new CodigoEconomicoIngresoNotFoundException(id));
    log.debug("findById(String id) - end");
    return codigoEconomicoIngreso;
  }

  /**
   * Obtiene todas las entidades {@link CodigoEconomicoIngreso} ordenados
   * por id.
   *
   * @return el listado de entidades {@link CodigoEconomicoIngreso} ordenados
   *         por id.
   */
  public List<CodigoEconomicoIngreso> findAllSorted() {
    log.debug("findAllSorted() - start");
    List<CodigoEconomicoIngreso> codigosEconomicosIngreso = repository
        .findAll(Sort.by(Sort.Direction.ASC, CodigoEconomicoIngreso_.ID));
    log.debug("findAllSorted() - end");
    return codigosEconomicosIngreso;
  }

}
