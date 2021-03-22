Feature: user should be able to get weather forecast data using open weather map API
  user should call the weather api with valid api key and get forecast information
  @weatherapitest
  Scenario Outline: user can get forecast data for any city
    Given a "<city>" and "<country>" and api key
    When a user calls the weather api with the api key
    Then user should see the weather data for the city
    Examples:
      |city|country|
      |London|uk   |
      |Istanbul|tr |
      |Toronto |ca |
      |Chicago |us |
      |Tokyo   |jp |
      |Hotan  |cn |
      |urumqi  |cn |