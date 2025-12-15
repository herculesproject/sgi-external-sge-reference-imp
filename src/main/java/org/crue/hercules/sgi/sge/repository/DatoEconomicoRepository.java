package org.crue.hercules.sgi.sge.repository;

import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoEconomicoRepository
    extends JpaRepository<DatoEconomico, String>, JpaSpecificationExecutor<DatoEconomico> {

}