package org.crue.hercules.sgi.sge.repository;

import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartidaPresupuestariaGastoRepository
    extends JpaRepository<PartidaPresupuestariaGasto, String>, JpaSpecificationExecutor<PartidaPresupuestariaGasto> {

}
