package org.springframework.boot.springbootstarterenglishguide.exception;

/**
 * Класс BookIdMismatchException
 * <p/>
 * Исключение при событии "такого ID не существует"
 * <p/>
 *
 * @author mihailinpk
 * created 08.02.2022 15:02
 */
public class BookIdMismatchException extends RuntimeException {

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BookIdMismatchException() {
        super("Book ID mismatch !!!");
    }

}
