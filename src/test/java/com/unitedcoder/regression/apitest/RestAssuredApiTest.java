package com.unitedcoder.regression.apitest;



import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;




public class RestAssuredApiTest {


    private static String baseurl="https://api.openweathermap.org/data/2.5/weather";
    private static String apikey="695c05eb094d03f25e8efde7e51349d3";

    @Test(description = "wheather forecast Api Test")
    public void getCurrentWeatherByCityName(){
        Response response= RestAssured.given().param("q","London").and().param("appid",apikey).when().get(baseurl);
        System.out.println(response.getBody().prettyPrint());
        System.out.println(baseurl);
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        Assert.assertTrue(response.getBody().jsonPath().getString("name").equalsIgnoreCase("London"));
    }


    @Test
    public void createSingleUser(){

        RestAssured.baseURI="https://reqres.in/";
        given().header("Content-Type","application/json").body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}").when().post("/api/users").then().assertThat().statusCode(201);


    }


}





