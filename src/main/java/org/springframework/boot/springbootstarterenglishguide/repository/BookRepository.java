package org.springframework.boot.springbootstarterenglishguide.repository;

import org.springframework.boot.springbootstarterenglishguide.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Интерфейс BookRepository, расширяет интерфейс {@link CrudRepository}
 * <p/>
 * Содержит методы работы с БД
 * <p/>
 *
 * @author mihailinpk
 * created 08.02.2022 12:26
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    /**
     * Поиск книг по названию
     *
     * @param title название книги
     * @return список найденных книг
     */
    List<Book> findByTitle(String title);
}
