package org.example.cucumber.with.guice.http;

public interface HttpClient <T> {
    T post(String endpoint, Object body);
}
