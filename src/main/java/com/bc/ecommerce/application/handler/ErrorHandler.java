package com.bc.ecommerce.application.handler;

import com.bc.ecommerce.application.exception.ApiErrorException;
import com.bc.ecommerce.application.exception.HeaderMissingException;
import com.bc.ecommerce.application.exception.UnhandledException;
import com.bc.ecommerce.application.mapper.ApiErrorMapper;
import com.bc.ecommerce.infrastructure.rest.spring.dto.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ErrorHandler Class: Exception handler providing centralized handling for errors
 * and returning standardized responses using the @ExceptionHandler annotation.
 */
@Log4j2
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class ErrorHandler {

  private final ApiErrorMapper apiErrorMapper;

  /**
   * Handles unexpected exceptions.
   *
   * @param e The exception.
   * @return The api response.
   */
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorDto> handleApiError(Exception e) {
    log.error("Root cause", e);
    return handleApiError(new UnhandledException(e));
  }

  /**
   * Handles missing request header exceptions.
   *
   * @param e The exception.
   * @return The api response.
   */
  @ExceptionHandler(value = MissingRequestHeaderException.class)
  public ResponseEntity<ErrorDto> handleApiError(MissingRequestHeaderException e) {
    log.error("Root cause", e);
    return handleApiError(new HeaderMissingException(e.getHeaderName(), e));
  }

  /**
   * Handles ApiError exceptions.
   *
   * @param e The api error exception.
   * @return The api response.
   */
  @ExceptionHandler(value = ApiErrorException.class)
  public ResponseEntity<ErrorDto> handleApiError(ApiErrorException e) {
    ErrorDto resp = apiErrorMapper.toApiErrorDto(e);
    log.error("Handling exception. Response: {}, error: ",resp, e);
    return ResponseEntity
            .status(e.getHttpStatus())
            .contentType(MediaType.APPLICATION_PROBLEM_JSON)
            .body(resp);
  }

}
