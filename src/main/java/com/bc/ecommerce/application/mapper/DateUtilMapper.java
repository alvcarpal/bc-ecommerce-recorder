package com.bc.ecommerce.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import java.time.Instant;
import java.time.OffsetDateTime;
import static java.time.ZoneOffset.UTC;

/**
 * DateUtilMapper interface. Mapstruct data conversions utilities.
 * com.bc.ecommerce.application.mapper.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
@Mapper(componentModel = "spring")
public interface DateUtilMapper {

  @Named("toOffsetDateTime")
  default OffsetDateTime toOffsetDateTime(Instant date) {
    return date == null ? null : OffsetDateTime.ofInstant(date, UTC);
  }

}
