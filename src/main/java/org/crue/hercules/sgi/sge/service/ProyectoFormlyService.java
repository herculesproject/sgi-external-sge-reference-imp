package org.crue.hercules.sgi.sge.service;

import org.crue.hercules.sgi.sge.converter.ProyectoFormlyConverter;
import org.crue.hercules.sgi.sge.dto.ProyectoFormlyDto;
import org.crue.hercules.sgi.sge.model.Proyecto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProyectoFormlyService {

  private final ProyectoService proyectoService;
  private final ProyectoFormlyConverter proyectoFormlyConverter;

  /**
   * Obtiene un {@link ProyectoFormlyDto} por id.
   * 
   * @param id identificador de la entidad {@link Proyecto}.
   * @return el {@link ProyectoFormlyDto}.
   */
  public ProyectoFormlyDto findById(Long id) {
    log.debug("findById(Long id) - start");
    final ProyectoFormlyDto proyecto = proyectoFormlyConverter.convert(proyectoService.findById(id));
    log.debug("findById(Long id) - end");
    return proyecto;
  }

  /**
   * Crea un {@link Proyecto}.
   * 
   * @param proyectoFormly la entidad {@link Proyecto}.
   * @return el identificador del {@link Proyecto} creado.
   */
  @Transactional
  public String create(ProyectoFormlyDto proyectoFormly) {
    log.debug("create(ProyectoFormly proyectoFormly) - start");
    Proyecto proyectoCreated = proyectoService.create(proyectoFormlyConverter.convertToEntity(proyectoFormly));
    log.debug("create(ProyectoFormly proyectoFormly) - end");
    return proyectoCreated.getId().toString();
  }

  /**
   * Actualiza un {@link Proyecto}.
   * 
   * @param proyectoFormly la entidad {@link Proyecto}.
   * @param proyectoId     Identificador del {@link Proyecto}.
   * @return el identificador del {@link Proyecto} actualizada.
   */
  @Transactional
  public String update(ProyectoFormlyDto proyectoFormly, String proyectoId) {
    log.debug("update(ProyectoFormly proyectoFormly, String proyectoId) - start");
    proyectoFormly.setId(proyectoId);
    Proyecto proyectoUpdated = proyectoService.update(proyectoFormlyConverter.convertToEntity(proyectoFormly));
    log.debug("update(ProyectoFormly proyectoFormly, String proyectoId) - end");
    return proyectoUpdated.getId().toString();
  }

}
