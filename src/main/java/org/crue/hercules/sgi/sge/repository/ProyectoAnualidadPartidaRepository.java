package org.crue.hercules.sgi.sge.repository;

import org.crue.hercules.sgi.sge.model.ProyectoAnualidadPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProyectoAnualidadPartidaRepository
    extends JpaRepository<ProyectoAnualidadPartida, Long>, JpaSpecificationExecutor<ProyectoAnualidadPartida> {

}
