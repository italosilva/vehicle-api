package br.com.italosilva.vehicleapi.controller.config;

import br.com.italosilva.vehicleapi.controller.exception.Info;
import br.com.italosilva.vehicleapi.service.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;

@ControllerAdvice
public class WebExceptionConfig {

    private final String MESSAGE_CONTRACT_ERROR = "Request failed: Field %s - Value %s";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Info> handleException(HttpServletRequest request, MethodArgumentNotValidException exception) {

        var httpStatus = HttpStatus.BAD_REQUEST;
        var errors = new ArrayList<String>();

        exception.getBindingResult().getAllErrors().forEach(item -> {
            var field = (FieldError) item;
            errors.add(MESSAGE_CONTRACT_ERROR.formatted(field.getField(), field.getRejectedValue()));
        });

        return new ResponseEntity<>(new Info(httpStatus.value(), exception.getClass().getSimpleName(), errors, request.getRequestURI()), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Info> handleException(HttpServletRequest request, Exception exception) {

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if(exception instanceof ConstraintViolationException)
            httpStatus = HttpStatus.BAD_REQUEST;

        if(exception instanceof MethodArgumentTypeMismatchException)
            httpStatus = HttpStatus.BAD_REQUEST;

        if(exception instanceof BusinessException)
            httpStatus = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(new Info(httpStatus.value(), exception.getClass().getSimpleName(), Collections.singletonList(exception.getMessage()), request.getRequestURI()), httpStatus);
    }
}
