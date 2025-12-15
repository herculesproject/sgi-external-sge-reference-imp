package org.crue.hercules.sgi.sge.repository;

import org.crue.hercules.sgi.sge.model.PartidaPresupuestariaIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartidaPresupuestariaIngresoRepository
    extends JpaRepository<PartidaPresupuestariaIngreso, String>,
    JpaSpecificationExecutor<PartidaPresupuestariaIngreso> {

}
