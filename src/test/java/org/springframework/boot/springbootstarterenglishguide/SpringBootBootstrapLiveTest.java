package org.springframework.boot.springbootstarterenglishguide;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.springbootstarterenglishguide.entity.Book;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Класс SpringBootBootstrapLiveTest
 * <p/>
 * Вспомогательные методы, необходимые для тестов
 * <p/>
 *
 * @author mihailinpk
 * created 08.02.2022 15:41
 */
@SpringBootTest
public class SpringBootBootstrapLiveTest {

    /**
     * Ссылка на web-приложение
     */
    private static final String API_ROOT
            = "http://localhost:8081/api/books";

    /**
     * {@code [TEST]} Получение всех книг
     */
    @Test
    public void whenGetAllBooks_thenOK() {
        Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    /**
     * {@code [TEST]} Получение книги по названию
     */
    @Test
    public void whenGetBooksByTitle_thenOK() {
        Book book = createRandomBook();
        createBookAsUri(book);
        Response response = RestAssured.get(
                API_ROOT + "/title/" + book.getTitle());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class).size() > 0);
    }

    /**
     * {@code [TEST]} Создание книги и получение по {@code ID}
     */
    @Test
    public void whenGetCreatedBookById_thenOK() {
        Book book = createRandomBook();
        String location = createBookAsUri(book);
        Response response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(book.getTitle(), response.jsonPath().get("title"));
    }

    /**
     * {@code [TEST]} Попытка получить несуществующую книгу
     */
    @Test
    public void whenGetNotExitingBooksById_thenNotFound() {
        Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    /**
     * {@code [TEST]} Создание новой книги
     */
    @Test
    public void whenCreateNewBook_thenCreated() {
        Book book = createRandomBook();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    /**
     * {@code [TEST]} Попытка создать "сломанную" книгу (в данном случае без автора)
     */
    @Test
    public void whenInvalidBook_thenError() {
        Book book = createRandomBook();
        book.setAuthor(null);
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    /**
     * {@code [TEST]} Обновить существующую книгу
     */
    @Test
    public void whenUpdateCreatedBook_thenUpdated() {
        Book book = createRandomBook();
        String location = createBookAsUri(book);
        book.setId(Long.parseLong(location.split("api/books/")[1]));
        book.setAuthor("newAuthor");
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .put(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newAuthor", response.jsonPath().get("author"));
    }

    /**
     * {@code [TEST]} Удалить существующую книгу
     */
    @Test
    public void whenDeleteCreatedBook_thenOK()  {
        Book book = createRandomBook();
        String location = createBookAsUri(book);
        Response response = RestAssured.delete(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    /**
     * Создать и заполнить случайную книгу
     *
     * @return случайная книга
     */
    private Book createRandomBook() {
        Book book = new Book();
        book.setTitle(randomAlphabetic(10));
        book.setAuthor(randomAlphabetic(15));
        return book;
    }

    /**
     * Преобразовать книгу (экземпляр {@link Book}) в URL
     *
     * @param book создаваемоя книга
     * @return URL с переданной в качестве параметра книги
     */
    private String createBookAsUri(Book book) {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(book)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }


}
