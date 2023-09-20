package spring.advices;

import app.exception.NotFoundException;
import domain.exceptions.MachiKoroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class MachiKoroAdvice {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MachiKoroException.class})
    public String badRequest(MachiKoroException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public String notFound(NotFoundException exception) {
        return exception.getMessage();
    }

}
