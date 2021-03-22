Feature: login functionality

  @ValidUserLogin
  Scenario: user should be able to login with valid username and password
    Given user on login page
    When user enter username and password click on login button
    Then user successfully login to the system