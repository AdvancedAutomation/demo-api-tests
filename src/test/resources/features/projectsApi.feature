Feature: Projects POST, GET and DELETE

  Scenario: POST Project
    When I send a "POST" request to "/projects"
    """
    {
    "name":"Project POST Project",
    "description": "Project description test",
    "project_type": "public",
    "public": true,
    "initial_velocity": 12,
    "week_start_day": "Sunday",
    "enable_tasks": false,
    "enable_incoming_emails": false
    }
    """
    Then I validate the response has status code "200"
    And I validate the response contains "name" equals "Project POST Project"
    And I validate the response contains "description" equals "Project description test"
    And I validate the response contains "project_type" equals "public"
    And I validate the response contains "initial_velocity" equals "12"
    And I validate the response contains "public" equals "true"
    And I validate the response contains "week_start_day" equals "Sunday"
    And I validate the response contains "enable_tasks" equals "false"
    And I validate the response contains "enable_incoming_emails" equals "false"
    And I send a DELETE request to "/projects/{projectId}"
    And I validate the response has status code "204"

  Scenario: GET Project
    Given I send a "POST" request to "/projects"
    """
    {
    "name": "Project GET"
    }
    """
    When I send a GET request to "/projects/{projectId}"
    Then I validate the response contains "name" equals "Project GET"
    And I send a DELETE request to "/projects/{projectId}"
    And I validate the response has status code "204"

  Scenario: DELETE Project
    Given I send a "POST" request to "/projects"
    """
    {
    "name": "Project DELETE"
    }
    """
    When I send a DELETE request to "/projects/{projectId}"
    Then I validate the response has status code "204"