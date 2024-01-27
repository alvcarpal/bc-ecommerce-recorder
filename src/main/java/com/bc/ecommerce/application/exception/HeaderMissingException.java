package com.bc.ecommerce.application.exception;

/**
 * Header missing exception.
 */
public class HeaderMissingException extends ApiErrorException {

  private static final long serialVersionUID = 1L;

  /**
   * Creates a HeaderMissingException.
   * @param field The missing header.
   * @param cause The error.
   */
  public HeaderMissingException(String field, Throwable cause) {
    super(ErrorCode.HEADER_FIELD_MISSING, field, cause);
  }

  /**
   * Creates a HeaderMissingException.
   *
   * @param field The missing header.
   */
  public HeaderMissingException(String field) {
    this(field, null);
  }

}
