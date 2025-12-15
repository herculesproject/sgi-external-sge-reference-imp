package org.crue.hercules.sgi.sge.repository;

import java.util.List;

import org.crue.hercules.sgi.sge.model.DocumentoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoDatoEconomicoRepository
    extends JpaRepository<DocumentoDetalle, String>, JpaSpecificationExecutor<DocumentoDetalle> {

  List<DocumentoDetalle> findByDatoEconomicoId(String datoEconomicoId);
}