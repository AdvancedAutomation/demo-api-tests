Feature: Projects

  Scenario: PUT Project
    Given I send a "POST" request to "/projects"
    """
    {
    "name": "Project created by cucumber",
    "public": true,
    "week_start_day": "Tuesday",
    "iteration_length": 2
    }
    """
    And I save the response as "P"
    When I send a "PUT" request to "/projects/{P.id}"
    """
    {
    "name": "Project updated by cucumber",
    "week_start_day": "Friday"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "Project updated by cucumber"
    And I validate the response contains "week_start_day" equals "Friday"
    And I validate the component 1 from "time_zone" equals "America/Los_Angeles"
    And I validate the response contains "iteration_length" equals "2"
    And I validate the response contains "public" equals "true"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204
