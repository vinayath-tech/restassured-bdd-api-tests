Feature: Create and update a new pet
  As a shop owner
  I want to be able create a new pet in website
  So that customers can see a newly added pet in website

  Scenario: Create a dog as new pet
    Given I execute a service to add a new pet
    Then the response code of create endpoint should be 200
    And the response should contain expected response values:
       | categoryName | name      | tagName    |
       | Gokul-testing | Dog-gokul | tag-gokul |


  Scenario: Update a name of a new pet
    Given I execute a service to update a pet
    Then the response code of update endpoint should be 200
    And the response should return the following updated name:
      | updated-test-name |