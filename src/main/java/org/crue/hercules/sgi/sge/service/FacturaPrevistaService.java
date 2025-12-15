package org.crue.hercules.sgi.sge.service;

import javax.validation.Valid;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLJPASupport;
import org.crue.hercules.sgi.sge.exceptions.FacturaPrevistaNotFoundException;
import org.crue.hercules.sgi.sge.model.FacturaPrevista;
import org.crue.hercules.sgi.sge.repository.FacturaPrevistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
@Transactional(readOnly = true)
public class FacturaPrevistaService {

  private final FacturaPrevistaRepository repository;

  /**
   * Crea una entidad {@link FacturaPrevista}.
   * 
   * @param facturaPrevista la entidad {@link FacturaPrevista}.
   * @return la entidad {@link FacturaPrevista} creada.
   */
  @Transactional
  public FacturaPrevista create(@Valid FacturaPrevista facturaPrevista) {
    log.debug("create(FacturaPrevista facturaPrevista) - start");
    FacturaPrevista facturaPrevistaCreated = repository.save(facturaPrevista);
    log.debug("create(FacturaPrevista facturaPrevista) - end");
    return facturaPrevistaCreated;
  }

  /**
   * Actualiza una entidad {@link FacturaPrevista}.
   * 
   * @param facturaPrevista la entidad {@link FacturaPrevista}.
   * @return la entidad {@link FacturaPrevista} actualizada.
   */
  @Transactional
  public FacturaPrevista update(@Valid FacturaPrevista facturaPrevista) {
    log.debug("update(FacturaPrevista facturaPrevista) - start");

    return repository.findById(facturaPrevista.getId()).map(data -> {
      data.setComentario(facturaPrevista.getComentario());
      data.setFechaEmision(facturaPrevista.getFechaEmision());
      data.setImporteBase(facturaPrevista.getImporteBase());
      data.setPorcentajeIVA(facturaPrevista.getPorcentajeIVA());
      data.setTipoFacturacion(facturaPrevista.getTipoFacturacion());

      FacturaPrevista facturaPrevistaUpdated = repository.save(data);

      log.debug("update(FacturaPrevista facturaPrevista) - end");
      return facturaPrevistaUpdated;
    }).orElseThrow(() -> new FacturaPrevistaNotFoundException(facturaPrevista.getId()));
  }

  /**
   * Obtiene todas las entidades {@link FacturaPrevista} paginadas y filtradas.
   *
   * @param query  información del filtro.
   * @param paging información de paginación.
   * @return el listado de entidades {@link FacturaPrevista} paginadas y
   *         filtradas.
   */
  public Page<FacturaPrevista> findAll(String query, Pageable paging) {
    log.debug("findAll(String query, Pageable paging) - start");

    Specification<FacturaPrevista> specs = SgiRSQLJPASupport.toSpecification(query);

    Page<FacturaPrevista> returnValue = repository.findAll(specs, paging);
    log.debug("findAll(String query, Pageable paging) - end");
    return returnValue;
  }

}
