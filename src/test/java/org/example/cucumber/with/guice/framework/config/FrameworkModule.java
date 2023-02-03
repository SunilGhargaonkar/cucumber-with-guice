package org.example.cucumber.with.guice.framework.config;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import io.restassured.response.Response;
import org.example.cucumber.with.guice.http.HttpClient;
import org.example.cucumber.with.guice.http.RestAssuredHttpClient;

public class FrameworkModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<HttpClient<Response>>(){}).to(RestAssuredHttpClient.class);

    }
}
