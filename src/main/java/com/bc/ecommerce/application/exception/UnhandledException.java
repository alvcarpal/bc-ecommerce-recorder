package com.bc.ecommerce.application.exception;

/**
 * Unhandled exception.
 */
public class UnhandledException extends ApiErrorException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an UnhandledException.
   *
   * @param cause The cause.
   */
  public UnhandledException(Throwable cause) {
    super(ErrorCode.UNHANDLED_ERROR, cause.getMessage(), cause);
  }

}
