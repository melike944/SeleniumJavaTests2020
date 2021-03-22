@regressionTest
Feature: Admin user should be able to add,edit,update,and delete products
  Background:
    Given user on login page
    When user enter username and password click on login button
    Then user successfully login to the system

  @addProductsTest
  Scenario: User should be able to add new Product
    Given User already on the dashboard page
    When User Click on Products link
    And user click on add product link to fill out product information
    Then Product should be added

  @deleteProductsTest
  Scenario: User should be able to delete Product
    Given User already on the dashboard page
    When User Click on Products link
    And user click on the delete icon
    Then Product should be deleted successfully



