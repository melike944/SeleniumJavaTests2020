package com.unitedcoder.regression.uitest.cucumberframework.apisteps;

import com.unitedcoder.regression.apitest.RestApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Assert;

public class WeatherApiSteps {
    private static String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
    private static String apiKey = "695c05eb094d03f25e8efde7e51349d3";
    private String city;
    private String country;
    private static CloseableHttpResponse closeableHttpResponse;

    @Given("a {string} and {string} and api key")
    public void aAndAndApiKey(String arg0, String arg1) {
        city = arg0;
        country = arg1;
    }

    @When("a user calls the weather api with the api key")
    public void aUserCallsTheWeatherApiWithTheApiKey() {
        RestApiClient client = new RestApiClient();
        //api.openweathermap.org/data/2.5/weather?q=london&appid=70926ddfd37fdf454548b8db13695995
        String apiUrl = baseUrl + "?q=" + city + "," + country + "&appid=" + apiKey;
        closeableHttpResponse = client.getRequest(apiUrl);
    }

    @Then("user should see the weather data for the city")
    public void userShouldSeeTheWeatherDataForTheCity() {
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
