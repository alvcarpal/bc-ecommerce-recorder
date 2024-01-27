package com.bc.ecommerce.application.handler;

import com.bc.ecommerce.application.exception.ErrorCode;
import com.bc.ecommerce.application.exception.HeaderMissingException;
import com.bc.ecommerce.application.exception.NotFoundElementException;
import com.bc.ecommerce.application.exception.ProblemsPersistingException;
import com.bc.ecommerce.application.exception.UnhandledException;
import com.bc.ecommerce.application.mapper.ApiErrorMapperImpl;
import com.bc.ecommerce.infrastructure.rest.spring.dto.ErrorDto;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;

public class ErrorHandlerTest {

    private ErrorHandler handler = new ErrorHandler(new ApiErrorMapperImpl());

    @Test
    public void testUnexpectedException() {
        ResponseEntity<ErrorDto> response = handler.handleApiError(new Exception());

        Assert.assertEquals(500, response.getStatusCodeValue());
    }

    @Test
    public void testHeaderMissingException() {
        HeaderMissingException exception = new HeaderMissingException("test");
        ResponseEntity<ErrorDto> response = handler.handleApiError(exception);

        Assert.assertEquals(exception.getHttpStatus().value(), response.getStatusCodeValue());
        Assert.assertEquals(exception.getErrorCode(), response.getBody().getCode());
    }

    @Test
    public void testSpringHeaderMissingException() {
        MissingRequestHeaderException e = Mockito.mock(MissingRequestHeaderException.class);
        Mockito.doReturn("test").when(e).getHeaderName();
        ResponseEntity<ErrorDto> response = handler.handleApiError(e);

        Assert.assertEquals(ErrorCode.HEADER_FIELD_MISSING.getStatus().value(), response.getStatusCodeValue());
        Assert.assertEquals(ErrorCode.HEADER_FIELD_MISSING.getCode(), response.getBody().getCode());
    }


    @Test
    public void testUnhandledException() {
        UnhandledException exception = new UnhandledException(new Exception());
        ResponseEntity<ErrorDto> response = handler.handleApiError(exception);

        Assert.assertEquals(exception.getHttpStatus().value(), response.getStatusCodeValue());
        Assert.assertEquals(exception.getErrorCode(), response.getBody().getCode());
    }

    @Test
    public void testProblemsPersistingException() {
        ProblemsPersistingException exception = new ProblemsPersistingException("test", new Exception());
        Assert.assertEquals(ErrorCode.PROBLEM_PERSISTING.getCode(), exception.getErrorCode());
    }

    @Test
    public void testNotFoundException() {
        NotFoundElementException exception = new NotFoundElementException("test", new Exception());
        Assert.assertEquals(ErrorCode.NOT_FOUND_ELEMENT.getCode(), exception.getErrorCode());
    }


}
