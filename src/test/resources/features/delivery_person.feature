Feature: Delivery Person Management

  Scenario: Create a new delivery person
    Given the delivery person details
    When I send a POST request to create delivery person "/delivery-person"
    Then the response status should be 201
    And the response should contain the delivery person details

  Scenario: Get a delivery person by ID
    Given a delivery person exists with ID 1
    When I send a GET request to "/delivery-person/1"
    Then the response status should be 200
    And the response should contain the delivery person details

  Scenario: Delete a delivery person
    Given a delivery person exists with ID 1
    When I send a DELETE request to "/delivery-person/1"
    Then the response status should be 204