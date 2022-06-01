Feature: API tests

  @3319935 @api
  Scenario: GET Request
    Given endpoint /api/users
    When I send GET request
    Then I should get 200 response code
    And List of users should be returned

  @3319936 @api
  Scenario: POST Request
    Given endpoint /api/users
    When I send POST request
    Then I should get 201 response code