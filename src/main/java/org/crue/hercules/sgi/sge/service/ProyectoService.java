package org.crue.hercules.sgi.sge.service;

import java.util.List;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLJPASupport;
import org.crue.hercules.sgi.framework.util.AssertHelper;
import org.crue.hercules.sgi.sge.dto.ProyectoRelacionesEliminadasInput;
import org.crue.hercules.sgi.sge.exceptions.ProyectoNotFoundException;
import org.crue.hercules.sgi.sge.model.Proyecto;
import org.crue.hercules.sgi.sge.repository.ProyectoRepository;
import org.crue.hercules.sgi.sge.repository.predicate.ProyectoPredicateResolver;
import org.springframework.data.domain.Page;
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
public class ProyectoService {

  private final ProyectoRepository repository;

  /**
   * Crea una entidad {@link Proyecto}.
   * 
   * @param proyecto la entidad {@link Proyecto}.
   * @return la entidad {@link Proyecto} creada.
   */
  @Transactional
  public Proyecto create(Proyecto proyecto) {
    log.debug("create(Proyecto proyecto) - start");
    Proyecto proyectoCreated = repository.save(proyecto);
    log.debug("create(Proyecto proyecto) - end");
    return proyectoCreated;
  }

  /**
   * Actualiza una entidad {@link Proyecto}.
   * 
   * @param proyecto la entidad {@link Proyecto}.
   * @return la entidad {@link Proyecto} actualizada.
   */
  @Transactional
  public Proyecto update(Proyecto proyecto) {
    log.debug("update(Proyecto proyecto) - start");

    return repository.findById(proyecto.getId()).map(data -> {
      data.setFechaFin(proyecto.getFechaFin());
      data.setFechaInicio(proyecto.getFechaInicio());
      data.setTitulo(proyecto.getTitulo());
      data.setResponsableRef(proyecto.getResponsableRef());

      Proyecto proyectoUpdated = repository.save(data);

      log.debug("update(Proyecto proyecto) - end");
      return proyectoUpdated;
    }).orElseThrow(() -> new ProyectoNotFoundException(proyecto.getId()));
  }

  /**
   * Obtiene una entidad {@link Proyecto} por id.
   * 
   * @param id identificador de la entidad {@link Proyecto}.
   * @return la entidad {@link Proyecto}.
   */
  public Proyecto findById(Long id) {
    log.debug("findById(Long id) - start");
    final Proyecto returnValue = repository.findById(id).orElseThrow(() -> new ProyectoNotFoundException(id));
    log.debug("findById(Long id) - end");
    return returnValue;
  }

  /**
   * Obtiene todas las entidades {@link Proyecto} paginadas y filtradas.
   *
   * @param query  información del filtro.
   * @param paging información de paginación.
   * @return el listado de entidades {@link Proyecto} paginadas y filtradas.
   */
  public Page<Proyecto> findAll(String query, Pageable paging) {
    log.debug("findAll(String query, Pageable paging) - start");

    Specification<Proyecto> specs = SgiRSQLJPASupport.toSpecification(query, ProyectoPredicateResolver.getInstance());

    Page<Proyecto> returnValue = repository.findAll(specs, paging);
    log.debug("findAll(String query, Pageable paging) - end");
    return returnValue;
  }

  @Transactional
  public void processNotificacionRelacionesEliminadas(Long id,
      List<ProyectoRelacionesEliminadasInput> proyectoRelacionesEliminadas) {
    log.debug("processNotificacionRelacionesEliminadas - id: {}, proyectoRelacionesEliminadas: {} - start", id,
        proyectoRelacionesEliminadas);

    AssertHelper.idNotNull(id, Proyecto.class);
    if (!repository.existsById(id)) {
      throw new ProyectoNotFoundException(id);
    }

    log.debug("processNotificacionRelacionesEliminadas - id: {}, proyectoRelacionesEliminadas: {} - end", id,
        proyectoRelacionesEliminadas);
  }

}
