
Feature: Test user login successful

  Scenario Outline: List all available books
    Given The application is available
    When I request to create a user with <Name> as name and <Job> as job
    Then I get the response with <Name> as name and <Job> as job
    Examples:
      | Name     | Job    |
      | morpheus | leader |