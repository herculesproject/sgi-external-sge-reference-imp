package org.crue.hercules.sgi.sge.service;

import java.util.List;

import org.crue.hercules.sgi.sge.model.ProyectoAnualidadPartida;
import org.crue.hercules.sgi.sge.repository.ProyectoAnualidadPartidaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProyectoAnualidadPartidaService {

  private final ProyectoAnualidadPartidaRepository repository;

  /**
   * Crea una lista de entidad {@link ProyectoAnualidadPartida}.
   * 
   * @param proyectoAnualidadPartidas la lista de
   *                                  {@link ProyectoAnualidadPartida}.
   * @return la lista {@link ProyectoAnualidadPartida} creadas.
   */
  public List<ProyectoAnualidadPartida> create(List<ProyectoAnualidadPartida> proyectoAnualidadPartidas) {
    log.debug("create(List<ProyectoAnualidadPartida> proyectoAnualidadPartidas) - start");
    List<ProyectoAnualidadPartida> proyectoAnualidadPartidasCreated = repository.saveAll(proyectoAnualidadPartidas);
    log.debug("create(List<ProyectoAnualidadPartida> proyectoAnualidadPartidas) - end");
    return proyectoAnualidadPartidasCreated;
  }

}
