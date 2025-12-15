package org.crue.hercules.sgi.sge.service;

import java.time.Instant;
import java.util.List;

import org.crue.hercules.sgi.sge.config.SgiConfigProperties;
import org.crue.hercules.sgi.sge.exceptions.PartidaPresupuestariaGastoNotFoundException;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto_;
import org.crue.hercules.sgi.sge.repository.PartidaPresupuestariaGastoRepository;
import org.crue.hercules.sgi.sge.repository.specification.PartidaPresupuestariaGastoSpecifications;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartidaPresupuestariaGastoService {

  private final PartidaPresupuestariaGastoRepository repository;
  private final SgiConfigProperties sgiConfigProperties;

  /**
   * Obtiene una entidad {@link PartidaPresupuestariaGasto} por id.
   * 
   * @param id identificador de la entidad {@link PartidaPresupuestariaGasto}.
   * 
   * @return la entidad {@link PartidaPresupuestariaGasto}.
   */
  public PartidaPresupuestariaGasto findById(String id) {
    log.debug("findById(String id) - start");
    final PartidaPresupuestariaGasto partidaPresupuestaria = repository.findById(id)
        .orElseThrow(() -> new PartidaPresupuestariaGastoNotFoundException(id));
    log.debug("findById(String id) - end");
    return partidaPresupuestaria;
  }

  /**
   * Obtiene todas las entidades {@link PartidaPresupuestariaGasto} ordenados por
   * id activas en la fecha actual.
   *
   * @return el listado de entidades {@link PartidaPresupuestariaGasto} ordenadas
   *         por id.
   */
  public List<PartidaPresupuestariaGasto> findAllSorted() {
    log.debug("findAllSorted() - start");

    Instant fechaActual = Instant.now().atZone(sgiConfigProperties.getTimeZone().toZoneId()).toInstant();

    Specification<PartidaPresupuestariaGasto> spec = PartidaPresupuestariaGastoSpecifications.activas(fechaActual);

    List<PartidaPresupuestariaGasto> partidasPresupuestarias = repository
        .findAll(spec, Sort.by(Sort.Direction.ASC, PartidaPresupuestariaGasto_.CODIGO));
    log.debug("findAllSorted() - end");
    return partidasPresupuestarias;
  }

}
