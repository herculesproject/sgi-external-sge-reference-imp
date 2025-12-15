package org.crue.hercules.sgi.sge.exceptions;

import org.crue.hercules.sgi.framework.exception.NotFoundException;
import org.crue.hercules.sgi.framework.problem.message.ProblemMessage;
import org.crue.hercules.sgi.framework.spring.context.support.ApplicationContextSupport;

/**
 * SgeNotFoundException
 */
public class SgeNotFoundException extends NotFoundException {

  private static final String PROBLEM_MESSAGE_PARAMETER_ENTITY = "entity";
  private static final String MESSAGE_KEY_ID = "id";

  /**
   * Serial version
   */
  private static final long serialVersionUID = 1L;

  public SgeNotFoundException(String message) {
    super(message);
  }

  public SgeNotFoundException(String id, Class<?> clazz) {
    super(ProblemMessage.builder().key(SgeNotFoundException.class)
        .parameter(PROBLEM_MESSAGE_PARAMETER_ENTITY, ApplicationContextSupport.getMessage(clazz))
        .parameter(MESSAGE_KEY_ID, id).build());
  }

  public SgeNotFoundException(Long id, Class<?> clazz) {
    this(id != null ? id.toString() : null, clazz);
  }

}
