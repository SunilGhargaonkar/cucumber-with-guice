package org.example.cucumber.with.guice.framework.config;

import com.google.inject.AbstractModule;
import org.example.cucumber.with.guice.http.HttpClient;
import org.example.cucumber.with.guice.http.RestAssuredHttpClient;

public class FrameworkModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HttpClient.class).to(RestAssuredHttpClient.class);
    }
}
