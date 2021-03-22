@regressionTest
Feature: Admin user should be able to add , delete ,edit customer information
  Background:
    Given user on login page
    When user enter username and password click on login button
    Then user successfully login to the system

  @AddCustomerTest
  Scenario: User should be able to add customer
    Given user already on the dashboard page
    When user click on Customer List link
    And user click on add customer link to fill out all customer information
    Then new Customer should be added

  @deleteCustomer
  Scenario: User should be able to delete customer
    Given user already on the dashboard page
    When user click on Customer List link
    And user click on delete icon
    Then user should be deleted successfully