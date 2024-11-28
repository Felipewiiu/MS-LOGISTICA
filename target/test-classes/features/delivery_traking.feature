Feature: Delivery Traking Management

Scenario: Create a new delivery traking
  Given a delivery traking details
  When I send a POST request to create new delivery traking "/delivery-traking"
  Then the response status should be 201
  And the response should contain the delivery traking details

Scenario: Get a delivery traking by ID
  Given a delivery traking exists with ID 1
  When I send a GET request to fetch delivery traking "/delivery-traking/1"
  Then the response status should be 200
  And the response should contain the delivery traking details

  Scenario: Delete a delivery traking
    Given a delivery traking exists with ID 1
    When I send a DELETE request to "/delivery-traking/1"
    Then the response status should be 204