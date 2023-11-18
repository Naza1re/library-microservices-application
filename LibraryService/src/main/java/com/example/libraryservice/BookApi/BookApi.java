package com.example.libraryservice.BookApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class BookApi {

    public static List<Book> getAllBooks() throws IOException {
        HttpClient client = HttpClients.createDefault();
        System.out.println("Вход в метод поиска всех книг");
        HttpGet request = new HttpGet("http://localhost:8083/books/all-books");

        HttpResponse response = client.execute(request);

        String json = EntityUtils.toString(response.getEntity());
        System.out.println(json);


        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> books = objectMapper.readValue(json, new TypeReference<List<Book>>() {});

        return books;
    }

}
