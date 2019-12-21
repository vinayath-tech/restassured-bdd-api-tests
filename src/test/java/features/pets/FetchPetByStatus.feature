Feature: Get pet by status
 As a customer
 I want to be able to find out availability of a pet
 So that I can buy the pet


 Scenario: Search a pet based on availability
   Given I execute a service to fetch pets by "sold" status
   Then the response code of pet availability endpoint should be 200
   And the response should contain "sold" status in body
#   Then the response returns the following headers:
#     | Access-Control-Allow-Headers |
#     | Access-Control-Allow-Methods |
#     | Access-Control-Allow-Origin  |
#     | Connection                   |
#     | Content-Type                 |
#     | Date                         |
#     | Server                       |

 Scenario: Search a pet based on unknown status
   Given I execute a service to fetch pets by "so" status
   Then the response code of pet availability endpoint should be 200
   And the response body should be empty

 Scenario: Search a pet by invalid service
  Given I execute an invalid service to fetch pets by status
  Then the response should return invalid response code 404