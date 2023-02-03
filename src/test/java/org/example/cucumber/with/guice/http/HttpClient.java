package org.example.cucumber.with.guice.http;

import io.restassured.response.Response;

public interface HttpClient<T>  {
    T post(String endpoint, Object body);
}
