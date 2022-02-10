package org.springframework.boot.springbootstarterenglishguide.entity;

import javax.persistence.*;

/**
 * Класс Book
 * <p/>
 * Сущность книги
 * <p/>
 *
 * @author mihailinpk
 * created 08.02.2022 11:50
 */
@Entity
public class Book {

    /**
     * ID книги
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Название
     */
    @Column(nullable = false, unique = true)
    private String title;

    /**
     * Автор
     */
    @Column(nullable = false)
    private String author;

    /**
     * Геттер для поля {@code ID}
     *
     * @return значение поля {@code ID}
     */
    public long getId() {
        return id;
    }

    /**
     * Сеттер для поля {@code ID}
     *
     * @param id новое значение поля {@code ID}
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Геттер для поля {@code Название}
     *
     * @return значение поля {@code Название}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Сеттер для поля {@code Название}
     *
     * @param title новое значение поля {@code Название}
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Геттер для поля {@code Автор}
     *
     * @return значение поля {@code Автор}
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Сеттер для поля {@code Автор}
     *
     * @param author новое значение поля {@code Автор}
     */
    public void setAuthor(String author) {
        this.author = author;
    }

}
