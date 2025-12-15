package org.crue.hercules.sgi.sge.controller;

import java.util.Base64;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.crue.hercules.sgi.sge.model.DocumentoDetalle;
import org.crue.hercules.sgi.sge.repository.DocumentoDatoEconomicoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DocumentoController
 */
@RestController
@RequestMapping(DocumentoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class DocumentoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "documentos";

  public static final String PATH_ARCHIVO = PATH_DELIMITER + "{id}/archivo";

  private final DocumentoDatoEconomicoRepository documentoRepository;

  /**
   * Devuelve el documento con el id indicado.
   * 
   * @param id Identificador del documento.
   * @return el documento correspondiente al id.
   */
  @GetMapping(PATH_ARCHIVO)
  public ResponseEntity<byte[]> findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);

    Optional<DocumentoDetalle> documentoOpt = documentoRepository.findById(id);
    if (documentoOpt.isEmpty()) {
      log.warn("findById({}) - Documento no encontrado", id);
      return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + documentoOpt.get().getNombreFichero() + "\"");
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(documentoOpt.get().getContenido().length);

    log.debug("findById({}) - end", id);
    return ResponseEntity.ok().headers(headers).body(documentoOpt.get().getContenido());
  }

}
