package org.crue.hercules.sgi.sge.repository;

import org.crue.hercules.sgi.sge.model.CodigoEconomicoGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CodigoEconomicoGastoRepository
    extends JpaRepository<CodigoEconomicoGasto, String>, JpaSpecificationExecutor<CodigoEconomicoGasto> {

}
