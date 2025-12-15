package org.crue.hercules.sgi.sge.service;

import java.time.Instant;
import java.util.List;

import org.crue.hercules.sgi.sge.config.SgiConfigProperties;
import org.crue.hercules.sgi.sge.exceptions.PartidaPresupuestariaIngresoNotFoundException;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso;
import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso_;
import org.crue.hercules.sgi.sge.repository.PartidaPresupuestariaIngresoRepository;
import org.crue.hercules.sgi.sge.repository.specification.PartidaPresupuestariaIngresoSpecifications;
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
public class PartidaPresupuestariaIngresoService {

  private final PartidaPresupuestariaIngresoRepository repository;
  private final SgiConfigProperties sgiConfigProperties;

  /**
   * Obtiene una entidad {@link PartidaPresupuestariaIngreso} por id.
   * 
   * @param id identificador de la entidad {@link PartidaPresupuestariaIngreso}.
   * 
   * @return la entidad {@link PartidaPresupuestariaIngreso}.
   */
  public PartidaPresupuestariaIngreso findById(String id) {
    log.debug("findById(String id) - start");
    final PartidaPresupuestariaIngreso partidaPresupuestaria = repository.findById(id)
        .orElseThrow(() -> new PartidaPresupuestariaIngresoNotFoundException(id));
    log.debug("findById(String id) - end");
    return partidaPresupuestaria;
  }

  /**
   * Obtiene todas las entidades {@link PartidaPresupuestariaIngreso} ordenados
   * por
   * id activas en la fecha actual.
   *
   * @return el listado de entidades {@link PartidaPresupuestariaIngreso}
   *         ordenadas
   *         por id.
   */
  public List<PartidaPresupuestariaIngreso> findAllSorted() {
    log.debug("findAllSorted() - start");

    Instant fechaActual = Instant.now().atZone(sgiConfigProperties.getTimeZone().toZoneId()).toInstant();

    Specification<PartidaPresupuestariaIngreso> spec = PartidaPresupuestariaIngresoSpecifications.activas(fechaActual);

    List<PartidaPresupuestariaIngreso> partidasPresupuestarias = repository
        .findAll(spec, Sort.by(Sort.Direction.ASC, PartidaPresupuestariaIngreso_.CODIGO));
    log.debug("findAllSorted() - end");
    return partidasPresupuestarias;
  }

}
