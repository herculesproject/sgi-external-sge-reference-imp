package org.crue.hercules.sgi.sge.service;

import java.util.List;

import org.crue.hercules.sgi.sge.exceptions.CodigoEconomicoGastoNotFoundException;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoGasto;
import org.crue.hercules.sgi.sge.model.CodigoEconomicoGasto_;
import org.crue.hercules.sgi.sge.repository.CodigoEconomicoGastoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodigoEconomicoGastoService {

  private final CodigoEconomicoGastoRepository repository;

  /**
   * Obtiene una entidad {@link CodigoEconomicoGasto} por id.
   * 
   * @param id identificador de la entidad {@link CodigoEconomicoGasto}.
   * @return la entidad {@link CodigoEconomicoGasto}.
   */
  public CodigoEconomicoGasto findById(String id) {
    log.debug("findById(String id) - start");
    final CodigoEconomicoGasto codigoEconomicoGasto = repository.findById(id)
        .orElseThrow(() -> new CodigoEconomicoGastoNotFoundException(id));
    log.debug("findById(String id) - end");
    return codigoEconomicoGasto;
  }

  /**
   * Obtiene todas las entidades {@link CodigoEconomicoGasto} ordenados
   * por id.
   *
   * @return el listado de entidades {@link CodigoEconomicoGasto} ordenados
   *         por id.
   */
  public List<CodigoEconomicoGasto> findAllSorted() {
    log.debug("findAllSorted() - start");
    List<CodigoEconomicoGasto> codigosEconomicosGasto = repository
        .findAll(Sort.by(Sort.Direction.ASC, CodigoEconomicoGasto_.ID));
    log.debug("findAllSorted() - end");
    return codigosEconomicosGasto;
  }

}
