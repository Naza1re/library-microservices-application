package com.example.bookservice.LibraryApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LibraryApi {

    public static HttpStatus addBookInLibrary(Long id) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost( "http://localhost:8082/library/add-book-record/"+id);
        request.setHeader("Content-Type", "application/json");
        HttpResponse response = client.execute(request);
        System.out.println(response);
        return HttpStatus.OK;

    }


}
