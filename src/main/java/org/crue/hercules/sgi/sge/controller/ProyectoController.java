package org.crue.hercules.sgi.sge.controller;

import java.util.List;

import javax.validation.Valid;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sge.converter.ProyectoAnualidadPartidaConverter;
import org.crue.hercules.sgi.sge.converter.ProyectoConverter;
import org.crue.hercules.sgi.sge.converter.ProyectoFormlyConverter;
import org.crue.hercules.sgi.sge.dto.ProyectoAnualidadPartidaInput;
import org.crue.hercules.sgi.sge.dto.ProyectoFormlyInput;
import org.crue.hercules.sgi.sge.dto.ProyectoFormlyOutput;
import org.crue.hercules.sgi.sge.dto.ProyectoOutput;
import org.crue.hercules.sgi.sge.dto.ProyectoRelacionesEliminadasInput;
import org.crue.hercules.sgi.sge.model.Proyecto;
import org.crue.hercules.sgi.sge.model.ProyectoAnualidadPartida;
import org.crue.hercules.sgi.sge.service.ProyectoAnualidadPartidaService;
import org.crue.hercules.sgi.sge.service.ProyectoFormlyService;
import org.crue.hercules.sgi.sge.service.ProyectoService;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ProyectoController
 */
@RestController
@RequestMapping(ProyectoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class ProyectoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "proyectos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";
  public static final String PATH_ANUALIDADES = PATH_DELIMITER + "anualidades";
  public static final String PATH_FORMLY = PATH_DELIMITER + "formly";
  public static final String PATH_FORMLY_ID = PATH_FORMLY + PATH_ID;
  public static final String PATH_NOTIFICAR_RELACIONES_ELIMINADAS = PATH_ID + PATH_DELIMITER
      + "notificaciones/relaciones-eliminadas";

  private final ProyectoService service;
  private final ProyectoConverter converter;
  private final ProyectoFormlyService proyectoFormlyService;
  private final ProyectoFormlyConverter proyectoFormlyConverter;
  private final ProyectoAnualidadPartidaService proyectoAnualidadService;
  private final ProyectoAnualidadPartidaConverter proyectoAnualidadConverter;

  /**
   * Devuelve una lista paginada y filtrada de {@link Proyecto}.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link Proyecto} paginadas y filtradas.
   */
  @GetMapping()
  public ResponseEntity<Page<ProyectoOutput>> findAll(@RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAll({}, {}) - start", query, paging);
    Page<ProyectoOutput> page = converter.convert(service.findAll(query, paging));
    log.debug("findAll({}, {}) - end", query, paging);
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

  /**
   * Devuelve el {@link Proyecto} con el id indicado.
   * 
   * @param id Identificador de {@link Proyecto}.
   * @return {@link Proyecto} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public ProyectoOutput findById(@PathVariable Long id) {
    log.debug("findById({}) - start", id);
    ProyectoOutput proyecto = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return proyecto;
  }

  /**
   * Crea un nuevo {@link Proyecto}
   * 
   * @param proyecto el nuevo {@link Proyecto}.
   * @return {@link Proyecto} creado.
   */
  @PostMapping(PATH_FORMLY)
  public ResponseEntity<String> createProyectoFormly(@Valid @RequestBody ProyectoFormlyInput proyecto) {
    log.debug("createPersonaFormly(ProyectoFormlyInput proyecto) - start");
    String proyectoId = proyectoFormlyService.create(proyectoFormlyConverter.convert(proyecto));
    log.debug("createPersonaFormly(ProyectoFormlyInput proyecto) - end");
    return new ResponseEntity<>(JSONObject.quote(proyectoId), HttpStatus.OK);
  }

  /**
   * Actualiza un {@link Proyecto}
   * 
   * @param proyecto la {@link Proyecto} actualizado.
   * @param id       identificador del {@link Proyecto}.
   * @return {@link Proyecto} actualizado.
   */
  @PutMapping(PATH_FORMLY_ID)
  public ResponseEntity<String> updateProyectoFormly(@Valid @RequestBody ProyectoFormlyInput proyecto,
      @PathVariable String id) {
    log.debug("updateProyectoFormly(ProyectoFormlyInput proyecto, String id) - start");
    String proyectoId = proyectoFormlyService.update(proyectoFormlyConverter.convert(proyecto), id);
    log.debug("updateProyectoFormly(ProyectoFormlyInput proyecto, String id) - end");
    return new ResponseEntity<>(JSONObject.quote(proyectoId), HttpStatus.OK);
  }

  /**
   * Devuelve el {@link Proyecto} con el id indicado.
   * 
   * @param id identificador del {@link Proyecto}.
   * @return {@link Proyecto} correspondiente al id
   */
  @GetMapping(PATH_FORMLY_ID)
  public ProyectoFormlyOutput findProyectoFormlyById(@PathVariable Long id) {
    log.debug("findProyectoFormlyById({}) - start", id);
    ProyectoFormlyOutput returnValue = proyectoFormlyConverter.convert(proyectoFormlyService.findById(id));
    log.debug("findProyectoFormlyById({}) - end", id);
    return returnValue;
  }

  /**
   * Crea los {@link ProyectoAnualidadPartida} de la lista.
   * 
   * @param proyectoAnualidadPartidas lista de {@link ProyectoAnualidadPartida}.
   * @return {@link HttpStatus#OK}
   */
  @PostMapping(PATH_ANUALIDADES)
  public ResponseEntity<Void> notificarPartidasAnualidad(
      @RequestBody List<ProyectoAnualidadPartidaInput> proyectoAnualidadPartidas) {
    log.debug("create(List<ProyectoAnualidadPartidaInput> proyectoAnualidadPartidas) - start");
    proyectoAnualidadService.create(proyectoAnualidadConverter.convertToEntities(proyectoAnualidadPartidas));
    log.debug("create(List<ProyectoAnualidadPartidaInput> proyectoAnualidadPartidas) - end");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Procesa las relaciones eliminadas
   * 
   * @param id                           Identificador del proyecto
   * @param proyectoRelacionesEliminadas lista de relaciones eliminadas
   * @return {@link HttpStatus#ACCEPTED}
   */
  @PostMapping(PATH_NOTIFICAR_RELACIONES_ELIMINADAS)
  public ResponseEntity<Void> processNotificacionRelacionesEliminadas(
      @PathVariable Long id,
      @RequestBody List<ProyectoRelacionesEliminadasInput> proyectoRelacionesEliminadas) {
    log.debug(
        "proccessNotificacionRelacionesEliminadas(Long id, List<ProyectoRelacionesEliminadasInput> proyectoRelacionesEliminadas) - start");
    service.processNotificacionRelacionesEliminadas(id, proyectoRelacionesEliminadas);
    log.debug(
        "proccessNotificacionRelacionesEliminadas(Long id, List<ProyectoRelacionesEliminadasInput> proyectoRelacionesEliminadas) - end");
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

}
