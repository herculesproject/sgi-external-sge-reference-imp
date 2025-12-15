package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.framework.problem.Problem;
import org.crue.hercules.sgi.framework.problem.exception.ProblemException;
import org.crue.hercules.sgi.framework.problem.message.ProblemMessage;
import org.crue.hercules.sgi.framework.problem.spring.web.ProblemExceptionHandler;
import org.springframework.http.HttpStatus;

/**
 * ProyectoIdNullException
 */
public class ProyectoIdNullException extends ProblemException {

  /**
   * Crea una excepcion para indicar que el proyecto Id no puede ser null
   */
  public ProyectoIdNullException() {
    super(Problem.builder().type(ProblemExceptionHandler.VALIDATION_PROBLEM_TYPE)
        .title(ProblemMessage.builder().key(HttpStatus.class, HttpStatus.BAD_REQUEST.name()).build())
        .detail(ProblemMessage.builder().key(ProyectoIdNullException.class).build())
        .status(HttpStatus.BAD_REQUEST.value())
        .build());
  }

}
