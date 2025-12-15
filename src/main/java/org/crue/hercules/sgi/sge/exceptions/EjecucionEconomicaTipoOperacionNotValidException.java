package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.framework.problem.Problem;
import org.crue.hercules.sgi.framework.problem.exception.ProblemException;
import org.crue.hercules.sgi.framework.problem.message.ProblemMessage;
import org.crue.hercules.sgi.framework.problem.spring.web.ProblemExceptionHandler;
import org.crue.hercules.sgi.sge.enums.EjecucionEconomicaTipoOperacionEnum;
import org.springframework.http.HttpStatus;

/**
 * EjecucionEconomicaTipoOperacionNotValidException
 */
public class EjecucionEconomicaTipoOperacionNotValidException extends ProblemException {

  /**
   * Crea una excepcion para indicar que el tipo de operacion no es un
   * {@link EjecucionEconomicaTipoOperacionEnum} valido
   *
   * @param tipoOperacion el tipo de operacion
   */
  public EjecucionEconomicaTipoOperacionNotValidException(String tipoOperacion) {
    super(Problem.builder().type(ProblemExceptionHandler.VALIDATION_PROBLEM_TYPE)
        .title(ProblemMessage.builder().key(HttpStatus.class, HttpStatus.BAD_REQUEST.name()).build())
        .detail(ProblemMessage.builder().key(EjecucionEconomicaTipoOperacionNotValidException.class)
            .parameter("tipoOperacion", tipoOperacion == null ? "null" : tipoOperacion)
            .build())
        .status(HttpStatus.BAD_REQUEST.value())
        .build());
  }

}