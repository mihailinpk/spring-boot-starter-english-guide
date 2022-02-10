package org.springframework.boot.springbootstarterenglishguide.exception;

/**
 * Класс BookNotFoundException, расширяет класс {@link RuntimeException}
 * <p/>
 * Исключение при событии "не найдена книга"
 * <p/>
 *
 * @author mihailinpk
 * created 08.02.2022 13:13
 */
public class BookNotFoundException extends RuntimeException {

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BookNotFoundException() {
        super("Book not found !!!");
    }

}