package org.crue.hercules.sgi.sge.repository;

import org.crue.hercules.sgi.sge.model.CodigoEconomicoIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CodigoEconomicoIngresoRepository
    extends JpaRepository<CodigoEconomicoIngreso, String>, JpaSpecificationExecutor<CodigoEconomicoIngreso> {

}
