package org.example.cucumber.with.guice.framework.context;

import io.cucumber.guice.ScenarioScoped;
import org.example.cucumber.with.guice.actions.model.UserCreationResponseModel;

@ScenarioScoped
public class HttpContext {

    private UserCreationResponseModel responseData;
    private String requestData;

    public UserCreationResponseModel getResponseData() {
        return responseData;
    }

    public void setResponseData(UserCreationResponseModel responseData) {
        this.responseData = responseData;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
}
