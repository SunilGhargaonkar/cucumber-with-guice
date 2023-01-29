package org.example.cucumber.with.guice.framework.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.cucumber.with.guice.actions.UserCreation;
import org.example.cucumber.with.guice.actions.model.UserCreationRequestModel;
import org.example.cucumber.with.guice.framework.context.HttpContext;


import static org.junit.jupiter.api.Assertions.*;

public class TestSteps {

    private final UserCreationRequestModel requestModel;
    private final UserCreation userCreation;
    private final HttpContext httpContext;

    @Inject
    public TestSteps(UserCreationRequestModel requestModel, UserCreation userCreation, HttpContext httpContext) {
        this.requestModel = requestModel;
        this.userCreation = userCreation;
        this.httpContext = httpContext;
    }


    @Given("The application is available")
    public void applicationTestBaseUrl() {
        //Make sure application is running
    }

    @When("I request to create a user with {} as name and {} as job")
    public void userCreation(final String name, final String job) {
        requestModel.setName(name);
        requestModel.setJob(job);

        userCreation.constructRequest(requestModel);
    }

    @Then("I get the response with {} as name and {} as job")
    public void userCreationResponse(final String name, final String job) {
        userCreation.execute();

        assertAll(
                () -> assertEquals(name, httpContext.getResponseData().getName(), "name is not as expected"),
                () -> assertEquals(job, httpContext.getResponseData().getJob(), "job is not as expected")
        );
    }

}
