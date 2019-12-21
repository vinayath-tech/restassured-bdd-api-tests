Feature: Delete a pet from a shop
  As a owner
  I want to be able to delete a pet
  So that I do not have unavailable pets

  Scenario: Search for available pets
    Given I execute a service to delete a pet
    Then the delete response code should be 200
#    And the pet should not be available in store