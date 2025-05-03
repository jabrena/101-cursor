Feature: Greek Gods API
  As a user of the Greek Gods service
  I want to retrieve the list of known Greek Gods
  So that I can see which gods are currently stored.

  Scenario: Retrieve the list of Greek Gods
    When I send a GET request to "/gods"
    Then the response status should be 200
    And the response body should be a JSON array of strings
    # Example of a more specific check if data is predictable during test execution
    # And the response body contains "Zeus" 