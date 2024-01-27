package com.bc.ecommerce.application.exception;

/**
 * Not found element exception.
 */
public class NotFoundElementException extends ApiErrorException {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a NotFoundElementException.
   * @param field Not found resource.
   * @param cause The error.
   */
  public NotFoundElementException(String field, Throwable cause) {
    super(ErrorCode.NOT_FOUND_ELEMENT, field, cause);
  }

}
