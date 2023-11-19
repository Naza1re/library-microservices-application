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

    public static List<Book> getAllBooks(String token) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8082/books/all-books");
        request.setHeader("Authorization", token);
        HttpResponse response = client.execute(request);
        String json = EntityUtils.toString(response.getEntity());
        System.out.println(json);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> books = objectMapper.readValue(json, new TypeReference<List<Book>>() {});

        return books;
    }

}
