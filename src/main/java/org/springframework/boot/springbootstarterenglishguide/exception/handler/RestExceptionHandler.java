package org.springframework.boot.springbootstarterenglishguide.exception.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.springbootstarterenglishguide.exception.BookIdMismatchException;
import org.springframework.boot.springbootstarterenglishguide.exception.BookNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Класс RestExceptionHandler, расширяет класс {@link ResponseEntityExceptionHandler}
 * <p/>
 * Обрабатывает исключения
 * <p/>
 *
 * @author mihailinpk
 * created 08.02.2022 15:04
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработка исключения {@link BookNotFoundException}
     *
     * @param ex      экземпляр исключения {@link BookNotFoundException}
     * @param request web-запрос
     * @return тело ответа на web-запрос со статусом {@code 404(NOT_FOUND)}
     */
    @ExceptionHandler({BookNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Book not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Обработка исключений {@link BookIdMismatchException}, {@link ConstraintViolationException},
     * {@link DataIntegrityViolationException}
     *
     * @param ex      экземпляр одного из исключений {@link BookIdMismatchException}, {@link ConstraintViolationException},
     *                {@link DataIntegrityViolationException}
     * @param request web-запрос
     * @return тело ответа на web-запрос со статусом {@code 400(BAD_REQUEST)}
     */
    @ExceptionHandler({BookIdMismatchException.class,
            ConstraintViolationException.class,
            DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
