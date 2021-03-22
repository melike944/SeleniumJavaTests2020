@DatabaseTest
Feature: user should be able to view product information in the cc_CubeCart_inventory table
  Scenario: user should be able to get product info
    Given a user has access to the cc_CubeCart_inventory table
    When user query the query script in the cc_CubeCart_inventory table
    Then user should be see product info