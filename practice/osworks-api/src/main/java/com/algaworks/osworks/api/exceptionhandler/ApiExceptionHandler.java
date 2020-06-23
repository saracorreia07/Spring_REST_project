package com.algaworks.osworks.api.exceptionhandler;

import com.algaworks.osworks.domain.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessHandle(BusinessException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        Trouble trouble = new Trouble();
        trouble.setStatus(status.value());
        trouble.setTitle(ex.getMessage());
        trouble.setDateTime(LocalDateTime.now());

        return handleExceptionInternal(ex, trouble, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ArrayList<Trouble.Field> fields = new ArrayList<Trouble.Field>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {

            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new Trouble.Field(name, message));
        }

        Trouble trouble = new Trouble();

        trouble.setStatus(status.value());
        trouble.setTitle("One or more fields are invalid. Fill it correctly and try again");
        trouble.setDateTime(LocalDateTime.now());
        trouble.setFields(fields);

        return super.handleExceptionInternal(ex, trouble, headers, status, request);
    }
}
