package org.springframework.boot.springbootstarterenglishguide.controller;

import org.springframework.boot.springbootstarterenglishguide.entity.Book;
import org.springframework.boot.springbootstarterenglishguide.exception.BookIdMismatchException;
import org.springframework.boot.springbootstarterenglishguide.exception.BookNotFoundException;
import org.springframework.boot.springbootstarterenglishguide.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс BookController
 * <p/>
 * Контроллер, обрабатывающий запросы работы с книгами <br/>
 * запрос {@code /api/books}
 * <p/>
 *
 * @author mihailinpk
 * created 08.02.2022 12:40
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    /**
     * Репозиторий
     */
    private final BookRepository bookRepository;

    /**
     * Внедрение зависимостей через конструктор
     *
     * @param bookRepository репозиторий
     */
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Обработка GET-запроса {@code /}, получаем все книги
     *
     * @return все книги в виде {@link Iterable}
     */
    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    /**
     * Обработка GET-запроса {@code /title/название_книги}
     *
     * @param bookTitle название книги
     * @return список найденных книг
     */
    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    /**
     * Обработка GET-запроса {@code /ID_книги}
     *
     * @param id ID книги
     * @return найденная книга
     */
    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    /**
     * Обработка POST-запроса {@code /} с телом {@link Book} в виде JSON, создание новой книги
     *
     * @param book новая книга
     * @return статус
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    /**
     * Обработка DELETE-запроса {@code /ID_удаляемой_книги}, удаление книги по ID
     *
     * @param id ID удаляемой книги
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    /**
     * Обработка PUT-запроса {@code /ID_обновляемой_книги} с телом {@link Book} в виде JSON и ID в цифровом виде
     *
     * @param book обновляемая книга
     * @param id   ID обновляемой книги
     * @return обновленная книга
     */
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }

}
