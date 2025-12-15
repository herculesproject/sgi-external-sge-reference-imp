package org.crue.hercules.sgi.sge.repository;

import org.crue.hercules.sgi.sge.model.FacturaPrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FacturaPrevistaRepository
    extends JpaRepository<FacturaPrevista, Long>, JpaSpecificationExecutor<FacturaPrevista> {

}
