Feature: Fetch Store Inventory
  As a store owner
  I need to be able to find th inventories
  So that I should be able to find total pets available in store

  Scenario: Store Inventory
    Given I execute a service to fetch store inventories
    Then the service should return 200 response code
    And I should see the following data in response:
      | sold | pending | available |
      |   60 |   55    |    696    |