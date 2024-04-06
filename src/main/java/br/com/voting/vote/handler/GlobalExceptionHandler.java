package br.com.voting.vote.handler;


import br.com.voting.vote.exception.ExceptionDetails;
import br.com.voting.vote.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDetails handlerNotFoundException(NotFoundException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(NOT_FOUND.value())
                .title(NOT_FOUND.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }
}
