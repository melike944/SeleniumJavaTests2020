package com.unitedcoder.regression.apitest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ApiPostRequest {
    CloseableHttpResponse httpResponse;
    RestApiClient apiClient;
    @Test
    public void postRequest() {
        String url="https://reqres.in/api/users";
        HashMap<String,String> requestHeader=new HashMap<>();
        requestHeader.put("Content-Type","application/json");
        Users users=new Users("David","SDET");// serilization:java object--json object
        ObjectMapper mapper=new ObjectMapper();
        try {
            mapper.writeValue(new File("testdata//employee.json"),users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //convert java Object in json string
        String userJsonString= null;
        try {
            userJsonString = mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(userJsonString);
        //apiClient=new RestApiClient();
        apiClient=new RestApiClient();
        httpResponse=apiClient.postRequest(url,userJsonString,requestHeader);

        int statusCode=httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,201);
        //Json String
        String responseString= null;
        try {
            responseString = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("The response From Api: "+responseJson);
        Users userResponseObject= null;
        try {
            userResponseObject = mapper.readValue(responseString, Users.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(userResponseObject.getId());
        System.out.println(userResponseObject.getCreatedAt());
        Assert.assertTrue(users.getName().equals(userResponseObject.getName()));
        Assert.assertTrue(users.getJob().equals(userResponseObject.getJob()));

    }
}

