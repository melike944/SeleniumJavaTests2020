package com.unitedcoder.regression.apitest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class ApiGetRequest {
    CloseableHttpResponse httpResponse;
    @Test
    public void getUserList(){
        String url="https://reqres.in/api/users?page=2";
        RestApiClient apiClient=new RestApiClient();
        httpResponse =apiClient.getRequest(url);
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,200);
    }
    @Test
    public void getEmployeeList(){
        String url="http://dummy.restapiexample.com/api/v1/employees";
        RestApiClient apiClient=new RestApiClient();
        httpResponse =apiClient.getRequest(url);
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,200);
    }
    @Test
    public void getWithHeader(){
        String url="http://dummy.restapiexample.com/api/v1/employees";
        RestApiClient apiClient=new RestApiClient();
        HashMap<String,String> requestHeader=new HashMap<>();
        requestHeader.put("Content-Type","application/json");
        httpResponse =apiClient.getRequestWithHeader(url,requestHeader);
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

}
