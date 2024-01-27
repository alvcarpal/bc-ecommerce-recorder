package com.bc.ecommerce.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * ApiError class.
 * The code is an 8-digit code with the format LLHHHNNN, where LL is the layer (01 -> application, 02 -> domain,
 * 03 -> infraestructure), HHH is the HTTP result code (400, 502, ...), and the NNN is the error number inside
 * the layer LL.
 */
@Getter
public abstract class ApiErrorException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  private final String errorCode;
  private final String errorCodeDescription;
  private final String errorMessage;
  private final ErrorLevel errorLevel;
  private final HttpStatus httpStatus;

  protected ApiErrorException(ErrorCode eCod, String errorMessageInfo, Throwable e) {
    super(e);
    this.httpStatus = eCod.getStatus();
    this.errorCode = eCod.getCode();
    this.errorCodeDescription = eCod.getCodeDescription();
    this.errorMessage = String.format(eCod.getMessage(), errorMessageInfo);
    this.errorLevel = eCod.getLevel();
  }

}
