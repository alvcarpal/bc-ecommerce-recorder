package com.bc.ecommerce.application.mapper;

import com.bc.ecommerce.application.exception.ApiErrorException;
import com.bc.ecommerce.infrastructure.rest.spring.dto.ErrorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Generic API Error. All the errors must follow this structure.
 */
@Mapper(componentModel = "spring")
public interface ApiErrorMapper {

  /**
   * Converts an API Exception to a DTO error.
   *
   * @param e The API Exception.
   * @return The DTO.
   */
  @Mapping(target = "code", source = "errorCode")
  @Mapping(target = "message", source = "errorCodeDescription")
  @Mapping(target = "level", source = "errorLevel")
  @Mapping(target = "description", source = "errorMessage")
  ErrorDto toApiErrorDto(ApiErrorException e);

}
