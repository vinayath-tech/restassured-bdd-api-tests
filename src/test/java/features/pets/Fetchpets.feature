Feature: Get available pets in the shop
  As a customer
  I want to be able to find out list of available pets
  So that I can buy a pet

  Scenario: Search for available pets
    Given I execute a service to fetch available pets
    Then the response code should be 200
    Then I should see the name as "updated-test-name"

