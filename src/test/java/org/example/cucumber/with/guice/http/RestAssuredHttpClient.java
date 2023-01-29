package org.example.cucumber.with.guice.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.guice.ScenarioScoped;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

@ScenarioScoped
public class RestAssuredHttpClient implements HttpClient<Response>{

    private static final int SUCCESS_RESPONSE_CODE = 202;

    private RequestSpecification getRequestSpecification() {
        return given().contentType(ContentType.JSON).config(getConfig());
    }

    private RestAssuredConfig getConfig() {
        final ObjectMapper objectMapper = new ObjectMapper();

        return RestAssuredConfig.config()
                .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation())
                .objectMapperConfig(
                        objectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> objectMapper))
                .encoderConfig(
                        EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }


    @Override
    public Response post(final String endpoint, final Object body) {
        final Response response = getRequestSpecification().body(body).post(endpoint);

        if (response.getStatusCode() != SUCCESS_RESPONSE_CODE) {
            response.then().log().all();
        }

        return response.then().extract().response();
    }
}
