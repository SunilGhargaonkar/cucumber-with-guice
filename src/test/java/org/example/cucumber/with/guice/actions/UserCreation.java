package org.example.cucumber.with.guice.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.guice.ScenarioScoped;
import io.restassured.response.Response;
import org.example.cucumber.with.guice.actions.model.UserCreationRequestModel;
import org.example.cucumber.with.guice.actions.model.UserCreationResponseModel;
import org.example.cucumber.with.guice.framework.context.HttpContext;
import org.example.cucumber.with.guice.framework.exception.JsonReadWriteException;
import org.example.cucumber.with.guice.http.HttpClient;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@ScenarioScoped
public class UserCreation {
    private static final String TEST_ENDPOINT = "/api/users";
    private final HttpContext httpContext;
    private final HttpClient<Response> httpClient;
    private final String endpoint;
    private final String requestData;


    @Inject
    public UserCreation(HttpContext httpContext, HttpClient<Response> httpClient,
                        @Named("schema") String schema,
                        @Named("host") String host) {
        this.httpContext = httpContext;
        this.httpClient = httpClient;
        this.requestData = Objects.requireNonNull(httpContext.getRequestData(), "requestData must not be null");
        this.endpoint = Objects.requireNonNull(prepareEndpoint(schema, host), "endpoint must not be null");
    }

    public void constructRequest(final UserCreationRequestModel requestModel){
        final ObjectMapper objectMapper = new ObjectMapper();

        try{
            final String body = objectMapper.writeValueAsString(requestModel);

            httpContext.setRequestData(body);

        } catch (Exception exception){
            throw new JsonReadWriteException("Unable to serialize request to from Java to JSON", exception);
        }
    }

    public void execute() {
        final ObjectMapper objectMapper = new ObjectMapper();

        final Response response = httpClient.post(endpoint, requestData);

        try {
            final UserCreationResponseModel responseModel = objectMapper.readValue(
                    response.getBody().asString(), UserCreationResponseModel.class);

            httpContext.setResponseData(responseModel);
        } catch (final Exception exception) {
            throw new JsonReadWriteException("Unable to deserialize request from JSON to Java", exception);
        }
    }

    private String prepareEndpoint(final String schema, final String host) {
        return schema.concat("://").concat(host).concat(TEST_ENDPOINT);
    }
}
