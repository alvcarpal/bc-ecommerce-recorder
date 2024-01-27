package com.bc.ecommerce.application.exception;

/**
 * Problems persisting exception.
 */
public class ProblemsPersistingException extends ApiErrorException {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a ProblemsPersistingException.
   * @param field The database information.
   * @param cause The error.
   */
  public ProblemsPersistingException(String field, Throwable cause) {
    super(ErrorCode.PROBLEM_PERSISTING, field, cause);
  }

}
